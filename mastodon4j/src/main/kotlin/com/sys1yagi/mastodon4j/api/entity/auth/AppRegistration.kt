package com.sys1yagi.mastodon4j.api.entity.auth

import com.google.gson.annotations.SerializedName

class AppRegistration(
        @SerializedName("id")
        val id: Long = 0,

        @SerializedName("client_id")
        val clientId: String = "",

        @SerializedName("client_secret")
        val clientSecret: String = "",

        @SerializedName("redirect_uri")
        val redirectUri: String = "",

        var instanceName: String = "") {
}
