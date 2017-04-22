package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#relationship
 */
class Relationship {
    @SerializedName("id")
    val id: Long = 0L

    @SerializedName("following")
    val following: Boolean = false

    @SerializedName("followed_by")
    val followedBy: Boolean = false

    @SerializedName("blocking")
    val blocking: Boolean = false

    @SerializedName("muting")
    val muting: Boolean = false

    @SerializedName("requested")
    val requested: Boolean = false

}
