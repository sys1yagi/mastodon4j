package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.entity.Results

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#search
 * https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/search_controller.rb
 */
interface SearchContract {
    interface Public {
        fun getSearch(query: String, resolve: Boolean = true): Results
    }

    interface AuthRequired {
        // none
    }
}
