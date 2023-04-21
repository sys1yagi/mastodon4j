package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class BlocksTest {
    @Test
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blocks = Blocks(client)
        val pageable = blocks.getBlocks().execute()
        val block = pageable.part.first()
        block.acct shouldEqualTo "test@test.com"
        block.displayName shouldEqualTo "test"
        block.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getBlocksException() {
        val client = MockClient.ioException()

        val blocks = Blocks(client)
        blocks.getBlocks().execute()
    }
}