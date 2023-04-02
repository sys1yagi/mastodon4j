package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

class Account(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("username") val userName: String = "",
        @SerializedName("acct") val acct: String = "",
        @SerializedName("display_name") val displayName: String = "",
        @SerializedName("note") val note: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("avatar") val avatar: String = "",
        @SerializedName("header") val header: String = "",
        @SerializedName("locked") val isLocked: Boolean = false,
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("followers_count") val followersCount: Long = 0,
        @SerializedName("following_count") val followingCount: Long = 0,
        @SerializedName("statuses_count") val statusesCount: Long = 0,
        @SerializedName("emojis") val emojis: List<Emoji> = emptyList()){
}
