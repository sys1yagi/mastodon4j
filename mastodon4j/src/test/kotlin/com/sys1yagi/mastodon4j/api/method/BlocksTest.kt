package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore

import kotlin.test.assertFailsWith

class BlocksTest {
    @Test 
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blocks = Blocks(client)
        val pageable = blocks.getBlocks().execute()
        val block = pageable.part.first()
        block.acct shouldBeEqualTo "test@test.com"
        block.displayName shouldBeEqualTo "test"
        block.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getBlocksException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()

            val blocks = Blocks(client)
            blocks.getBlocks().execute()
        }
    }
}
