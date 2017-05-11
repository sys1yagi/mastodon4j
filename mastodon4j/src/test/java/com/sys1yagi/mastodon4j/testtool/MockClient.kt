package com.sys1yagi.mastodon4j.testtool

import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.eq
import com.sys1yagi.kmockito.any
import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import okhttp3.*
import okio.BufferedSource
import okio.Okio
import org.mockito.ArgumentMatchers
import java.net.SocketTimeoutException


object MockClient {

    private fun setResponse(client: MastodonClient, response: Response) {
        client.get(ArgumentMatchers.anyString(), eq(null)).invoked.thenReturn(response)
        client.get(ArgumentMatchers.anyString(), any()).invoked.thenReturn(response)
        client.post(ArgumentMatchers.anyString(), any()).invoked.thenReturn(response)
        client.postUrl(ArgumentMatchers.anyString(), any()).invoked.thenReturn(response)
        client.patch(ArgumentMatchers.anyString(), any()).invoked.thenReturn(response)
        client.getSerializer().invoked.thenReturn(Gson())
    }

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
        setResponse(client, response)
        return client
    }

    fun mockStreaming(fileName: String): MastodonClient {
        val data = AssetsUtil.readFromAssets(fileName)

        val responseBody = object : ResponseBody() {
            override fun contentType(): MediaType {
                return MediaType.parse("text/plain")
            }

            override fun contentLength(): Long {
                return data.length.toLong()
            }

            override fun source(): BufferedSource {
                return Okio.buffer(Okio.source(AssetsUtil.openInputStream(fileName)))
            }
        }

        val client: MastodonClient = mock()
        val response: Response = Response.Builder()
                .code(200)
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(responseBody)
                .build()
        setResponse(client, response)

        return client
    }

    fun ioException(): MastodonClient {
        val client: MastodonClient = mock()
        val source: BufferedSource = mock()
        source.readString(any()).invoked.thenThrow(SocketTimeoutException())
        val response: Response = Response.Builder()
                .code(200)
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        1024,
                        source
                ))
                .build()
        setResponse(client, response)
        return client
    }
}
