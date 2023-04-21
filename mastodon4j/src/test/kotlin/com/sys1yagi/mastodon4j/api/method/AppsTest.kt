package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore

import kotlin.test.assertFailsWith

class AppsTest {
    @Test 
    fun createApp() {
        val client: MastodonClient = MockClient.mock("app_registration.json")
        client.getInstanceName().invoked.thenReturn("mastodon.cloud")

        val apps = Apps(client)
        val registration = apps.createApp(
            clientName = "mastodon-android-sys1yagi",
            scope = Scope(Scope.Name.ALL)
        ).execute()

        registration.instanceName shouldBeEqualTo "mastodon.cloud"
        registration.clientId shouldBeEqualTo "client id"
        registration.clientSecret shouldBeEqualTo "client secret"
        registration.redirectUri shouldBeEqualTo "urn:ietf:wg:oauth:2.0:oob"
    }

    @Test  
    fun createAppWithException() {
        assertFailsWith<Mastodon4jRequestException>{

            val client = MockClient.ioException()

            val apps = Apps(client)
            apps.createApp(
                clientName = "mastodon-android-sys1yagi",
                scope = Scope(Scope.Name.ALL)
            ).execute()
        }
    }

    @Test 
    fun getOAuthUrl() {
        val client: MastodonClient = mock()
        client.getInstanceName().invoked.thenReturn("mastodon.cloud")

        val url = Apps(client).getOAuthUrl("client_id", Scope(Scope.Name.ALL))
        url shouldBeEqualTo "https://mastodon.cloud/oauth/authorize?client_id=client_id&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=read write follow"
    }

    @Test 
    fun getAccessToken() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        val apps = Apps(client)
        val accessToken = apps.getAccessToken("test", "test", code = "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1493188835
    }

    @Test 
    fun getAccessTokenWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client: MastodonClient = MockClient.ioException()
            val apps = Apps(client)
            apps.getAccessToken("test", "test", code = "test").execute()
        }
    }

    @Test 
    fun postUserNameAndPassword() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        val apps = Apps(client)
        val accessToken = apps.postUserNameAndPassword("test", "test", Scope(Scope.Name.ALL), "test", "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1493188835
    }

    @Test 
    fun postUserNameAndPasswordWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client: MastodonClient = MockClient.ioException()
            val apps = Apps(client)
            apps.postUserNameAndPassword("test", "test", Scope(Scope.Name.ALL), "test", "test").execute()
        }
    }
}
