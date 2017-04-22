package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class StatusTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("status.json")
        val status:Status = Gson().fromJson(json, Status::class.java)
        assertThat(status.id).isEqualTo(172429L)
        assertThat(status.visibility).isEqualTo(Status.Visibility.Public.value)
        assertThat(status.content).isEqualTo("Test Status")
        val account = status.account
        requireNotNull(account)
        account?.let{
            assertThat(it.id).isEqualTo(14476L)
        }
    }
}
