package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.rx.testtool.MockClient
import org.junit.Assert
import org.junit.Test

class RxTimelinesTest {

    @Test
    fun getPublic() {
        val client = MockClient.mock("public_timeline.json")
        val timelines = RxTimelines(client)
        timelines.getLocalPublic()
                .subscribe(
                        {
                            // success
                        },
                        {
                            Assert.fail()
                        })
    }
}