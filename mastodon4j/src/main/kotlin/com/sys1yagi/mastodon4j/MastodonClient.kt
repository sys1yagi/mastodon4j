package com.sys1yagi.mastodon4j

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


open class MastodonClient
private constructor(
        private val instanceName: String,
        private val client: OkHttpClient,
        private val gson: Gson
) {

    class Builder(private val instanceName: String,
                  private val okHttpClientBuilder: OkHttpClient.Builder,
                  private val gson: Gson) {

        private var accessToken: String? = null
        private var debug = false

        fun accessToken(accessToken: String) = apply {
            this.accessToken = accessToken
        }

        fun useStreamingApi() = apply {
            okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        }

        fun debug() = apply {
            this.debug = true
        }

        fun build(): MastodonClient {
            return MastodonClient(
                    instanceName,
                    okHttpClientBuilder.addNetworkInterceptor(AuthorizationInterceptor(accessToken)).build(),
                    gson
            ).also {
                it.debug = debug
            }
        }
    }

    private var debug = false

    fun debugPrint(log: String) {
        if (debug) {
            println(log)
        }
    }

    private class AuthorizationInterceptor(val accessToken: String? = null) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val compressedRequest = originalRequest.newBuilder()
                    .headers(originalRequest.headers())
                    .method(originalRequest.method(), originalRequest.body())
                    .apply {
                        accessToken?.let {
                            header("Authorization", String.format("Bearer %s", it));
                        }
                    }
                    .build()
            return chain.proceed(compressedRequest)
        }
    }

    val baseUrl = "https://${instanceName}/api/v1"

    open fun getSerializer() = gson

    open fun getInstanceName() = instanceName

    open fun get(path: String, parameter: Parameter? = null): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val urlWithParams = parameter?.let {
                "$url?${it.build()}"
            } ?: url
            val call = client.newCall(
                    Request.Builder()
                            .url(urlWithParams)
                            .get()
                            .build())
            return call.execute()
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    open fun postUrl(url: String, body: RequestBody): Response {
        try {
            debugPrint(url)
            val call = client.newCall(
                    Request.Builder()
                            .url(url)
                            .post(body)
                            .build())
            return call.execute()
        } catch (e: IllegalArgumentException) {
            throw Mastodon4jRequestException(e)
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    open fun post(path: String, body: RequestBody) =
            postUrl("$baseUrl/$path", body)

    open fun patch(path: String, body: RequestBody): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                    Request.Builder()
                            .url(url)
                            .patch(body)
                            .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    open fun delete(path: String): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                    Request.Builder()
                            .url(url)
                            .delete()
                            .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }
}
