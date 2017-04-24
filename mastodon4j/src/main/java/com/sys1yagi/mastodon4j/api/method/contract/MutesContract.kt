package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#mutes
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/mutes_controller.rb
 */
interface MutesContract {
    interface Public {
        // none
    }

    interface AuthRequired {
        fun getMutes(range: Range = Range()): List<Account>
    }
}
