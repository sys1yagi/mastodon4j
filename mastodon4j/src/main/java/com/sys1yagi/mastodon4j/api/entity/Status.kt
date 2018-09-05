package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#status
 */
class Status(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("uri") val uri: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("account") val account: Account? = null,
        @SerializedName("in_reply_to_id") val inReplyToId: Long? = null,
        @SerializedName("in_reply_to_account_id") val inReplyToAccountId: Long? = null,
        @SerializedName("reblog") val reblog: Status? = null,
        @SerializedName("content") val content: String = "",
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("emojis") val emojis: List<Emoji> = emptyList(),
        @SerializedName("reblogs_count") val reblogsCount: Int = 0,
        @SerializedName("favourites_count") val favouritesCount: Int = 0,
        @SerializedName("reblogged") val isReblogged: Boolean = false,
        @SerializedName("favourited") val isFavourited: Boolean = false,
        @SerializedName("sensitive") val isSensitive: Boolean = false,
        @SerializedName("spoiler_text") val spoilerText: String = "",
        @SerializedName("visibility") val visibility: String = Visibility.Public.value,
        @SerializedName("media_attachments") val mediaAttachments: List<Attachment> = emptyList(),
        @SerializedName("mentions") val mentions: List<Mention> = emptyList(),
        @SerializedName("tags") val tags: List<Tag> = emptyList(),
        @SerializedName("application") val application: Application? = null,
        @SerializedName("language") val language: String? = null,
        @SerializedName("pinned") val pinned: Boolean? = null) {
    enum class Visibility(val value: String) {
        Public("public"),
        Unlisted("unlisted"),
        Private("private"),
        Direct("direct")
    }
}
