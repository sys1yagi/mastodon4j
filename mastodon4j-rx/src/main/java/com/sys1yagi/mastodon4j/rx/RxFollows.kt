package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.method.Follows
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxFollows(client: MastodonClient) {
    val follows = Follows(client)

    fun postRemoteFollow(uri: String): Single<Account> {
        return Single.create {
            try {
                val account = follows.postRemoteFollow(uri)
                it.onSuccess(account.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}