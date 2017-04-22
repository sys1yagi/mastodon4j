package com.sys1yagi.mastodon4j.entity

import com.sys1yagi.mastodon4j.api.Scope
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test

class ScopeTest {
    @Test
    fun toStringTest() {
        assertThat(Scope(Scope.Name.READ).toString()).isEqualTo("read")
        assertThat(Scope(Scope.Name.READ, Scope.Name.WRITE).toString()).isEqualTo("read write")
        assertThat(Scope(Scope.Name.ALL).toString()).isEqualTo("read write follow")
    }

    @Test
    fun validate() {
        try {
            Scope().validate()
            fail("should throw exception")
        } catch(e: IllegalArgumentException) {
            // success
        }
    }
}
