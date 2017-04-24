package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.TimelinesContract
import com.sys1yagi.mastodon4j.extension.genericType
import javafx.beans.DefaultProperty

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(val client: MastodonClient) : TimelinesContract.Public, TimelinesContract.AuthRequired {

    //  GET /api/v1/timelines/home
    @Throws(Mastodon4jRequestException::class)
    override fun getHome(range: Range): List<Status> {
        val response = client.get(
                "timelines/home",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/timelines/public
    @Throws(Mastodon4jRequestException::class)
    @Deprecated("Use getLocalPublic() or getFederatedPublic() instead")
    override fun getPublic(range: Range): List<Status> {
        val response = client.get(
                "timelines/public",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    private fun getPublic(local: Boolean, range: Range): List<Status> {
        val response = client.get(
                "timelines/public",
                range.toParameter()
                        .append("local", local)
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            println(body)
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    override fun getLocalPublic(range: Range): List<Status> {
        return getPublic(true, range)
    }

    @Throws(Mastodon4jRequestException::class)
    override fun getFederatedPublic(range: Range): List<Status> {
        return getPublic(false, range)
    }

    //  GET /api/v1/timelines/tag/:tag
    @Throws(Mastodon4jRequestException::class)
    override fun getTag(tag: String, range: Range): List<Status> {
        val response = client.get(
                "timelines/tag/$tag",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
