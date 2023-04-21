package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import kotlin.test.Test
import kotlin.test.Ignore

import kotlin.test.assertFailsWith

import org.junit.Assert.*

class NotificationsTest {
    @Test 
    fun getNotifications() {
        val client = MockClient.mock("notifications.json")
        val notifications = Notifications(client)
        val pageable = notifications.getNotifications().execute()
        val notification = pageable.part.first()
        notification.type shouldBeEqualTo "favourite"
        notification.account shouldNotBe null
        notification.status shouldNotBe null
    }

    @Test 
    fun getNotificationsWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val notifications = Notifications(client)
            notifications.getNotifications().execute()
        }
    }

    @Test 
    fun getNotificationWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val notifications = Notifications(client)
            notifications.getNotification(1L).execute()
        }
    }
}
