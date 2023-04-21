package com.sys1yagi.mastodon4j.api

import com.sys1yagi.mastodon4j.Parameter

class Range
@JvmOverloads
constructor(val maxId: Long? = null, val sinceId: Long? = null, val limit: Int = 20) {
    fun toParameter() = Parameter().apply {
        maxId?.let { append("max_id", it) }
        sinceId?.let { append("since_id", it) }
        append("limit", limit)
    }
}