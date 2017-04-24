package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#apps
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/apps_controller.rb
 */
interface AppsContract {
    interface Public {
        fun createApp(clientName: String, redirectUris: String = "urn:ietf:wg:oauth:2.0:oob", scope: Scope, website: String? = null): AppRegistration
        fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String = "urn:ietf:wg:oauth:2.0:oob"): String
        fun getAccessToken(
                clientId: String,
                clientSecret: String,
                redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
                code: String,
                grantType: String = "authorization_code"): AccessToken
        fun postUserNameAndPassword(
                clientId: String,
                clientSecret: String,
                scope: Scope,
                userName: String,
                password: String
        ): AccessToken
    }

    interface AuthRequired {
        // none
    }
}
