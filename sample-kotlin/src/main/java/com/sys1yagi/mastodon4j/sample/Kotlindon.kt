package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.api.method.Apps
import com.sys1yagi.mastodon4j.api.method.Blocks
import com.sys1yagi.mastodon4j.api.method.Timelines
import okhttp3.OkHttpClient
import java.io.File
import java.util.*

class Kotlindon {
    companion object {
        const val CLIENT_ID = "client_id"
        const val CLIENT_SECRET = "client_secret"
        const val ACCESS_TOKEN = "access_token"

        @JvmStatic
        fun main(args: Array<String>) {
            val instanceName = args[0]
            val credentialFilePath = args[1]
            val client = appRegistrationIfNeeded(instanceName, credentialFilePath)
            listenHome(client)
        }

        fun listenHome(client: MastodonClient) {
            val timelines = Timelines(client)
            var pageable: Pageable<Status>? = null
            while (true) {
                val result = pageable?.let {
                    timelines.getHome(it.prevRange(limit = 5))
                } ?: timelines.getHome(Range(limit = 5))

                result.part.sortedBy { it.createdAt }.forEach {
                    println(it.account?.displayName)
                    println(it.content)
                    println(it.createdAt)
                    println("------------------------")
                }
                if (result.part.isNotEmpty()) {
                    pageable = result
                }
                println("wait next load...")
                Thread.sleep(10000)
            }
        }

        fun appRegistrationIfNeeded(instanceName: String, credentialFilePath: String): MastodonClient {
            val file = File(credentialFilePath)
            if (!file.exists()) {
                println("create $credentialFilePath.")
                file.createNewFile()
            }
            val properties = Properties()
            println("load $credentialFilePath.")
            properties.load(file.inputStream())
            if (properties[CLIENT_ID] == null) {
                println("try app registration...")
                val appRegistration = appRegistration(instanceName)
                properties.put(CLIENT_ID, appRegistration.clientId)
                properties.put(CLIENT_SECRET, appRegistration.clientSecret)
                properties.store(file.outputStream(), "app registration")
            } else {
                println("app registration found...")
            }
            val clientId = properties[CLIENT_ID]?.toString() ?: throw IllegalStateException("client id not found")
            val clientSecret = properties[CLIENT_SECRET]?.toString() ?: throw IllegalStateException("client secret not found")

            if (properties[ACCESS_TOKEN] == null) {
                println("get access token for $instanceName...")
                println("please input your email...")
                val email = System.`in`.bufferedReader().readLine()
                println("please input your password...")
                val pass = System.`in`.bufferedReader().readLine()
                val accessToken = getAccessToken(instanceName,
                        clientId,
                        clientSecret,
                        email,
                        pass
                )
                properties.put(ACCESS_TOKEN, accessToken.accessToken)
                properties.store(file.outputStream(), "app registration")
            } else {
                println("access token found...")
            }
            return MastodonClient(instanceName, OkHttpClient(), Gson(), properties[ACCESS_TOKEN].toString())
        }

        fun getAccessToken(instanceName: String,
                           clientId: String,
                           clientSecret: String,
                           email: String,
                           password: String
        ): AccessToken {
            val client = MastodonClient(instanceName, OkHttpClient(), Gson())
            val apps = Apps(client)
            return apps.postUserNameAndPassword(clientId, clientSecret, Scope(), email, password)
        }

        fun appRegistration(instanceName: String): AppRegistration {
            val client = MastodonClient(instanceName, OkHttpClient(), Gson())
            val apps = Apps(client)
            return apps.createApp(
                    "kotlindon",
                    scope = Scope()
            )
        }
    }
}
