package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.Handler
import com.sys1yagi.mastodon4j.api.Retryable
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.testtool.MockClient
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class StreamingTest {
    @Test
    fun localPublic() {
        val client = MockClient.mockStreaming("streaming_payload.txt")
        val stream = Streaming(client)
        val atomicInt = AtomicInteger(0)
        runBlocking {
            val disposable = stream.localPublic(object : Handler {
                override fun onStatus(status: Status) {
                    atomicInt.incrementAndGet()
                }

                override fun onNotification(notification: Notification) {
                    // no op
                }

                override fun onDelete(id: Long) {
                    // no op
                }

                override fun onDisconnected(retryable: Retryable) {
                    // no op
                }
            })
            delay(1000L)
            disposable.shutdown()
        }
        atomicInt.get() shouldEqualTo 13
    }

    @Test
    fun localPublicRetry() {
    }
}
