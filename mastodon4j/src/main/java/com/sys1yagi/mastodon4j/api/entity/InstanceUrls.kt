package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instance
 */
data class InstanceUrls(
        @SerializedName("streaming_api")
        val streamingApi: String = "") {
}
