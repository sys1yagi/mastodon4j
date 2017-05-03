package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.method.Notifications
import io.reactivex.Completable
import io.reactivex.Single

class RxNotifications(client: MastodonClient) {
    val notifications = Notifications(client)

    fun getNotifications(range: Range): Single<Pageable<Notification>> {
        return Single.create {
            try {
                val notifications = notifications.getNotifications(range)
                it.onSuccess(notifications.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getNotification(id: Long): Single<Notification> {
        return Single.create {
            try {
                val notification = notifications.getNotification(id)
                it.onSuccess(notification.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun clearNotifications(): Completable {
        return Completable.create {
            try {
                notifications.clearNotifications()
                it.onComplete()
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}