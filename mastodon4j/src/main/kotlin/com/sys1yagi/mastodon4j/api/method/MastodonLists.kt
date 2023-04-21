package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.MastodonList
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

class MastodonLists(private val client: MastodonClient) {

    // GET /api/v1/lists
    fun getLists(): MastodonRequest<Pageable<MastodonList>> {
        return MastodonRequest<Pageable<MastodonList>>(
                {
                    client.get(
                            "lists"
                    )
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        ).toPageable()
    }

    //GET /api/v1/timelines/list/:list_id
    @Throws(Mastodon4jRequestException::class)
    fun getListTimeLine(listID: Long, range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "timelines/list/$listID",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }
}