package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Relationship
import com.sys1yagi.mastodon4j.api.entity.Status

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#accounts
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/accounts_controller.rb
 */
interface AccountsContract {

    interface Public {
        // none
    }

    interface AuthRequired {
        fun getAccount(accountId: Long): Account
        fun getVerifyCredentials(): Account
        fun updateCredential(displayName: String?, note: String?, avatar: String?, header: String?): Account
        fun getFollowers(accountId: Long, range: Range): Pageable<Account>
        fun getFollowing(accountId: Long, range: Range): Pageable<Account>
        fun getStatuses(accountId: Long, onlyMedia: Boolean, range: Range): Pageable<Status>
        fun postFollow(accountId: Long): Relationship
        fun postUnFollow(accountId: Long): Relationship
        fun postBlock(accountId: Long): Relationship
        fun postUnblock(accountId: Long): Relationship
        fun postMute(accountId: Long): Relationship
        fun postUnmute(accountId: Long): Relationship
        fun getRelationships(accountIds: List<Long>): Pageable<Relationship>
        fun getAccountSearch(query: String, limit: Int = 40): Pageable<Account>
    }
}
