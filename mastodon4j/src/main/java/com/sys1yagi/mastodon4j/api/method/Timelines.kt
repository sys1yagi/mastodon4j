package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Link
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(private val client: MastodonClient) {
    //  GET /api/v1/timelines/home
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getHome(range: Range = Range()): Pageable<Status> {
        val response = client.get(
                "timelines/home",
                range.toParameter()
        )
        if (response.isSuccessful) {
            return response.fromJson<List<Status>>(client.getSerializer(), genericType<List<Status>>())
                    .toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
