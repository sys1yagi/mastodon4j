package com.sys1yagi.mastodon4j.api

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/OAuth-details.md
 */
class Scope(vararg private val scopes: Name) {
    enum class Name(val scopeName: String) {
        READ("read"),
        WRITE("write"),
        FOLLOW("follow"),
        ALL(Scope(READ, WRITE, FOLLOW).toString())
    }

    fun validate() {
        if (scopes.isEmpty()) {
            throw IllegalArgumentException("Should set at least one. ex: Scope(Type.READ)")
        }
    }

    override fun toString(): String {
        return scopes.joinToString(separator = " ", transform = { it.scopeName })
    }
}
