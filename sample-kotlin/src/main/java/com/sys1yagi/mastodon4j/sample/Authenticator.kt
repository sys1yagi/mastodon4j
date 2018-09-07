package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.api.method.Apps
import okhttp3.OkHttpClient
import java.io.File
import java.util.*

object Authenticator {
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"
    const val ACCESS_TOKEN = "access_token"

    fun appRegistrationIfNeeded(instanceName: String, credentialFilePath: String, useStreaming: Boolean = false): MastodonClient {
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
        return MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson())
                .accessToken(properties[ACCESS_TOKEN].toString())
                .apply {
                    if (useStreaming) {
                        useStreamingApi()
                    }
                }
                .build()
    }

    fun getAccessToken(instanceName: String,
                       clientId: String,
                       clientSecret: String,
                       email: String,
                       password: String
    ): AccessToken {
        val client = MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson()).build()
        val apps = Apps(client)
        return apps.postUserNameAndPassword(clientId, clientSecret, Scope(), email, password).execute()
    }

    fun appRegistration(instanceName: String): AppRegistration {
        val client = MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson()).build()
        val apps = Apps(client)
        return apps.createApp(
                "kotlindon",
                scope = Scope()
        ).execute()
    }
}