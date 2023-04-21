package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.testtool.MockClient
import okhttp3.MultipartBody
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.Test

class MediaTest {
    @Test
    fun postMedia() {
        val client = MockClient.mock("attachment.json")
        val media = Media(client)
        val attachment = media.postMedia(MultipartBody.Part.create(emptyRequestBody())).execute()
        attachment.id shouldEqualTo 10
        attachment.type shouldEqualTo "video"
        attachment.url shouldEqualTo "youtube"
        attachment.remoteUrl shouldNotBe null
        attachment.previewUrl shouldEqualTo "preview"
        attachment.textUrl shouldNotBe  null
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postMediaWithException() {
        val client = MockClient.ioException()
        val media = Media(client)
        media.postMedia(MultipartBody.Part.create(emptyRequestBody())).execute()
    }
}
