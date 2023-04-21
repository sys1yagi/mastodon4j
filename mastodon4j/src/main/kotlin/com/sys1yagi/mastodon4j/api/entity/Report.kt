package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 */
class Report(
        @SerializedName("id")
        val id: Long = 0L, // The ID of the report
        @SerializedName("action_taken")
        val actionTaken: String = "" //	The action taken in response to the report
) {

}
