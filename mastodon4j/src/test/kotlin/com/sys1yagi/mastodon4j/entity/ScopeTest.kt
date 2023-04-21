package com.sys1yagi.mastodon4j.entity

import com.sys1yagi.mastodon4j.api.Scope
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ScopeTest {
    @Test
    fun toStringTest() {
        Scope(Scope.Name.READ).toString() shouldBeEqualTo "read"
        Scope(Scope.Name.READ, Scope.Name.WRITE).toString() shouldBeEqualTo "read write"
        Scope(Scope.Name.ALL).toString() shouldBeEqualTo "read write follow"
        Scope().toString() shouldBeEqualTo "read write follow"
    }

    @Test
    fun validateSuccess() {
        Scope().validate()
        Scope(Scope.Name.READ).validate()
        Scope(Scope.Name.READ, Scope.Name.WRITE).validate()
        Scope(Scope.Name.ALL).validate()
    }

    @Test
    fun validateDuplication() {
        assertFailsWith<IllegalArgumentException>{
            Scope(Scope.Name.READ, Scope.Name.READ).validate()
        }
    }
}
