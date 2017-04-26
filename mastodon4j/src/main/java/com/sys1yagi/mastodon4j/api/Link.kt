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
        fun parse(linkHeader: String?): Link? {
            return linkHeader?.let {
                val links = it.split(",")
                val nextRel = ".*max_id=([0-9]+).*rel=\"next\"".toRegex()
                val prevRel = ".*since_id=([0-9]+).*rel=\"prev\"".toRegex()
                var nextPath = ""
                var maxId = 0L
                var prevPath = ""
                var sinceId = 0L
                links.forEach {
                    val link = it.trim()
                    nextRel.matchEntire(link)?.let {
                        nextPath = it.value.replace("; rel=\"next\"", "")
                        maxId = it.groupValues.get(1).toLong()
                    }

                    prevRel.matchEntire(link)?.let {
                        prevPath = it.value.replace("; rel=\"prev\"", "")
                        sinceId = it.groupValues.get(1).toLong()
                    }
                }
                Link(it, nextPath, prevPath, maxId, sinceId)
            }
        }
    }
}