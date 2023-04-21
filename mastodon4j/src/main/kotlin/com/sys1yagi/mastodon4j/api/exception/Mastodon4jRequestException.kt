package com.sys1yagi.mastodon4j.api.exception

import okhttp3.Response

class Mastodon4jRequestException : Exception {
    val response: Response?

    constructor(response: Response) : super(response.message()) {
        this.response = response
    }

    constructor(e : Exception) : super(e) {
        this.response = null
    }

    fun isErrorResponse() = response != null
}
