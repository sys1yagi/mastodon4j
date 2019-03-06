package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Poll
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://docs.joinmastodon.org/api/rest/polls/
 */
class Polls(private val client: MastodonClient) {

    //  GET /api/v1/polls/:id
    @Throws(Mastodon4jRequestException::class)
    fun getPoll(pollId: Long): MastodonRequest<Poll> {
        return MastodonRequest<Poll>(
                {
                    client.get("polls/$pollId")
                },
                {
                    client.getSerializer().fromJson(it, Poll::class.java)
                }
        )
    }

    // POST /api/v1/polls/:id/votes
    @Throws(Mastodon4jRequestException::class)
    fun postVote(pollId: Long, choices: List<Int>): MastodonRequest<Poll> {
        val parameters = Parameter().apply {
            append("choices", choices)
        }.build()
        return MastodonRequest<Poll>(
                {
                    client.post("polls/$pollId/votes",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Poll::class.java)
                }
        )
    }
}
