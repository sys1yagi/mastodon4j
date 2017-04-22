package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Results
import com.sys1yagi.mastodon4j.api.method.Search
import io.reactivex.Single

class RxSearch(client: MastodonClient) {
    val search = Search(client)
    fun getSearch(query: String, resolve: Boolean = true): Single<Results> {
        return Single.create {
            try {
                val results = search.getSearch(query, resolve)
                it.onSuccess(results)
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}