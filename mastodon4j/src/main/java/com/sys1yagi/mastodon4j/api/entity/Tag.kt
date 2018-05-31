package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#tag
 */
class Tag(
        @SerializedName("name")
        val name: String = "",

        @SerializedName("url")
        val url: String = "",

        @SerializedName("history")
        val history: List<History> = emptyList()
) {
                class History(
                        @SerializedName("day")
                        val day: Int = 0,

                        @SerializedName("uses")
                        val uses: Int = 0,

                        @SerializedName("accounts")
                        val accounts: Int = 0
                )
}
