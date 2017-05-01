package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

import org.junit.Assert.*

class BlocksTest {
    @Test
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blocks = Blocks(client)
        val pageable = blocks.getBlocks()
        val block = pageable.part.first()
        block.acct shouldEqualTo "test@test.com"
        block.displayName shouldEqualTo "test"
        block.userName shouldEqualTo "test"
    }
}