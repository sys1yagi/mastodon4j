package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Report

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/reports_controller.rb
 */
interface ReportsContract {
    interface Public {
        // none
    }

    interface AuthRequired {
        fun getReports(range: Range = Range()): Pageable<Report>
        fun postReport(accountId: Long, statusId: Long, comment: String): Report
    }
}
