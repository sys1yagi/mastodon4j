package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.fromJson
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.IOException

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#apps
 */
class Apps(private val client: MastodonClient) {
    // POST /api/v1/apps
    @JvmOverloads
    fun createApp(clientName: String, redirectUris: String = "urn:ietf:wg:oauth:2.0:oob", scope: Scope = Scope(Scope.Name.ALL), website: String? = null): MastodonRequest<AppRegistration> {
        scope.validate()
        return MastodonRequest(
                {
                    client.post("apps",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    arrayListOf(
                                            "client_name=$clientName",
                                            "scopes=$scope",
                                            "redirect_uris=$redirectUris"
                                    ).apply {
                                        website?.let {
                                            add("website=${it}")
                                        }
                                    }.joinToString(separator = "&")
                            ))
                },
                {
                    client.getSerializer().fromJson(it, AppRegistration::class.java).apply {
                        this.instanceName = client.getInstanceName()
                    }
                }
        )
    }

    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String = "urn:ietf:wg:oauth:2.0:oob"): String {
        val endpoint = "/oauth/authorize"
        val parameters = listOf(
                "client_id=$clientId",
                "redirect_uri=$redirectUri",
                "response_type=code",
                "scope=$scope"
        ).joinToString(separator = "&")
        return "https://${client.getInstanceName()}$endpoint?$parameters"
    }

    // POST /oauth/token
    @JvmOverloads
    fun getAccessToken(
            clientId: String,
            clientSecret: String,
            redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
            code: String,
            grantType: String = "authorization_code"
    ): MastodonRequest<AccessToken> {
        val url = "https://${client.getInstanceName()}/oauth/token"
        val parameters = listOf(
                "client_id=$clientId",
                "client_secret=$clientSecret",
                "redirect_uri=$redirectUri",
                "code=$code",
                "grant_type=$grantType"
        ).joinToString(separator = "&")
        return MastodonRequest(
                {
                    client.postUrl(url,
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters
                            ))
                },
                {
                    client.getSerializer().fromJson(it, AccessToken::class.java)
                }
        )
    }

    // POST /oauth/token
    fun postUserNameAndPassword(
            clientId: String,
            clientSecret: String,
            scope: Scope,
            userName: String,
            password: String
    ): MastodonRequest<AccessToken> {
        val url = "https://${client.getInstanceName()}/oauth/token"
        val parameters = Parameter().apply {
            append("client_id", clientId)
            append("client_secret", clientSecret)
            append("scope", scope.toString())
            append("username", userName)
            append("password", password)
            append("grant_type", "password")
        }.build()

        return MastodonRequest(
                {
                    client.postUrl(url,
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters
                            ))
                },
                {
                    client.getSerializer().fromJson(it, AccessToken::class.java)
                }
        )
    }
}
