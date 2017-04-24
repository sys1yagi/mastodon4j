package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Relationship
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.AccountsContract
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.genericType
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#accounts
 */
class Accounts(val client: MastodonClient) : AccountsContract.Public, AccountsContract.AuthRequired {
    // GET /api/v1/accounts/:id
    override fun getAccount(accountId: Long): Account {
        val response = client.get("accounts/$accountId")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Account::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/verify_credentials
    override fun getVerifyCredentials(): Account {
        val response = client.get("accounts/verify_credentials")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Account::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    /**
     * PATCH /api/v1/accounts/update_credentials
     * display_name: The name to display in the user's profile
     * note: A new biography for the user
     * avatar: A base64 encoded image to display as the user's avatar (e.g. data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAADrCAYAAAA...)
     * header: A base64 encoded image to display as the user's header image (e.g. data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAADrCAYAAAA...)
     */
    override fun updateCredential(displayName: String?, note: String?, avatar: String?, header: String?): Account {
        val parameters = Parameter().apply {
            displayName?.let {
                append("display_name", it)
            }
            note?.let {
                append("note", it)
            }
            avatar?.let {
                append("avatar", it)
            }
            header?.let {
                append("header", it)
            }
        }.build()
        val response = client.patch("accounts/update_credentials",
                RequestBody.create(
                        MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                        parameters
                ))
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Account::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/:id/followers
    override fun getFollowers(accountId: Long, range: Range): List<Account> {
        val response = client.get(
                "accounts/$accountId/followers",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Account>>(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/:id/following
    override fun getFollowing(accountId: Long, range: Range): List<Account> {
        val response = client.get(
                "accounts/$accountId/following",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Account>>(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/:id/statuses
    override fun getStatuses(accountId: Long, range: Range): List<Status> {
        val response = client.get(
                "accounts/$accountId/statuses",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/follow
    override fun postFollow(accountId: Long): Relationship {
        val response = client.post("accounts/$accountId/follow", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Relationship::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/unfollow
    override fun postUnFollow(accountId: Long): Relationship {
        val response = client.post("accounts/$accountId/unfollow", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Relationship::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/block
    override fun postBlock(accountId: Long): Relationship {
        val response = client.post("accounts/$accountId/block", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Relationship::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/unblock
    override fun postUnblock(accountId: Long): Relationship {
        val response = client.post("accounts/$accountId/unblock", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Relationship::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/mute
    override fun postMute(accountId: Long): Relationship {
        val response = client.post("accounts/$accountId/mute", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Relationship::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/unmute
    override fun postUnmute(accountId: Long): Relationship {
        val response = client.post("accounts/$accountId/unmute", emptyRequestBody())
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Relationship::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/relationships
    override fun getRelationships(accountIds: List<Long>): List<Relationship> {
        val response = client.get(
                "accounts/relationships",
                Parameter().append("id", accountIds)
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Relationship>>(
                    body,
                    genericType<List<Relationship>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    // GET /api/v1/accounts/search
    /**
     * q: What to search for
     * limit: Maximum number of matching accounts to return (default: 40)
     */
    override fun getAccountSearch(query: String, limit: Int): List<Account> {
        val response = client.get(
                "accounts/search",
                Parameter()
                        .append("q", query)
                        .append("limit", limit)
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Account>>(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
