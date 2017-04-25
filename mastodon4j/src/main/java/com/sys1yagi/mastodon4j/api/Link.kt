package com.sys1yagi.mastodon4j.api

class Link(
        val linkHeader: String,
        val nextPath: String,
        val prevPath: String,
        val maxId: Long,
        val sinceId: Long
) {
    companion object {
        @JvmStatic
        fun parse(linkHeader: String): Link {
            val links = linkHeader.split(",")
            val nextRel = ".*max_id=([0-9]+).*rel=\"next\"".toRegex()
            val prevRel = ".*since_id=([0-9]+).*rel=\"prev\"".toRegex()
            var nextPath = ""
            var maxId = 0L
            var prevPath = ""
            var sinceId = 0L
            links.forEach {
                nextRel.matchEntire(it)?.let {
                    nextPath = it.value.replace("; rel=\"next\"", "")
                    maxId = it.groupValues.get(1).toLong()
                }

                prevRel.matchEntire(it)?.let {
                    prevPath = it.value.replace("; rel=\"prev\"", "")
                    sinceId = it.groupValues.get(1).toLong()
                }
            }
            return Link(linkHeader, nextPath, prevPath, maxId, sinceId)
        }
    }
}