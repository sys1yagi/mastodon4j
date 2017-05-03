package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class AccountsTest {
    @Test
    fun getAccount() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.getAccount(1L).execute()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getAccountWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getAccount(1L).execute()
    }

    @Test
    fun getVerifyCredentials() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.getVerifyCredentials().execute()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getVerifyCredentialsWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getVerifyCredentials().execute()
    }

    // TODO getVerifyCredentialsWith401

    @Test
    fun updateCredential() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.updateCredential("test", "test", "test", "test").execute()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun updateCredentialWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.updateCredential("test", "test", "test", "test").execute()
    }

    @Test
    fun getFollowers() {
        val client = MockClient.mock("accounts.json")
        val accounts = Accounts(client)
        val pageable = accounts.getFollowers(1L).execute()
        val account = pageable.part.first()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getFollowersWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getFollowers(1L).execute()
    }

    @Test
    fun getFollowing() {
        val client = MockClient.mock("accounts.json")
        val accounts = Accounts(client)
        val pageable = accounts.getFollowing(1L).execute()
        val account = pageable.part.first()
        account.acct shouldEqualTo "test@test.com"
        account.displayName shouldEqualTo "test"
        account.userName shouldEqualTo "test"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getFollowingWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getFollowing(1L).execute()
    }

    @Test
    fun getStatuses() {
        val client = MockClient.mock("statuses.json")
        val accounts = Accounts(client)
        val pageable = accounts.getStatuses(1, false).execute()
        val status = pageable.part.first()
        status.id shouldEqualTo 11111L
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getStatusesWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getStatuses(1, false).execute()
    }

    @Test
    fun postFollow() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postFollow(1L).execute()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postFollowWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.postFollow(1L).execute()
    }

    @Test
    fun postUnFollow() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnFollow(1L).execute()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postUnFollowWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.postUnFollow(1L).execute()
    }

    @Test
    fun postBlock() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postBlock(1L).execute()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postBlockWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.postBlock(1L).execute()
    }

    @Test
    fun postUnblock() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnblock(1L).execute()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postUnblockWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.postUnblock(1L).execute()
    }

    @Test
    fun postMute() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postMute(1L).execute()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postMuteWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.postMute(1L).execute()
    }


    @Test
    fun postUnmute() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnmute(1L).execute()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun postUnmuteWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.postUnmute(1L).execute()
    }

    @Test
    fun getRelationships() {
        val client = MockClient.mock("relationships.json")
        val accounts = Accounts(client)
        val relationships = accounts.getRelationships(listOf(1L)).execute()
        val relationship = relationships.first()
        relationship.id shouldEqualTo 3361
        relationship.isFollowing shouldEqualTo true
        relationship.isFollowedBy shouldEqualTo false
        relationship.isBlocking shouldEqualTo false
        relationship.isMuting shouldEqualTo false
        relationship.isRequested shouldEqualTo false
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getRelationshipsWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getRelationships(listOf(1L)).execute()
    }

    @Test
    fun getAccountSearch() {
        val client = MockClient.mock("account_search.json")
        val accounts = Accounts(client)
        val result = accounts.getAccountSearch("test").execute()
        val account = result.first()
        account.acct shouldEqualTo "A"
        account.displayName shouldEqualTo ""
        account.userName shouldEqualTo "A"
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getAccountSearchWithException() {
        val client = MockClient.ioException()
        val accounts = Accounts(client)
        accounts.getAccountSearch("test").execute()
    }
}