package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertFailsWith

/**
 * This exercises the Accounts class.
 *
 * The Accounts class is usually an active one. That means, it would ordinarily get a response from the remote
 * server: it would fetch the accounts information from a Mastodon server.
 *
 * For testing, a number of responses from Mastodon servers and stored
 * them as JSON files in test/resources.
 *
 * The procedure with these Mock tests is to load a mack MastodonClient with the JSON
 * file's contents rather than make a request. And then pass that MockClient instance to the Accounts object.
 *
 * The getAccount method is invoked. The getAccount method does not access a remote
 * server, because it already has a response in it (from the JSON file) and the Mock object returns that as an
 * object when execute is invoked on it.
 *
 * It is then possible to use the org.amshove.kluent methods to verify the results are as expected.
 *
 * The advantages of Mock-testing can be seen here. It is possible to validate the operation of the methods of
 * the Accounts object without making the remote calls.
 */

class AccountsTest {
    // @Test 
    @Test
    fun getAccount() {
        // Fake a call using the MockClient (for the MastodonClient) that is pre-loaded with a JSON file.
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.getAccount(1L).execute()

        // Check the results are as given in the JSON file.
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getAccountWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getAccount(1L).execute()
        }
    }

    @Test 
    fun getVerifyCredentials() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.getVerifyCredentials().execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getVerifyCredentialsWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getVerifyCredentials().execute()
        }
    }

    // TODO getVerifyCredentialsWith401

    @Test 
    fun updateCredential() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.updateCredential("test", "test", "test", "test").execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun updateCredentialWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.updateCredential("test", "test", "test", "test").execute()
        }
    }

    @Test 
    fun getFollowers() {
        val client = MockClient.mock("accounts.json")
        val accounts = Accounts(client)
        val pageable = accounts.getFollowers(1L).execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getFollowersWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getFollowers(1L).execute()
        }
    }

    @Test 
    fun getFollowing() {
        val client = MockClient.mock("accounts.json")
        val accounts = Accounts(client)
        val pageable = accounts.getFollowing(1L).execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test 
    fun getFollowingWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getFollowing(1L).execute()
        }
    }

    @Test 
    fun getStatuses() {
        val client = MockClient.mock("statuses.json")
        val accounts = Accounts(client)
        val pageable = accounts.getStatuses(1, false).execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo 11111L
    }

    @Test 
    fun getStatusesWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getStatuses(1, false).execute()
        }
    }

    @Test 
    fun postFollow() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postFollow(1L).execute()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }


    @Test 
    fun postFollowWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postFollow(1L).execute()
        }
    }

    @Test 
    fun postUnFollow() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnFollow(1L).execute()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test 
    fun postUnFollowWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postUnFollow(1L).execute()
        }
    }

    @Test 
    fun postBlock() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postBlock(1L).execute()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test 
    fun postBlockWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postBlock(1L).execute()
        }
    }

    @Test 
    fun postUnblock() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnblock(1L).execute()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test 
    fun postUnblockWithException() {
        assertFailsWith<Mastodon4jRequestException>{

            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postUnblock(1L).execute()
        }
    }

    @Test 
    fun postMute() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postMute(1L).execute()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test 
    fun postMuteWithException() {
        assertFailsWith<Mastodon4jRequestException>{

            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postMute(1L).execute()
        }
    }


    @Test 
    fun postUnmute() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnmute(1L).execute()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test 
    fun postUnmuteWithException() {
        assertFailsWith<Mastodon4jRequestException>{
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postUnmute(1L).execute()
        }
    }

    @Test 
    fun getRelationships() {
        val client = MockClient.mock("relationships.json")
        val accounts = Accounts(client)
        val relationships = accounts.getRelationships(listOf(1L)).execute()
        val relationship = relationships.first()
        relationship.id shouldBeEqualTo 3361
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test 
    fun getRelationshipsWithException() {
        assertFailsWith<Mastodon4jRequestException>{

            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getRelationships(listOf(1L)).execute()
        }
    }

    @Test 
    fun getAccountSearch() {
        val client = MockClient.mock("account_search.json")
        val accounts = Accounts(client)
        val result = accounts.getAccountSearch("test").execute()
        val account = result.first()
        account.acct shouldBeEqualTo "A"
        account.displayName shouldBeEqualTo ""
        account.userName shouldBeEqualTo "A"
    }

    @Test 
    fun getAccountSearchWithException() {
        assertFailsWith<Mastodon4jRequestException>{

            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getAccountSearch("test").execute()
        }
    }
}
