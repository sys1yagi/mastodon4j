package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.api.method.Apps
import com.sys1yagi.mastodon4j.api.method.Timelines
import com.sys1yagi.mastodon4j.rx.extensions.onErrorIfNotDisposed
import io.reactivex.Single

class RxApps(client: MastodonClient) {
    val apps = Apps(client)

    fun createApp(clientName: String, redirectUris: String = "urn:ietf:wg:oauth:2.0:oob", scope: Scope, website: String? = null): Single<AppRegistration> {
        return Single.create {
            try {
                val registration = apps.createApp(clientName, redirectUris, scope, website)
                it.onSuccess(registration.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getAccessToken(
            clientId: String,
            clientSecret: String,
            redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
            code: String,
            grantType: String = "authorization_code"): Single<AccessToken> {
        return Single.create {
            try {
                val accessToken = apps.getAccessToken(clientId, clientSecret, redirectUri, code, grantType)
                it.onSuccess(accessToken.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUserNameAndPassword(
            clientId: String,
            clientSecret: String,
            scope: Scope,
            userName: String,
            password: String
    ): Single<AccessToken> {
        return Single.create {
            try {
                val accessToken = apps.postUserNameAndPassword(clientId, clientSecret, scope, userName, password)
                it.onSuccess(accessToken.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
