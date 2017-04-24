package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.entity.Attachment
import okhttp3.MultipartBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#media
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/media_controller.rb
 */
interface MediaContract {
    interface Public {
        // none
    }

    interface AuthRequired {
        fun postMedia(file: MultipartBody.Part): Attachment
    }
}
