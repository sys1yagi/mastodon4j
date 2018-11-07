package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Emoji
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.entity.Results
import com.sys1yagi.mastodon4j.api.entity.Status

class Public(private val client: MastodonClient) {
    /**
     * GET /api/v1/instance
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instances
     */
    fun getInstance(): MastodonRequest<Instance> {
        return MastodonRequest(
                {
                    client.get("instance")
                },
                { json ->
                    client.getSerializer().fromJson(json, Instance::class.java)
                }
        )
    }

    /**
     * GET /api/v1/custom_emojis
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#getting-current-instances-custom-emojis
     */
    fun getCustomEmojis(): MastodonRequest<List<Emoji>> {
        return MastodonRequest(
                {
                    client.get("custom_emojis")
                },
                { json ->
                    client.getSerializer().fromJson(json, Emoji::class.java)
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
    fun getSearch(query: String, resolve: Boolean = false): MastodonRequest<Results> {
        return MastodonRequest<Results>(
                {
                    client.get(
                            "search",
                            Parameter().apply {
                                append("q", query)
                                if (resolve) {
                                    append("resolve", resolve)
                                }
                            }
                    )
                },
                {
                    client.getSerializer().fromJson(it, Results::class.java)
                }
        )
    }

    /**
     *  GET /api/v1/timelines/public
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
    private fun getPublic(local: Boolean, range: Range, only_media: Boolean): MastodonRequest<Pageable<Status>> {
        val parameter = range.toParameter()
        if (local) {
            parameter.append("local", local)
        }
        if (only_media) {
            parameter.append("only_media", true)
        }
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get("timelines/public", parameter)
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    @JvmOverloads
    fun getLocalPublic(range: Range = Range(), only_media: Boolean = false) = getPublic(true, range, only_media)

    @JvmOverloads
    fun getFederatedPublic(range: Range = Range(), only_media: Boolean = false) = getPublic(false, range, only_media)

    /**
     * GET /api/v1/timelines/tag/:tag
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
    private fun getTag(tag: String, local: Boolean, range: Range): MastodonRequest<Pageable<Status>> {
        val parameter = range.toParameter()
        if (local) {
            parameter.append("local", local)
        }
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "timelines/tag/$tag",
                            parameter
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    @JvmOverloads
    fun getLocalTag(tag: String, range: Range = Range()) = getTag(tag, true, range)

    @JvmOverloads
    fun getFederatedTag(tag: String, range: Range = Range()) = getTag(tag, false, range)
}