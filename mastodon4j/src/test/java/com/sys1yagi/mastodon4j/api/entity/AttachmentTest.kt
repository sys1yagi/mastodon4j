package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AttachmentTest {
    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val status: Attachment = Gson().fromJson(json, Attachment::class.java)
        assertThat(status.id).isEqualTo(10L)
        assertThat(status.url).isEqualTo("youtube")
        assertThat(status.remoteUrl).isEqualTo("remote")
        assertThat(status.previewUrl).isEqualTo("preview")
        assertThat(status.textUrl).isEqualTo("text")
        assertThat(status.type).isEqualTo(Attachment.Type.Video.value)
    }
}
