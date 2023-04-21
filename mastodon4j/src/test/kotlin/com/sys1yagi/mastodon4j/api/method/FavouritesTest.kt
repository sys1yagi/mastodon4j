package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test
import kotlin.test.Ignore

import kotlin.test.assertFailsWith

import org.junit.Assert.*
import java.net.SocketTimeoutException

class FavouritesTest {
    @Test 
    fun getFavourites() {
        val client = MockClient.mock("favourites.json")

        val favorites = Favourites(client)
        val pageable = favorites.getFavourites().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo 1111
    }

    @Test 
    fun exception() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()

            val favorites = Favourites(client)
            favorites.getFavourites().execute()
        }
    }
}
