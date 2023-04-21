package com.sys1yagi.mastodon4j.testtool

import com.google.gson.Gson
// import com.sys1yagi.kmockito.any
import com.sys1yagi.kmockito.any

import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import okhttp3.*
import okio.BufferedSource
import org.mockito.ArgumentMatchers
import java.net.SocketTimeoutException

/**
 * Deprecated testtool.
 *
 * This Mock object, MockClient, is now obsolete. It relied on the packages from 
 * com.sys1yagi.kmockito. This package has been deprecated and can be replaced by 
 * package org.mockito.kotlin (mockito-kotlin).
 *  
 * All of the tests that used it have now been marked with .
 *
 * The fault is shown in the XML logs. This can be seen by removing the  annotation from the first of the
 * Test cases in AccountsTest.
 * lib/build/test-results/test/TEST-com.sys1yagi.mastodon4j.api.method.AccountsTest.xml
 *
 * The offending invocation is the setResponse(client, response). It is the first time that client (the mock object)
 * is being used, so its constructor is invoked then.
 *
 * The issue is that Mockito no longer has the method, the client call
 * https://github.com/sys1yagi/kmockito/blob/master/src/main/kotlin/com/sys1yagi/kmockito/mockito_extensions.kt
 * The last sighting
 * https://javadoc.io/static/org.mockito/mockito-core/2.0.1-beta/org/mockito/internal/creation/MockSettingsImpl.html
 *
 * Not much use is made of the mock object. It's constructed and any() and invoked.
 *
 * The any() call appears to be a direct call to Mockito.
 * inline fun <reified T : Any> any() = Mockito.any(T::class.java) ?: instance(T::class)
 * And it should be the kotlin implementation.
 *
 * @note
 * The line numbering will be wrong because I have added these comments.
 * failure message="java.lang.NoSuchMethodError: 'org.mockito.mock.MockCreationSettings org.mockito.internal.creation.MockSettings
 * Impl.confirm(java.lang.Class)'" type="java.lang.NoSuchMethodError">java.lang.NoSuchMethodError:
 * 'org.mockito.mock.MockCreationSettings org.mockito.internal.creation.MockSettingsImpl.confirm(java.lang.Class)'
 *         at com.sys1yagi.kmockito.Mockito_extensionsKt.defaultMock(mockito_extensions.kt:68)
 *         at com.sys1yagi.kmockito.Mockito_extensionsKt.instance(mockito_extensions.kt:60)
 *         at com.sys1yagi.mastodon4j.testtool.MockClient.setResponse(MockClient.kt:84)
 *
 */
object MockClient {

    fun <T> eq(obj: T): T = org.mockito.kotlin.eq<T>(obj)
    inline fun <reified T : Any> any() = org.mockito.kotlin.any<T>()

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
