package com.sys1yagi.mastodon4j.testtool

import com.google.gson.Gson
import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import okhttp3.*
import org.mockito.ArgumentMatchers

object MockClient {
    fun mock(jsonName: String, maxId: Long? = null, sinceId: Long? = null): MastodonClient {
        val client: MastodonClient = mock()
        val response: Response = Response.Builder()
                .code(200)
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        AssetsUtil.readFromAssets(jsonName)
                ))
                .apply {
                    val linkHeader = arrayListOf<String>().apply {
                        maxId?.let {
                            add("""<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=$it>; rel="next"""")
                        }
                        sinceId?.let {
                            add("""<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=$it>; rel="prev"""")
                        }
                    }.joinToString(separator = ",")
                    if (linkHeader.isNotEmpty()) {
                        header("link", linkHeader)
                    }
                }
                .build()
        client.get(ArgumentMatchers.anyString(), ArgumentMatchers.any()).invoked.thenReturn(response)
        client.getSerializer().invoked.thenReturn(Gson())
        return client
    }
}
