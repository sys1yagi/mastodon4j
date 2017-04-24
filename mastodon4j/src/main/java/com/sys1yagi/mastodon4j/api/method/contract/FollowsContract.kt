package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.entity.Account

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follows
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/follows_controller.rb
 */
interface FollowsContract {

    interface Public {
        // none
    }

    interface AuthRequired {
        fun postRemoteFollow(uri: String): Account
    }
}
