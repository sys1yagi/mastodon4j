package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.method.Instances
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxInstances(client: MastodonClient) {
    val instances = Instances(client)

    fun getInstance(): Single<Instance> {
        return Single.create {
            try {
                val instance = instances.getInstance()
                it.onSuccess(instance)
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}