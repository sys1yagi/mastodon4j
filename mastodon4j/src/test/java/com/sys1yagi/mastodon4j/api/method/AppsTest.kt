package com.sys1yagi.mastodon4j.api.method

import com.google.gson.Gson
import com.sys1yagi.kmockito.any
import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.kmockito.spy
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import okhttp3.*
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.assertj.core.api.Assertions.assertThat
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

        assertThat(registration.instanceName).isEqualTo("mastodon.cloud")
        assertThat(registration.clientId).isEqualTo("client id")
        assertThat(registration.clientSecret).isEqualTo("client secret")
        assertThat(registration.redirectUri).isEqualTo("urn:ietf:wg:oauth:2.0:oob")
    }

    @Test
    fun getOAuthUrl(){
        val client: MastodonClient = mock()
        client.getInstanceName().invoked.thenReturn("mastodon.cloud")

        val url = Apps(client).getOAuthUrl("client_id", Scope(Scope.Name.ALL))
        assertThat(url).isEqualTo("https://mastodon.cloud/oauth/authorize?client_id=client_id&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=read write follow")
    }
}
