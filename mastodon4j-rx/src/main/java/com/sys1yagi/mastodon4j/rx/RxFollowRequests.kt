package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.method.FollowRequests
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Completable
import io.reactivex.Single

class RxFollowRequests(client: MastodonClient) {
    val followRequests = FollowRequests(client)

    fun getFollowRequests(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = followRequests.getFollowRequests(range)
                it.onSuccess(accounts.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postAuthorize(accountId: Long): Completable {
        return Completable.create {
            try {
                followRequests.postAuthorize(accountId)
                it.onComplete()
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postReject(accountId: Long): Completable {
        return Completable.create {
            try {
                followRequests.postReject(accountId)
                it.onComplete()
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}