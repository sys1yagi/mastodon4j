package com.sys1yagi.mastodon4j

import java.net.URLEncoder
import java.util.ArrayList

class Parameter {
    private val parameters = ArrayList<Pair<String, String>>()

    fun append(key: String, value: String): Parameter {
        parameters.add(Pair(key, value))
        return this
    }

    fun append(key: String, value: Int): Parameter = append(key, value.toString())

    fun append(key: String, value: Long): Parameter = append(key, value.toString())

    fun append(key: String, value: Boolean): Parameter = append(key, value.toString())

    fun <T> append(key: String, value: List<T>): Parameter {
        value.forEach {
            append("$key[]", it.toString())
        }
        return this
    }

    fun build(): String =
            parameters
                    .map {
                        "${it.first}=${URLEncoder.encode(it.second, "utf-8")}"
                    }
                    .joinToString(separator = "&")

}
