package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#poll
 */
class Poll(
        @SerializedName("id")
        val id: Long = 0L,

        @SerializedName("expires_at")
        val expiresAt: String? = null,

        @SerializedName("expired")
        val expired: Boolean = false,

        @SerializedName("multiple")
        val multiple: Boolean = false,

        @SerializedName("votes_count")
        val votesCount: Int = 0,

        @SerializedName("options")
        val options: List<Option> = emptyList(),

        @SerializedName("voted")
        val voted: Boolean? = null) {

        data class Option(
                @SerializedName("title")
                val title: String = "",

                @SerializedName("votes_count")
                val votesCount: Int? = null
        )
}
