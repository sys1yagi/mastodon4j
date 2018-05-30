package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.entity.Trend
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

class Trends(private val client: MastodonClient) {
    // GET /api/v1/trends
    @Throws(Mastodon4jRequestException::class)
    fun getTrends(): MastodonRequest<List<Trend>> {
        return MastodonRequest<List<Trend>>(
                {
                    client.get("trends")
                },
                {
                    client.getSerializer().fromJson(it, Trend::class.java)
                }
        )
    }
}