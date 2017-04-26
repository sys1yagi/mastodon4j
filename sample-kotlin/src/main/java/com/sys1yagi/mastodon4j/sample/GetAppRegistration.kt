package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.Apps
import okhttp3.OkHttpClient

object GetAppRegistration {
    fun main(args: Array<String>) {
        val client = MastodonClient("mstdn.jp", OkHttpClient(), Gson())
        val apps = Apps(client)
        try {
            val registration = apps.createApp(
                    "mastodon4j-sample-app",
                    "urn:ietf:wg:oauth:2.0:oob",
                    Scope(Scope.Name.ALL),
                    ""
            )
            System.out.println("instance=" + registration.instanceName)
            System.out.println("client_id=" + registration.clientId)
            System.out.println("client_secret=" + registration.clientSecret)
        } catch (e: Mastodon4jRequestException) {
            val statusCode = e.response.code()
        }
    }
}
