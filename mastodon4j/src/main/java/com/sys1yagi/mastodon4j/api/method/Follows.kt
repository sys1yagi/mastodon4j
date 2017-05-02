package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follows
 */
class Follows(private val client: MastodonClient) {
    /**
     * POST /api/v1/follows
     * @param uri: username@domain of the person you want to follow
     */
    @Throws(Mastodon4jRequestException::class)
    fun postRemoteFollow(uri: String): Account {
        val parameters = Parameter()
                .append("uri", uri)
                .build()
        val response = client.post("follows",
                RequestBody.create(
                        MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                        parameters
                )
        )
        if (response.isSuccessful) {
            return response.fromJson(client.getSerializer(), Account::class.java)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
