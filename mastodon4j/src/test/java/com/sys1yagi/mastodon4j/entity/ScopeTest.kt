package com.sys1yagi.mastodon4j.entity

import com.sys1yagi.mastodon4j.api.Scope
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class ScopeTest {
    @Test
    fun toStringTest() {
        Scope(Scope.Name.READ).toString() shouldEqualTo "read"
        Scope(Scope.Name.READ, Scope.Name.WRITE).toString() shouldEqualTo "read write"
        Scope(Scope.Name.ALL).toString() shouldEqualTo "read write follow"
        Scope().toString() shouldEqualTo "read write follow"
    }

    @Test
    fun validateSuccess() {
        Scope().validate()
        Scope(Scope.Name.READ).validate()
        Scope(Scope.Name.READ, Scope.Name.WRITE).validate()
        Scope(Scope.Name.ALL).validate()
    }

    @Test(expected = IllegalArgumentException::class)
    fun validateDuplication() {
        Scope(Scope.Name.READ, Scope.Name.READ).validate()
    }
}
