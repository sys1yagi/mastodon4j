package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import kotlin.test.Test

class AttachmentTest {
    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val status: Attachment = Gson().fromJson(json, Attachment::class.java)
        status.id shouldBeEqualTo 10L
        status.url shouldBeEqualTo "youtube"
        status.remoteUrl shouldNotBe null
        status.previewUrl shouldBeEqualTo "preview"
        status.textUrl shouldNotBe null
        status.type shouldBeEqualTo Attachment.Type.Video.value
    }
}
