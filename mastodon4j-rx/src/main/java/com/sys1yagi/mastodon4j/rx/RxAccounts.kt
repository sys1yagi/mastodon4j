package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Relationship
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Accounts
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxAccounts(client: MastodonClient) {
    val accounts = Accounts(client)

    fun getAccount(accountId: Long): Single<Account> {
        return Single.create {
            try {
                val account = accounts.getAccount(accountId).execute()
                it.onSuccess(account)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getVerifyCredentials(): Single<Account> {
        return Single.create {
            try {
                val credential = accounts.getVerifyCredentials().execute()
                it.onSuccess(credential)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun updateCredential(displayName: String?, note: String?, avatar: String?, header: String?): Single<Account> {
        return Single.create {
            try {
                val credential = accounts.updateCredential(displayName, note, avatar, header).execute()
                it.onSuccess(credential)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getFollowers(accountId: Long, range: Range): Single<Pageable<Account>> {
        return Single.create {
            try {
                val followers = accounts.getFollowers(accountId, range).execute()
                it.onSuccess(followers)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getFollowing(accountId: Long, range: Range): Single<Pageable<Account>> {
        return Single.create {
            try {
                val following = accounts.getFollowing(accountId, range).execute()
                it.onSuccess(following)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getStatuses(accountId: Long, onlyMedia: Boolean, range: Range): Single<Pageable<Status>> {
        return Single.create {
            try {
                val statuses = accounts.getStatuses(accountId, onlyMedia, range = range).execute()
                it.onSuccess(statuses)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postFollow(accountId: Long): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postFollow(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUnFollow(accountId: Long): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postUnFollow(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postBlock(accountId: Long): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postBlock(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUnblock(accountId: Long): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postUnblock(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postMute(accountId: Long): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postMute(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUnmute(accountId: Long): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postUnmute(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getRelationships(accountIds: List<Long>): Single<List<Relationship>> {
        return Single.create {
            try {
                val relationships = accounts.getRelationships(accountIds).execute()
                it.onSuccess(relationships)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getAccountSearch(query: String, limit: Int = 40): Single<List<Account>> {
        return Single.create {
            try {
                val accounts = accounts.getAccountSearch(query, limit).execute()
                it.onSuccess(accounts)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}