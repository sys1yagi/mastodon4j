package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Statuses
import io.reactivex.Single

class RxStatuses(client: MastodonClient) {
    val statuses = Statuses(client)

    fun postStatus(
            status: String,
            inReplyToId: Long? = null,
            mediaIds: List<Long>? = null,
            sensitive: Boolean = false,
            spoilerText: String? = null,
            visibility: Status.Visibility = Status.Visibility.Public
    ): Single<Status> {
        return Single.create {
            try {
                val result = statuses.postStatus(status, inReplyToId, mediaIds, sensitive, spoilerText, visibility)
                it.onSuccess(result)
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }


}
