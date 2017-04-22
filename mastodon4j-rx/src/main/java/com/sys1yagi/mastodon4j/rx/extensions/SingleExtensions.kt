package com.sys1yagi.mastodon4j.rx.extensions

import io.reactivex.SingleEmitter

fun <T> SingleEmitter<T>.onErrorIfNotDisposed(t: Throwable) {
    if (!isDisposed) {
        onError(t)
    }
}
