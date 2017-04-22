package com.sys1yagi.mastodon4j.extension

import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody


// gson
inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

// okhttp
fun emptyRequestBody() = RequestBody.create(null, byteArrayOf())