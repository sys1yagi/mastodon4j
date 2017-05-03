package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#mutes
 */
class Mutes(private val client: MastodonClient) {
    // GET /api/v1/mutes
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getMutes(range: Range = Range()): Pageable<Account> {
        val response = client.get("mutes", range.toParameter())
        if (response.isSuccessful) {
            return response.fromJson<List<Account>>(client.getSerializer(), genericType<List<Account>>())
                    .toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
