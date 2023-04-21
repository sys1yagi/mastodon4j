package com.sys1yagi.mastodon4j.api.entity.auth

import com.google.gson.annotations.SerializedName


class AccessToken(
        @SerializedName("access_token")
        var accessToken: String = "",

        @SerializedName("token_type")
        var tokenType: String = "",

        @SerializedName("scope")
        var scope: String = "",

        @SerializedName("created_at")
        var createdAt: Long = 0L) {
}
