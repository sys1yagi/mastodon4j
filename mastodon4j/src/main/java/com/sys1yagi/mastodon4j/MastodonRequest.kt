package com.sys1yagi.mastodon4j

import com.google.gson.JsonParser
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.toPageable
import okhttp3.Response
import java.lang.Exception

open class MastodonRequest<T>(
        private val executor: () -> Response,
        private val mapper: (String) -> Any
) {
    var action: (String) -> Unit = {}

    private var isPageable: Boolean = false

    fun toPageable() = apply {
        isPageable = true
    }

    fun doOnJson(action: (String) -> Unit) = apply {
        this.action = action
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(Mastodon4jRequestException::class)
    fun execute(): T {
        val response = executor()
        if (response.isSuccessful) {
            try {
                val body = response.body().string()
                val element = JsonParser().parse(body)
                if (element.isJsonObject) {
                    action(body)
                    return mapper(body) as T
                } else {
                    val list = arrayListOf<Any>()
                    element.asJsonArray.forEach {
                        val json = it.toString()
                        action(json)
                        list.add(mapper(json))
                    }
                    if (isPageable) {
                        return list.toPageable(response) as T
                    } else {
                        return list as T
                    }
                }
            } catch (e: Exception) {
                throw Mastodon4jRequestException(e)
            }
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}