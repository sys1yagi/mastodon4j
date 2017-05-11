package com.sys1yagi.mastodon4j.testtool

import java.io.File
import java.io.InputStream

object AssetsUtil {
    fun readFromAssets(path: String): String {
        return File("./src/test/assets/", path)
                .inputStream()
                .bufferedReader()
                .readText()
    }

    fun openInputStream(path: String): InputStream {
        return File("./src/test/assets/", path)
                .inputStream()
    }
}
