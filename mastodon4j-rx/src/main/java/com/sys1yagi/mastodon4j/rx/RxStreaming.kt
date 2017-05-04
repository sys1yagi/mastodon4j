package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Handler
import com.sys1yagi.mastodon4j.api.Shutdownable
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Streaming
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class RxStreaming(client: MastodonClient) {

    val streaming = Streaming(client)

    private fun stream(f: (Handler) -> Shutdownable): Flowable<Status> {
        return Flowable.create<Status>({ emmiter ->
            val shutdownable = f(object : Handler {
                override fun onStatus(status: Status) {
                    emmiter.onNext(status)
                }

                override fun onNotification(notification: Notification) {
                    // no op
                }

                override fun onDelete(id: Long) {
                    // no op
                }
            })
            emmiter.setCancellable {
                shutdownable.shutdown()
            }
        }, BackpressureStrategy.LATEST)
    }

    private fun statusStream(f: (Handler) -> Shutdownable): Flowable<Status> {
        return stream { handler ->
            f(handler)
        }
    }

    private fun tagStream(tag: String, f: (String, Handler) -> Shutdownable): Flowable<Status> {
        return stream { handler ->
            f(tag, handler)
        }
    }

    fun localPublic(): Flowable<Status> {
        return statusStream(streaming::localPublic)
    }

    fun federatedPublic(): Flowable<Status> {
        return statusStream(streaming::federatedPublic)
    }

    fun localHashtag(tag: String): Flowable<Status> {
        return tagStream(tag, streaming::localHashtag)
    }

    fun federatedHashtag(tag: String): Flowable<Status> {
        return tagStream(tag, streaming::federatedHashtag)
    }

    // TODO user streaming
}
