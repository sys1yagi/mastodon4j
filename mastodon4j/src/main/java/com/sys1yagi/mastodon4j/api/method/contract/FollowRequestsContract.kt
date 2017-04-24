package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follow-requests
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/follow_requests_controller.rb
 */
interface FollowRequestsContract {
    interface Public {

    }

    interface AuthRequired {
        fun getFollowRequests(range: Range = Range()): List<Account>
        fun postAuthorize(accountId: Long)
        fun postReject(accountId: Long)
    }
}
