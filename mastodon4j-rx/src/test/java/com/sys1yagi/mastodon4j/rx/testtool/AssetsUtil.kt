package com.sys1yagi.mastodon4j.rx.testtool

import java.io.File

object AssetsUtil {
    fun readFromAssets(path: String): String {
        return File("./src/test/assets/", path)
                .inputStream()
                .bufferedReader()
                .readText()
    }
}
