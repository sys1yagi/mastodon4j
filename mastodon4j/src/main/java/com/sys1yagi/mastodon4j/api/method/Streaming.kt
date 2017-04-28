package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Dispatcher
import com.sys1yagi.mastodon4j.api.Handler
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.Status
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Streaming(val client: MastodonClient, val handler: Handler) {

    val dispatcher = Dispatcher()
    val lock = ReentrantLock()

    fun federatedPublic() {
        val response = client.get(
                "streaming/public"
        )
        if (response.isSuccessful) {
            val reader = response.body().byteStream().bufferedReader()
            dispatcher.invokeLater(Runnable {
                while (true) {
                    val line = reader.readLine()
                    if (line == null || line.isEmpty()) {
                        continue
                    }
                    val payload = reader.readLine()
                    val event = line.split(":")[1].trim()
                    if (event == "update") {
                        val start = payload.indexOf(":") + 1
                        val json = payload.substring(start).trim()
                        val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                        )
                        handler.onStatus(status)
                    }
                }
            })
        }
    }

    fun localPublic() {
        val response = client.get(
                "streaming/public/local"
        )
        if (response.isSuccessful) {
            val reader = response.body().byteStream().bufferedReader()
            dispatcher.invokeLater(Runnable {
                while (true) {
                    val line = reader.readLine()
                    if (line == null || line.isEmpty()) {
                        continue
                    }
                    val payload = reader.readLine()
                    val event = line.split(":")[1].trim()
                    if (event == "update") {
                        val start = payload.indexOf(":") + 1
                        val json = payload.substring(start).trim()
                        val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                        )
                        handler.onStatus(status)
                    }
                }
            })
        }
    }

    fun federatedHashtag(tag: String) {
        val response = client.get(
                "streaming/hashtag",
                Parameter().append("tag", tag)
        )
        if (response.isSuccessful) {
            val reader = response.body().byteStream().bufferedReader()
            dispatcher.invokeLater(Runnable {
                while (true) {
                    val line = reader.readLine()
                    if (line == null || line.isEmpty()) {
                        continue
                    }
                    val payload = reader.readLine()
                    val event = line.split(":")[1].trim()
                    if (event == "update") {
                        val start = payload.indexOf(":") + 1
                        val json = payload.substring(start).trim()
                        val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                        )
                        handler.onStatus(status)
                    }
                }
            })
        }
    }

    fun localHashtag(tag: String) {
        val response = client.get(
                "streaming/hashtag/local",
                Parameter().append("tag", tag)
        )
        if (response.isSuccessful) {
            val reader = response.body().byteStream().bufferedReader()
            dispatcher.invokeLater(Runnable {
                while (true) {
                    val line = reader.readLine()
                    if (line == null || line.isEmpty()) {
                        continue
                    }
                    val payload = reader.readLine()
                    val event = line.split(":")[1].trim()
                    if (event == "update") {
                        val start = payload.indexOf(":") + 1
                        val json = payload.substring(start).trim()
                        val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                        )
                        handler.onStatus(status)
                    }
                }
            })
        }
    }

    fun user() {
        val response = client.get(
                "streaming/user"
        )
        if (response.isSuccessful) {
            val reader = response.body().byteStream().bufferedReader()
            dispatcher.invokeLater(Runnable {
                while (true) {
                    val line = reader.readLine()
                    if (line == null || line.isEmpty()) {
                        continue
                    }
                    val event = line.split(":")[1].trim()

                    val payload = reader.readLine()
                    val start = payload.indexOf(":") + 1
                    val json = payload.substring(start).trim()
                    if (event == "update") {
                        val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                        )
                        handler.onStatus(status)
                    }
                    if (event == "notification") {
                        val notification = client.getSerializer().fromJson(
                                json,
                                Notification::class.java
                        )
                        handler.onNotification(notification)
                    }
                    if (event == "delete") {
                        val id = client.getSerializer().fromJson(
                                json,
                                Long::class.java
                        )
                        handler.onDelete(id)
                    }
                }
            })
        }
    }

    fun shutdown() {
        lock.withLock {
            dispatcher.shutdown()
        }
    }
}
