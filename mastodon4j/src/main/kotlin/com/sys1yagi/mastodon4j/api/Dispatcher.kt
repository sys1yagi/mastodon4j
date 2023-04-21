package com.sys1yagi.mastodon4j.api

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Dispatcher {
    val executorService: ExecutorService = Executors.newFixedThreadPool(1, { r ->
        val thread = Thread(r)
        thread.isDaemon = true
        return@newFixedThreadPool thread
    })

    val lock = ReentrantLock()
    val shutdownTime = 1000L

    fun invokeLater(task: Runnable) = executorService.execute(task)

    fun shutdown() {
        lock.withLock {
            executorService.shutdown()
            if (!executorService.awaitTermination(shutdownTime, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow()
            }
        }
    }
}
