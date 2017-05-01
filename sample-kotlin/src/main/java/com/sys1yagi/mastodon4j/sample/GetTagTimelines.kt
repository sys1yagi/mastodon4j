package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.Public
import com.sys1yagi.mastodon4j.api.method.Timelines
import okhttp3.OkHttpClient

object GetTagTimelines {
    fun main(args: Array<String>) {
        val client = MastodonClient("mstdn.jp", OkHttpClient(), Gson())
        val publicMethods = Public(client)

        try {
            val statuses = publicMethods.getFederatedTag("mastodon", Range())
            statuses.part.forEach({ status ->
                System.out.println("=============")
                System.out.println(status.account?.displayName)
                System.out.println(status.content)
                System.out.println(status.isReblogged)
            })
        } catch (e: Mastodon4jRequestException) {
            e.printStackTrace()
        }
    }
}
