package com.sys1yagi.mastodon4j

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import okhttp3.Response
import java.lang.Exception

class MastodonRequest<T>(private val executor: () -> Response, private val mapper: (String) -> T) {

    var action: (String) -> Unit = {}

    fun doOnJson(action: (String) -> Unit): MastodonRequest<T> {
        this.action = action
        return this
    }

    @Throws(Mastodon4jRequestException::class)
    fun execute(): T {
        val response = executor()
        if (response.isSuccessful) {
            try {
                val body = response.body().string()
                action(body)
                return mapper(body)
            } catch (e: Exception) {
                throw Mastodon4jRequestException(e)
            }
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}