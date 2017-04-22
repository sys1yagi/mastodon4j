package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follow-requests
 */
class FollowRequests(val client: MastodonClient) {
    //    GET /api/v1/follow_requests
    fun getFollowRequests() {

    }
//    POST /api/v1/follow_requests/:id/authorize

    fun postAuthorize(followRequestId: Long) {

    }

    //    POST /api/v1/follow_requests/:id/reject
    fun postReject(followRequestId: Long) {

    }
}
