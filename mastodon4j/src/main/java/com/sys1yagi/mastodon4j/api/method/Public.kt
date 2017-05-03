package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.entity.Results
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

class Public(private val client: MastodonClient) {
    /**
     * GET /api/v1/instance
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instances
     */
    @Throws(Mastodon4jRequestException::class)
    fun getInstance(): MastodonRequest<Instance> {
        return MastodonRequest(
                {
                    client.get("instance")
                },
                {
                    client.getSerializer().fromJson(it, Instance::class.java)
                }
        )
    }

    /**
     * GET /api/v1/search
     * q: The search query
     * resolve: Whether to resolve non-local accounts
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#search
     */
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getSearch(query: String, resolve: Boolean = false): Results {
        val response = client.get(
                "search",
                Parameter().apply {
                    append("q", query)
                    if (resolve) {
                        append("resolve", resolve)
                    }
                }
        )

        if (response.isSuccessful) {
            return response.fromJson<Results>(
                    client.getSerializer(),
                    Results::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    /**
     *  GET /api/v1/timelines/public
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
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
            return response.fromJson<List<Status>>(
                    client.getSerializer(),
                    genericType<List<Status>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getLocalPublic(range: Range = Range()) = getPublic(true, range)

    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getFederatedPublic(range: Range = Range()) = getPublic(false, range)

    /**
     * GET /api/v1/timelines/tag/:tag
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
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
            return response.fromJson<List<Status>>(
                    client.getSerializer(),
                    genericType<List<Status>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getLocalTag(tag: String, range: Range = Range()) = getTag(tag, true, range)

    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getFederatedTag(tag: String, range: Range = Range()) = getTag(tag, false, range)
}