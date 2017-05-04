package com.sys1yagi.mastodon4j.sample

import com.sys1yagi.mastodon4j.api.Handler
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.Streaming


object StreamPublicTimeline {

    @JvmStatic fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]

        // require authentication even if public streaming
        val client = Authenticator().appRegistrationIfNeeded(instanceName, credentialFilePath, true)
        val handler = object : Handler {
            override fun onStatus(status: Status) {
                println(status.content)
            }

            override fun onNotification(notification: Notification) {

            }

            override fun onDelete(id: Long) {

            }
        }
        val streaming = Streaming(client)
        try {
            val shutdownable = streaming.localPublic(handler)
            Thread.sleep(10000L)
            shutdownable.shutdown()
        } catch(e: Mastodon4jRequestException) {
            println("error")
            println(e.response?.code())
            println(e.response?.message())
            println(e.response?.body()?.string())
            return
        }
    }
}
