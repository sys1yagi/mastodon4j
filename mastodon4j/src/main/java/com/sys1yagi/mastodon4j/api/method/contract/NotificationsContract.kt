package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notifications
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/notifications_controller.rb
 */
interface NotificationsContract {
    interface Public {
        // none
    }

    interface AuthRequired {
        fun getNotifications(range: Range): List<Notification>
        fun getNotification(id: Long): Notification
        fun clearNotifications()
    }
}
