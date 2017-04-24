package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Attachment
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.MediaContract
import okhttp3.MultipartBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#media
 */
class Media(val client: MastodonClient): MediaContract.Public, MediaContract.AuthRequired {
    //  POST /api/v1/media
    @Throws(Mastodon4jRequestException::class)
    override fun postMedia(file: MultipartBody.Part): Attachment {
        val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(file)
                .build()

        val response = client.post("media", requestBody)
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Attachment::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
