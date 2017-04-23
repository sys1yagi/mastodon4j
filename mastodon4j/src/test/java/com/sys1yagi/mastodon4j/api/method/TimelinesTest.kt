package com.sys1yagi.mastodon4j.api.method

import com.google.gson.Gson
import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import okhttp3.*
import org.amshove.kluent.shouldEqualTo
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString

class TimelinesTest {

    fun mockClient(jsonName: String): MastodonClient {
        val client: MastodonClient = mock()
        val response: Response = Response.Builder()
                .code(200)
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        AssetsUtil.readFromAssets(jsonName)
                ))
                .build()
        client.get(anyString(), any()).invoked.thenReturn(response)
        client.getSerializer().invoked.thenReturn(Gson())
        return client
    }

    @Test
    fun public() {
        val client = mockClient("public_timeline.json")
        val timelines = Timelines(client)
        val statuses = timelines.getPublic()
        statuses.size shouldEqualTo 20
    }

    @Test
    fun tag() {
        val client = mockClient("tag.json")
        val timelines = Timelines(client)
        val statuses = timelines.getTag("mastodon")
        statuses.size shouldEqualTo 20
    }
}
