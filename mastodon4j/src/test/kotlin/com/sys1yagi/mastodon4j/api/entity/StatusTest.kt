package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

class StatusTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("status.json")
        val status: Status = Gson().fromJson(json, Status::class.java)
        status.id shouldBeEqualTo 11111L
        status.visibility shouldBeEqualTo Status.Visibility.Public.value
        status.content shouldBeEqualTo "Test Status"
        val account = status.account
        requireNotNull(account)
        account.let {
            it.id shouldBeEqualTo 14476L
        }
        status.isReblogged shouldBeEqualTo false
    }

    @Test
    fun constructor() {
        val status: Status = Status(id = 123, visibility = Status.Visibility.Private.value)
        status.id shouldBeEqualTo 123L
        status.visibility shouldBeEqualTo Status.Visibility.Private.value
        status.content shouldBeEqualTo ""
    }
}
