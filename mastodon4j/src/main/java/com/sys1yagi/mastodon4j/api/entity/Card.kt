package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#card
 */
class Card(
        @SerializedName("url")
        val url: String = "",

        @SerializedName("title")
        val title: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("image")
        val image: String? = null) {
}
