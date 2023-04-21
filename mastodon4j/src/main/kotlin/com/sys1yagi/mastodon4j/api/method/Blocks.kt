package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#blocks
 */
class Blocks(private val client: MastodonClient) {

    //  GET /api/v1/blocks
    @JvmOverloads
    fun getBlocks(range: Range = Range()):MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get("blocks", range.toParameter())
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }
}
