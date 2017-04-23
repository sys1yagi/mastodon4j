package com.sys1yagi.mastodon4j.api.exception

import okhttp3.Response

class Mastodon4jRequestException(val response: Response) : Exception(response.message())
