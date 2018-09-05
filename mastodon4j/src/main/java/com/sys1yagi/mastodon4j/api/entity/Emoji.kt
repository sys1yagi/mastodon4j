package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#emoji
 */
class Emoji(
        @SerializedName("shortcode")
        val shortcode: String = "",

        @SerializedName("static_url")
        val staticUrl: String = "",

        @SerializedName("url")
        val url: String = "",

        @SerializedName("visible_in_picker")
        val visibleInPicker: Boolean = true) {
}
