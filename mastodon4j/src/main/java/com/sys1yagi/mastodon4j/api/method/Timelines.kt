package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Link
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.TimelinesContract
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(val client: MastodonClient) : TimelinesContract.Public, TimelinesContract.AuthRequired {

    //  GET /api/v1/timelines/home
    @Throws(Mastodon4jRequestException::class)
    override fun getHome(range: Range): Pageable<Status> {
        val response = client.get(
                "timelines/home",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/timelines/public
    private fun getPublic(local: Boolean, range: Range): Pageable<Status> {
        val parameter = range.toParameter()
        if (local) {
            parameter.append("local", local)
        }
        val response = client.get(
                "timelines/public",
                parameter
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    override fun getLocalPublic(range: Range) = getPublic(true, range)

    @Throws(Mastodon4jRequestException::class)
    override fun getFederatedPublic(range: Range) = getPublic(false, range)

    //  GET /api/v1/timelines/tag/:tag
    private fun getTag(tag: String, local: Boolean, range: Range): Pageable<Status> {
        val parameter = range.toParameter()
        if (local) {
            parameter.append("local", local)
        }
        val response = client.get(
                "timelines/tag/$tag",
                parameter
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    override fun getLocalTag(tag: String, range: Range) = getTag(tag, true, range)

    @Throws(Mastodon4jRequestException::class)
    override fun getFederatedTag(tag: String, range: Range) = getTag(tag, false, range)
}
