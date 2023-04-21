package com.sys1yagi.mastodon4j.testtool

object AssetsUtil {
    fun readFromAssets(path: String): String {
        return this.javaClass.getResource("/" + path).readText()
    }
}
