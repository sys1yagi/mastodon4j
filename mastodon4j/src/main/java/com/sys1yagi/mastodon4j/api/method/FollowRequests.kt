package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.FollowRequestsContract
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follow-requests
 */
class FollowRequests(val client: MastodonClient) : FollowRequestsContract.Public, FollowRequestsContract.AuthRequired {
    // GET /api/v1/follow_requests
    override fun getFollowRequests(range: Range): List<Account> {
        val response = client.get("follow_requests", range.toParameter())
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

    //  POST /api/v1/follow_requests/:id/authorize
    override fun postAuthorize(accountId: Long) {
        val response = client.post("follow_requests/$accountId/authorize", emptyRequestBody())
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/follow_requests/:id/reject
    override fun postReject(accountId: Long) {
        val response = client.post("follow_requests/$accountId/reject", emptyRequestBody())
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response)
        }
    }
}
