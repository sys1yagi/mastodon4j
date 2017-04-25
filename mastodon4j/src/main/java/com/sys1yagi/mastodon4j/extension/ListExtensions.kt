package com.sys1yagi.mastodon4j.extension

import com.sys1yagi.mastodon4j.api.Link
import com.sys1yagi.mastodon4j.api.Pageable
import okhttp3.Response

fun <T> List<T>.toPageable(response: Response): Pageable<T> {
    val linkHeader = response.header("link")
    val link = Link.parse(linkHeader)
    return Pageable(this, link)
}
