package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Timelines
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxTimelines(client: MastodonClient) {
    val timelines = Timelines(client)

    fun home(maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): Single<List<Status>> {
        return Single.create {
            try {
                val statuses = timelines.getHome(maxId, sinceId, limit)
                it.onSuccess(statuses)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun public(maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): Single<List<Status>> {
        return Single.create {
            try {
                val statuses = timelines.getPublic(maxId, sinceId, limit)
                it.onSuccess(statuses)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
