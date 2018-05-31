package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.entity.Tag
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

class Trends(private val client: MastodonClient) {
    // GET /api/v1/trends
    @Throws(Mastodon4jRequestException::class)
    fun getTrends(): MastodonRequest<List<Tag>> {
        return MastodonRequest<List<Tag>>(
                {
                    client.get("trends")
                },
                {
                    client.getSerializer().fromJson(it, Tag::class.java)
                }
        )
    }
}