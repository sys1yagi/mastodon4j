package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Report
import com.sys1yagi.mastodon4j.api.method.Reports
import io.reactivex.Single

class RxReports(client: MastodonClient) {
    val reports = Reports(client)

    fun getReports(range: Range = Range()): Single<Pageable<Report>> {
        return Single.create {
            try {
                val reports = reports.getReports(range)
                it.onSuccess(reports.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }


    fun postReport(accountId: Long, statusId: Long, comment: String): Single<Report> {
        return Single.create {
            try {
                val report = reports.postReport(accountId, statusId, comment)
                it.onSuccess(report.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}