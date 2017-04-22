package com.sys1yagi.mastodon4j.extension

import com.google.gson.reflect.TypeToken

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type
