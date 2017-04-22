package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instance
 */
class Instance {
    @SerializedName("uri")
    val uri: String = ""

    @SerializedName("title")
    val title: String = ""

    @SerializedName("description")
    val description: String = ""

    @SerializedName("email")
    val email: String = ""
}
