package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#relationship
 */
class Relationship(
        @SerializedName("id")
        val id: Long = 0L,

        @SerializedName("following")
        val isFollowing: Boolean = false,

        @SerializedName("followed_by")
        val isFollowedBy: Boolean = false,

        @SerializedName("blocking")
        val isBlocking: Boolean = false,

        @SerializedName("muting")
        val isMuting: Boolean = false,

        @SerializedName("requested")
        val isRequested: Boolean = false) {
}
