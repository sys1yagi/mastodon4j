package com.sys1yagi.mastodon4j.api.entity.auth

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

class AccessTokenTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("access_token.json")
        val accessToken: AccessToken = Gson().fromJson(json, AccessToken::class.java)
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.createdAt shouldBeEqualTo 1493188835L
    }

    @Test
    fun constructor() {
        val accessToken: AccessToken = AccessToken(accessToken = "123", scope = "scope")
        accessToken.accessToken shouldBeEqualTo "123"
        accessToken.tokenType shouldBeEqualTo ""
        accessToken.scope shouldBeEqualTo "scope"
    }
}
