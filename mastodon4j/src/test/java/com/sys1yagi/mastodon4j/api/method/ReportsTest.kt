package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class ReportsTest {
    @Test
    fun getReports() {
        val client = MockClient.mock("reports.json")
        val reports = Reports(client)
        val pageable = reports.getReports().execute()
        val report = pageable.part.first()
        report.id shouldEqualTo 100L
        report.actionTaken shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getReportsWithException() {
        val client = MockClient.ioException()
        val reports = Reports(client)
        reports.getReports().execute()
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postReportWithException() {
        val client = MockClient.ioException()
        val reports = Reports(client)
        reports.postReport(10, 20, "test").execute()
    }
}
