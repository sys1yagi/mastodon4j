package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class AttachmentTest {
    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val status: Attachment = Gson().fromJson(json, Attachment::class.java)
        status.id shouldEqualTo 10L
        status.url shouldEqualTo "youtube"
        status.remoteUrl shouldEqualTo "remote"
        status.previewUrl shouldEqualTo "preview"
        status.textUrl shouldEqualTo "text"
        status.type shouldEqualTo Attachment.Type.Video.value
    }
}
