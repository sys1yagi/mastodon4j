package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instances
 */
class Instances(val client: MastodonClient) {

    //  GET /api/v1/instance
    fun getInstance(): Instance {
        val response = client.get("instance")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Instance::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }
}
