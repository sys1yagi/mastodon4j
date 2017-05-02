package com.sys1yagi.mastodon4j.api.method

import com.google.gson.Gson
import com.sys1yagi.kmockito.any
import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import com.sys1yagi.mastodon4j.testtool.MockClient
import okhttp3.*
import org.amshove.kluent.shouldEqualTo
import org.junit.Test
import org.mockito.ArgumentMatchers
import java.net.SocketTimeoutException

class AppsTest {
    @Test
    fun createApp() {
        val client: MastodonClient = mock()
        val response: Response = Response.Builder()
                .code(200)
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        AssetsUtil.readFromAssets("app_registration.json")
                ))
                .build()
        client.getInstanceName().invoked.thenReturn("mastodon.cloud")
        client.post(ArgumentMatchers.anyString(), any()).invoked.thenReturn(response)
        client.getSerializer().invoked.thenReturn(Gson())

        val apps = Apps(client)
        val registration = apps.createApp(
                clientName = "mastodon-android-sys1yagi",
                scope = Scope(Scope.Name.ALL)
        )

        registration.instanceName shouldEqualTo "mastodon.cloud"
        registration.clientId shouldEqualTo "client id"
        registration.clientSecret shouldEqualTo "client secret"
        registration.redirectUri shouldEqualTo "urn:ietf:wg:oauth:2.0:oob"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun createAppWithException() {
        val client = MockClient.ioException()

        val apps = Apps(client)
        apps.createApp(
                clientName = "mastodon-android-sys1yagi",
                scope = Scope(Scope.Name.ALL)
        )
    }

    @Test
    fun getOAuthUrl() {
        val client: MastodonClient = mock()
        client.getInstanceName().invoked.thenReturn("mastodon.cloud")

        val url = Apps(client).getOAuthUrl("client_id", Scope(Scope.Name.ALL))
        url shouldEqualTo "https://mastodon.cloud/oauth/authorize?client_id=client_id&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=read write follow"
    }
}
