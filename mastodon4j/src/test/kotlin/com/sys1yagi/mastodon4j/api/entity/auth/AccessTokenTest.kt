package com.sys1yagi.mastodon4j.api.entity.auth

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class AccessTokenTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("access_token.json")
        val accessToken: AccessToken = Gson().fromJson(json, AccessToken::class.java)
        accessToken.accessToken shouldEqualTo "test"
        accessToken.tokenType shouldEqualTo "bearer"
        accessToken.scope shouldEqualTo "read write follow"
        accessToken.createdAt shouldEqualTo 1493188835L
    }

    @Test
    fun constructor() {
        val accessToken: AccessToken = AccessToken(accessToken = "123", scope = "scope")
        accessToken.accessToken shouldEqualTo "123"
        accessToken.tokenType shouldEqualTo ""
        accessToken.scope shouldEqualTo "scope"
    }
}
