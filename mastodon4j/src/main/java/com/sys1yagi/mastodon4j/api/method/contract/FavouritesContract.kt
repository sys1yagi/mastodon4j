package com.sys1yagi.mastodon4j.api.method.contract

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#favourites
 * api implementation: https://github.com/tootsuite/mastodon/blob/master/app/controllers/api/v1/favourites_controller.rb
 */
interface FavouritesContract{
    interface Public{
        // none
    }

    interface AuthRequired{
        fun getFavourites(range: Range = Range()): Pageable<Status>
    }
}
