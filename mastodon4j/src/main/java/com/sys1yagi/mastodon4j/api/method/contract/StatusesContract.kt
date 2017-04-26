package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Card
import com.sys1yagi.mastodon4j.api.entity.Context
import com.sys1yagi.mastodon4j.api.entity.Status

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#statuses
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/statuses_controller.rb
 */
interface StatusesContract {

    interface Public {
        // none
    }

    interface AuthRequired {
        fun getStatus(statusId: Long): Status

        fun getContext(statusId: Long): Context

        fun getCard(statusId: Long): Card

        fun getRebloggedBy(statusId: Long, range: Range = Range()): Pageable<Account>

        fun getFavouritedBy(statusId: Long, range: Range = Range()): Pageable<Account>

        fun postStatus(
                status: String,
                inReplyToId: Long? = null,
                mediaIds: List<Long>? = null,
                sensitive: Boolean = false,
                spoilerText: String? = null,
                visibility: Status.Visibility = Status.Visibility.Public
        ): Status

        fun deleteStatus(statusId: Long)

        fun postReblog(statusId: Long): Status

        fun postUmreblog(statusId: Long): Status

        fun postFavourite(statusId: Long): Status

        fun postUnfavourite(statusId: Long): Status
    }
}
