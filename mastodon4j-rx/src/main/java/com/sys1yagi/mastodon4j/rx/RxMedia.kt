package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Attachment
import com.sys1yagi.mastodon4j.api.method.Media
import io.reactivex.Single
import okhttp3.MultipartBody

class RxMedia(client: MastodonClient) {
    val media = Media(client)

    fun postMedia(part: MultipartBody.Part): Single<Attachment> {
        return Single.create {
            try {
                val result = media.postMedia(part)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
