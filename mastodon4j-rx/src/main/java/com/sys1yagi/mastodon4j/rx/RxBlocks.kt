package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.method.Blocks
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxBlocks(client: MastodonClient) {
    val blocks = Blocks(client)

    fun getBlocks(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val blocks = blocks.getBlocks(range)
                it.onSuccess(blocks.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
