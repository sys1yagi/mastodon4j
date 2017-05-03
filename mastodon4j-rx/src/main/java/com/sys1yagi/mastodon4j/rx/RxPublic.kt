package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.entity.Results
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Public
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import com.sys1yagi.mastodon4j.rx.extensions.single
import io.reactivex.Single

class RxPublic(client: MastodonClient) {
    val publicMethod = Public(client)

    fun getInstance(): Single<Instance> {
        return Single.create {
            try {
                val instance = publicMethod.getInstance()
                it.onSuccess(instance.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getSearch(query: String, resolve: Boolean = true): Single<Results> {
        return Single.create {
            try {
                val results = publicMethod.getSearch(query, resolve)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getLocalPublic(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getLocalPublic(range).execute()
        }
    }

    fun getFederatedPublic(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getFederatedPublic(range).execute()
        }
    }

    fun getLocalTag(tag: String, range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getLocalTag(tag, range).execute()
        }
    }

    fun getFederatedTag(tag: String, range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getFederatedTag(tag, range).execute()
        }
    }
}