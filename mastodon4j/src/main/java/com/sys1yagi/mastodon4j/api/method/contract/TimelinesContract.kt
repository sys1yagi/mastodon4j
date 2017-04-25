package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/timelines_controller.rb
 */
interface TimelinesContract {
    interface Public {
        fun getLocalPublic(range: Range = Range()): List<Status>
        fun getFederatedPublic(range: Range = Range()): List<Status>

        fun getLocalTag(tag: String, range: Range = Range()): List<Status>
        fun getFederatedTag(tag: String, range: Range = Range()): List<Status>

    }

    interface AuthRequired {
        fun getHome(range: Range = Range()): List<Status>
    }
}
