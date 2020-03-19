package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#field
 */
class Field(
        @SerializedName("name")
        val name: String = "",

        @SerializedName("value")
        val value: String = "",

        @SerializedName("verified_at")
        val verified_at: String = "") {
}
