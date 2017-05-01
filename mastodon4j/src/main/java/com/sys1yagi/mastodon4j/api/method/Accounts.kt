package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Relationship
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#accounts
 */
class Accounts(val client: MastodonClient) {
    // GET /api/v1/accounts/:id
    @Throws(Mastodon4jRequestException::class)
    fun getAccount(accountId: Long): Account {
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
    @Throws(Mastodon4jRequestException::class)
    fun getVerifyCredentials(): Account {
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
    @Throws(Mastodon4jRequestException::class)
    fun updateCredential(displayName: String?, note: String?, avatar: String?, header: String?): Account {
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
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getFollowers(accountId: Long, range: Range = Range()): Pageable<Account> {
        val response = client.get(
                "accounts/$accountId/followers",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Account>>(
                    body,
                    genericType<List<Account>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/:id/following
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getFollowing(accountId: Long, range: Range = Range()): Pageable<Account> {
        val response = client.get(
                "accounts/$accountId/following",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Account>>(
                    body,
                    genericType<List<Account>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  GET /api/v1/accounts/:id/statuses
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getStatuses(accountId: Long, onlyMedia: Boolean, range: Range = Range()): Pageable<Status> {
        val parameters = range.toParameter()
        if (onlyMedia) {
            parameters.append("only_media", true)
        }
        val response = client.get(
                "accounts/$accountId/statuses",
                parameters
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/accounts/:id/follow
    @Throws(Mastodon4jRequestException::class)
    fun postFollow(accountId: Long): Relationship {
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
    @Throws(Mastodon4jRequestException::class)
    fun postUnFollow(accountId: Long): Relationship {
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
    @Throws(Mastodon4jRequestException::class)
    fun postBlock(accountId: Long): Relationship {
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
    @Throws(Mastodon4jRequestException::class)
    fun postUnblock(accountId: Long): Relationship {
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
    @Throws(Mastodon4jRequestException::class)
    fun postMute(accountId: Long): Relationship {
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
    @Throws(Mastodon4jRequestException::class)
    fun postUnmute(accountId: Long): Relationship {
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
    @Throws(Mastodon4jRequestException::class)
    fun getRelationships(accountIds: List<Long>): Pageable<Relationship> {
        val response = client.get(
                "accounts/relationships",
                Parameter().append("id", accountIds)
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Relationship>>(
                    body,
                    genericType<List<Relationship>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    // GET /api/v1/accounts/search
    /**
     * q: What to search for
     * limit: Maximum number of matching accounts to return (default: 40)
     */
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getAccountSearch(query: String, limit: Int = 40): Pageable<Account> {
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
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}
