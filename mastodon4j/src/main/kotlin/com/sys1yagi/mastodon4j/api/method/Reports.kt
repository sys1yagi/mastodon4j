package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Report
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 */
class Reports(private val client: MastodonClient) {
    // GET /api/v1/reports
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getReports(range: Range = Range()): MastodonRequest<Pageable<Report>> {
        return MastodonRequest<Pageable<Report>>(
                {
                    client.get(
                            "reports",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Report::class.java)
                }
        ).toPageable()
    }

    /**
     * POST /api/v1/reports
     * account_id: The ID of the account to report
     * status_ids: The IDs of statuses to report (can be an array)
     * comment: A comment to associate with the report.
     */
    @Throws(Mastodon4jRequestException::class)
    fun postReport(accountId: Long, statusId: Long, comment: String): MastodonRequest<Report> {
        val parameters = Parameter().apply {
            append("account_id", accountId)
            append("status_ids", statusId)
            append("comment", comment)
        }.build()
        return MastodonRequest<Report>(
                {
                    client.post("reports",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Report::class.java)
                }
        )
    }
}
