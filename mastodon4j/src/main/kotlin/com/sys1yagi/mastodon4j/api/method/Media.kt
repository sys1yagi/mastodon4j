package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.entity.Attachment
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import okhttp3.MultipartBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#media
 */
class Media(private val client: MastodonClient) {
    //  POST /api/v1/media
    fun postMedia(file: MultipartBody.Part): MastodonRequest<Attachment> {
        val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(file)
                .build()
        return MastodonRequest<Attachment>(
                {
                    client.post("media", requestBody)
                },
                {
                    client.getSerializer().fromJson(it, Attachment::class.java)
                }
        )
    }
}
