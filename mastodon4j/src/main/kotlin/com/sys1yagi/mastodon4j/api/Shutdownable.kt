package com.sys1yagi.mastodon4j.api

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Shutdownable(private val dispatcher: Dispatcher) {
    private val lock = ReentrantLock()

    fun shutdown() {
        lock.withLock {
            dispatcher.shutdown()
        }
    }
}
