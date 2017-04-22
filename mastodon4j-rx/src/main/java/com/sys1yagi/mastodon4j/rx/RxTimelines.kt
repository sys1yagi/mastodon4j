package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Timelines
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxTimelines(client: MastodonClient) {
    val timelines = Timelines(client)

    fun getHome(range: Range = Range()): Single<List<Status>> {
        return Single.create {
            try {
                val statuses = timelines.getHome(range)
                it.onSuccess(statuses)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getPublic(range: Range = Range()): Single<List<Status>> {
        return Single.create {
            try {
                val statuses = timelines.getPublic(range)
                it.onSuccess(statuses)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getTag(tag: String, range: Range = Range()): Single<List<Status>> {
        return Single.create {
            try {
                val statuses = timelines.getTag(tag, range)
                it.onSuccess(statuses)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
