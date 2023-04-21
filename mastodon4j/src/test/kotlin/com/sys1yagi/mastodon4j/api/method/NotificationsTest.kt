package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.Test

import org.junit.Assert.*

class NotificationsTest {
    @Test
    fun getNotifications() {
        val client = MockClient.mock("notifications.json")
        val notifications = Notifications(client)
        val pageable = notifications.getNotifications().execute()
        val notification = pageable.part.first()
        notification.type shouldEqualTo "favourite"
        notification.account shouldNotBe null
        notification.status shouldNotBe null
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getNotificationsWithException() {
        val client = MockClient.ioException()
        val notifications = Notifications(client)
        notifications.getNotifications().execute()
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getNotificationWithException() {
        val client = MockClient.ioException()
        val notifications = Notifications(client)
        notifications.getNotification(1L).execute()
    }
}
