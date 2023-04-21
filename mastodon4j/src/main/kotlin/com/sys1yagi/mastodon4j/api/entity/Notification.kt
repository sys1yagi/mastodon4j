package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notification
 */
class Notification(
        @SerializedName("id")
        val id: Long = 0L, //	The notification ID

        @SerializedName("type")
        val type: String = Type.Mention.value, //	One of: "mention", "reblog", "favourite", "follow"

        @SerializedName("created_at")
        val createdAt: String = "", //	The time the notification was created

        @SerializedName("account")
        val account: Account? = null, //	The Account sending the notification to the user

        @SerializedName("status")
        val status: Status? = null //	The Status associated with the notification, if applicable
) {
    enum class Type(val value: String) {
        Mention("mention"),
        Reblog("reblog"),
        Favourite("favourite"),
        Follow("follow")
    }
}
