package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.entity.Instance

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instances
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/instances_controller.rb
 */
interface InstancesContract {
    interface Public {
        fun getInstance(): Instance
    }

    interface AuthRequired {
        // none
    }
}
