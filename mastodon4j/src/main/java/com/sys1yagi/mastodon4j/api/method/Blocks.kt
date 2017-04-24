package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.BlocksContract
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#blocks
 */
class Blocks(val client: MastodonClient) : BlocksContract.Public, BlocksContract.AuthRequired {

    //  GET /api/v1/blocks
    override fun getBlocks(range: Range): List<Account> {
        val response = client.get("blocks", range.toParameter())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
