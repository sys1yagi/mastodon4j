package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.entity.Results
import com.sys1yagi.mastodon4j.api.method.Public
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxPublic(client: MastodonClient) {
    val publicMethod = Public(client)

    fun getInstance(): Single<Instance> {
        return Single.create {
            try {
                val instance = publicMethod.getInstance()
                it.onSuccess(instance)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getSearch(query: String, resolve: Boolean = true): Single<Results> {
        return Single.create {
            try {
                val results = publicMethod.getSearch(query, resolve)
                it.onSuccess(results)
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}