package com.sys1yagi.mastodon4j.api

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/OAuth-details.md
 */
class Scope
@JvmOverloads
constructor(vararg private val scopes: Name = arrayOf(Name.ALL)) {
    enum class Name(val scopeName: String) {
        READ("read"),
        WRITE("write"),
        FOLLOW("follow"),
        ALL(Scope(READ, WRITE, FOLLOW).toString())
    }

    fun validate() {
        if (scopes.size != scopes.distinct().size) {
            throw IllegalArgumentException("There is a duplicate scope. : $this")
        }
    }

    override fun toString(): String {
        return scopes.joinToString(separator = " ", transform = { it.scopeName })
    }
}
