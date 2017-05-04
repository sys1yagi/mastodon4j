package com.sys1yagi.mastodon4j.sample

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Timelines

object Kotlindon {
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"
    const val ACCESS_TOKEN = "access_token"

    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]
        val client = Authenticator().appRegistrationIfNeeded(instanceName, credentialFilePath)
        listenHome(client)
    }

    fun listenHome(client: MastodonClient) {
        val timelines = Timelines(client)
        var pageable: Pageable<Status>? = null
        while (true) {
            val result = pageable?.let {
                timelines.getHome(it.prevRange(limit = 5)).execute()
            } ?: timelines.getHome(Range(limit = 5)).execute()

            result.part.sortedBy { it.createdAt }.forEach {
                println(it.account?.displayName)
                println(it.content)
                println(it.createdAt)
                println("------------------------")
            }
            if (result.part.isNotEmpty()) {
                pageable = result
            }
            println("wait next load...")
            Thread.sleep(10000)
        }
    }
}
