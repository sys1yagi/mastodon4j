package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.rx.testtool.MockClient
import org.junit.Test

class RxTimelinesTest {

    @Test
    fun getPublic() {
        val client = MockClient.mock("public_timeline.json", 5L, 40L)
        val publicMethod = RxPublic(client)
        val subscriber = publicMethod.getLocalPublic().test()
        subscriber.assertNoErrors()
    }
}