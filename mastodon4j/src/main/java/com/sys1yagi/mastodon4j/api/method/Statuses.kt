package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.*
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.StatusesContract
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.genericType
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#statuses
 */
class Statuses(val client: MastodonClient) : StatusesContract.Public, StatusesContract.AuthRequired {

    //  GET /api/v1/statuses/:id
    @Throws(Mastodon4jRequestException::class)
    override fun getStatus(statusId: Long): Status {
        val response = client.get("statuses/$statusId")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/statuses/:id/context
    @Throws(Mastodon4jRequestException::class)
    override fun getContext(statusId: Long): Context {
        val response = client.get("statuses/$statusId/context")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Context::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/statuses/:id/card
    @Throws(Mastodon4jRequestException::class)
    override fun getCard(statusId: Long): Card {
        val response = client.get("statuses/$statusId/card")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Card::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/reblogged_by
    @Throws(Mastodon4jRequestException::class)
    override fun getRebloggedBy(statusId: Long, range: Range): List<Account> {
        val response = client.get(
                "statuses/$statusId/reblogged_by",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/favourited_by
    @Throws(Mastodon4jRequestException::class)
    override fun getFavouritedBy(statusId: Long, range: Range): List<Account> {
        val response = client.get(
                "statuses/$statusId/favourited_by",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    /**
     * POST /api/v1/status
     * status: The text of the status
     * in_reply_to_id (optional): local ID of the status you want to reply to
     * media_ids (optional): array of media IDs to attach to the status (maximum 4)
     * sensitive (optional): set this to mark the media of the status as NSFW
     * spoiler_text (optional): text to be shown as a warning before the actual content
     * visibility (optional): either "direct", "private", "unlisted" or "public"
     */
    @Throws(Mastodon4jRequestException::class)
    override fun postStatus(
            status: String,
            inReplyToId: Long?,
            mediaIds: List<Long>?,
            sensitive: Boolean,
            spoilerText: String?,
            visibility: Status.Visibility
    ): Status {
        val parameters = Parameter().apply {
            append("status", status)
            inReplyToId?.let {
                append("in_reply_to_id", it)
            }
            mediaIds?.let {
                append("media_ids", it)
            }
            append("sensitive", sensitive)
            spoilerText?.let {
                append("spoiler_text", it)
            }
            append("visibility", visibility.value)
        }.build()

        val response = client.post("statuses",
                RequestBody.create(
                        MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                        parameters
                ))
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  DELETE /api/v1/statuses/:id
    @Throws(Mastodon4jRequestException::class)
    override fun deleteStatus(statusId: Long) {
        val response = client.delete("statuses/$statusId")
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/statuses/:id/reblog
    @Throws(Mastodon4jRequestException::class)
    override fun postReblog(statusId: Long): Status {
        val response = client.post("statuses/$statusId/reblog", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/statuses/:id/unreblog
    @Throws(Mastodon4jRequestException::class)
    override fun postUmreblog(statusId: Long): Status {
        val response = client.post("statuses/$statusId/unreblog", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/statuses/:id/favourite
    @Throws(Mastodon4jRequestException::class)
    override fun postFavourite(statusId: Long): Status {
        val response = client.post("statuses/$statusId/favourite", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/statuses/:id/unfavourite
    @Throws(Mastodon4jRequestException::class)
    override fun postUnfavourite(statusId: Long): Status {
        val response = client.post("statuses/$statusId/unfavourite", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
