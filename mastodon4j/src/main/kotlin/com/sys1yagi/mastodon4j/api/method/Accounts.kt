package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Relationship
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#accounts
 */
class Accounts(private val client: MastodonClient) {
    // GET /api/v1/accounts/:id
    fun getAccount(accountId: Long): MastodonRequest<Account> {
        return MastodonRequest(
                { client.get("accounts/$accountId") },
                { client.getSerializer().fromJson(it, Account::class.java) }
        )
    }

    //  GET /api/v1/accounts/verify_credentials
    fun getVerifyCredentials(): MastodonRequest<Account> {
        return MastodonRequest(
                { client.get("accounts/verify_credentials") },
                { client.getSerializer().fromJson(it, Account::class.java) }
        )
    }

    /**
     * PATCH /api/v1/accounts/update_credentials
     * display_name: The name to display in the user's profile
     * note: A new biography for the user
     * avatar: A base64 encoded image to display as the user's avatar (e.g. data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAADrCAYAAAA...)
     * header: A base64 encoded image to display as the user's header image (e.g. data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAADrCAYAAAA...)
     */
    fun updateCredential(displayName: String?, note: String?, avatar: String?, header: String?): MastodonRequest<Account> {
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
        return MastodonRequest(
                {
                    client.patch("accounts/update_credentials",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        )
    }

    //  GET /api/v1/accounts/:id/followers
    @JvmOverloads
    fun getFollowers(accountId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "accounts/$accountId/followers",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/accounts/:id/following
    @JvmOverloads
    fun getFollowing(accountId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "accounts/$accountId/following",
                            range.toParameter())
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/accounts/:id/statuses
    @JvmOverloads
    fun getStatuses(
            accountId: Long,
            onlyMedia: Boolean = false,
            excludeReplies: Boolean = false,
            pinned: Boolean = false,
            range: Range = Range()
    ): MastodonRequest<Pageable<Status>> {
        val parameters = range.toParameter()
        if (onlyMedia) {
            parameters.append("only_media", true)
        }
        if (pinned) {
            parameters.append("pinned", true)
        }
        if (excludeReplies) {
            parameters.append("exclude_replies", true)
        }
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "accounts/$accountId/statuses",
                            parameters
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    //  POST /api/v1/accounts/:id/follow
    fun postFollow(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest<Relationship>(
                {
                    client.post("accounts/$accountId/follow", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unfollow
    fun postUnFollow(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest<Relationship>(
                {
                    client.post("accounts/$accountId/unfollow", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/block
    fun postBlock(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest<Relationship>(
                {
                    client.post("accounts/$accountId/block", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unblock
    fun postUnblock(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest<Relationship>(
                {
                    client.post("accounts/$accountId/unblock", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/mute
    fun postMute(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest<Relationship>(
                {
                    client.post("accounts/$accountId/mute", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unmute
    fun postUnmute(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest<Relationship>(
                {
                    client.post("accounts/$accountId/unmute", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  GET /api/v1/accounts/relationships
    fun getRelationships(accountIds: List<Long>): MastodonRequest<List<Relationship>> {
        return MastodonRequest(
                {
                    client.get(
                            "accounts/relationships",
                            Parameter().append("id", accountIds)
                    )
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    // GET /api/v1/accounts/search
    /**
     * q: What to search for
     * limit: Maximum number of matching accounts to return (default: 40)
     */
    @JvmOverloads
    fun getAccountSearch(query: String, limit: Int = 40): MastodonRequest<List<Account>> {
        return MastodonRequest(
                {
                    client.get(
                            "accounts/search",
                            Parameter()
                                    .append("q", query)
                                    .append("limit", limit)
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        )
    }
}
