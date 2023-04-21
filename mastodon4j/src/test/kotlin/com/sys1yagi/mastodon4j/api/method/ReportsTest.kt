package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore
import kotlin.test.assertFailsWith

class ReportsTest {
    @Test 
    fun getReports() {
        val client = MockClient.mock("reports.json")
        val reports = Reports(client)
        val pageable = reports.getReports().execute()
        val report = pageable.part.first()
        report.id shouldBeEqualTo 100L
        report.actionTaken shouldBeEqualTo "test"
    }

    @Test 
    fun getReportsWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val reports = Reports(client)
            reports.getReports().execute()
        }
    }

    @Test 
    fun postReportWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val reports = Reports(client)
            reports.postReport(10, 20, "test").execute()
        }
    }
}
