package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#mutes
 */
class Mutes(val client: MastodonClient) {
    // GET /api/v1/mutes
    fun getMutes(range: Range = Range()): List<Account> {
        val response = client.get("mutes", range.toParameter())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }
}
