package com.sys1yagi.mastodon4j.rx.extensions

import io.reactivex.CompletableEmitter

fun CompletableEmitter.onErrorIfNotDisposed(t: Throwable) {
    if (!isDisposed) {
        onError(t)
    }
}
