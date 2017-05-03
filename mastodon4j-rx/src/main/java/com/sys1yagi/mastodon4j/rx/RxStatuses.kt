package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Card
import com.sys1yagi.mastodon4j.api.entity.Context
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.Statuses
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import io.reactivex.Completable
import io.reactivex.Single

class RxStatuses(client: MastodonClient) {
    val statuses = Statuses(client)

    fun getStatus(statusId: Long): Single<Status> {
        return Single.create {
            try {
                val status = statuses.getStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getContext(statusId: Long): Single<Context> {
        return Single.create {
            try {
                val context = statuses.getContext(statusId)
                it.onSuccess(context.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getCard(statusId: Long): Single<Card> {
        return Single.create {
            try {
                val context = statuses.getCard(statusId)
                it.onSuccess(context.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getRebloggedBy(statusId: Long, range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = statuses.getRebloggedBy(statusId, range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    //  GET /api/v1/favourited_by
    fun getFavouritedBy(statusId: Long, range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = statuses.getFavouritedBy(statusId, range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postStatus(
            status: String,
            inReplyToId: Long? = null,
            mediaIds: List<Long>? = null,
            sensitive: Boolean = false,
            spoilerText: String? = null,
            visibility: Status.Visibility = Status.Visibility.Public
    ): Single<Status> {
        return Single.create {
            try {
                val result = statuses.postStatus(status, inReplyToId, mediaIds, sensitive, spoilerText, visibility)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun deleteStatus(statusId: Long): Completable {
        return Completable.create {
            try {
                statuses.deleteStatus(statusId)
                it.onComplete()
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postReblog(statusId: Long): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postReblog(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postUnreblog(statusId: Long): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postUnreblog(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postFavourite(statusId: Long): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postFavourite(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postUnfavourite(statusId: Long): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postUnfavourite(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
