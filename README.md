# mastodon4j

[![](https://jitpack.io/v/sys1yagi/mastodon4j.svg)](https://jitpack.io/#sys1yagi/mastodon4j)
[![wercker status](https://app.wercker.com/status/498944e68f1f37a697fcbab383b0299c/s/master "wercker status")](https://app.wercker.com/project/byKey/498944e68f1f37a697fcbab383b0299c)
[![codecov](https://codecov.io/gh/sys1yagi/mastodon4j/branch/master/graph/badge.svg)](https://codecov.io/gh/sys1yagi/mastodon4j)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-mastodon4j-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5637)

mastodon4j is [mastodon](https://github.com/tootsuite/mastodon) client for Java and Kotlin.

# Official API Doc

https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md

# Sample App

__Android App__

- https://github.com/sys1yagi/DroiDon

# Get Started

Mastodon4j publish in jitpack.
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
compile 'com.github.sys1yagi.mastodon4j:mastodon4j:$version'
compile 'com.github.sys1yagi.mastodon4j:mastodon4j-rx:$version'
```

Check latest version on Jitpack [![](https://jitpack.io/v/sys1yagi/mastodon4j.svg)](https://jitpack.io/#sys1yagi/mastodon4j)

# Usage

## Get Public Timeline

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
        
val timelines = Timelines(client)
val statuses: List<Status> = timelines.getPublic().execute()
```

__java__

```java
MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
Timelines timelines = new Timelines(client);

try {
  List<Status> statuses = timelines.getPublic(new Range()).execute();
  statuses.forEach(status->{
    System.out.println("=============");
    System.out.println(status.getAccount().getDisplayName());
    System.out.println(status.getContent());
  });
} catch (Mastodon4jRequestException e) {
  e.printStackTrace();
}
```

## Register App

If you want to access the auth required API, you need create client credential and get access token. see more [docs](https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#apps)

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
val apps = Apps(client)
val appRegistration = apps.createApp(
	clientName = "client name",
	redirectUris = "urn:ietf:wg:oauth:2.0:oob",
	scope = Scope(Scope.Name.ALL),
	website = "https://sample.com"
).execute()
save(appRegistration) // appRegistration needs to be saved.
```

AppRegistration has client id and client secret.

__java__

```java
MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
Apps apps = new Apps(client);
try {
	AppRegistration registration = apps.createApp(
	    "mastodon4j-sample-app",
	    "urn:ietf:wg:oauth:2.0:oob",
	    new Scope(Scope.Name.ALL),
        "https://sample.com"
    ).execute();
    System.out.println("instance=" + registration.getInstanceName());
    System.out.println("client_id=" + registration.getClientId());
    System.out.println("client_secret=" + registration.getClientSecret());
} catch (Mastodon4jRequestException e) {
	int statusCode = e.getResponse().code();
	// error handling.
}
```

## OAuth login and get Access Token

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
val clientId = appRegistration.clientId
val apps = Apps(client)

val url = apps.getOAuthUrl(clientId, Scope(Scope.Name.ALL))
// url like bellow
// https://:instance_name/oauth/authorize?client_id=:client_id&redirect_uri=:redirect_uri&response_type=code&scope=read 
// open url and OAuth login and get auth code

val authCode = //...
val clientSecret = appRegistration.clientSecret
val redirectUri = appRegistration.redirectUri
val accessToken = apps.getAccessToken(
			clientId,
			clientSecret,
			redirectUri,
			authCode,
			"authorization_code"
		)
// 	accessToken needs to be saved.
```

## Get Home Timeline

__kotlin__

```kotlin
// Need parameter of accessToken
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson())
  .accessToken(accessToken)
  .build()

val statuses: List<Status> = timelines.getHome().execute()
```

## Get raw json

v0.0.7 or later

__kotlin__

```kotlin
val client = //...
val publicMethod = Public(client)

publicMethod.getLocalPublic()
  .doOnJson { jsonString -> 
    // You can get raw json for each element.
    println(jsonString)
  }
  .execute() 
```

## Streaming API

v1.0.0 or later

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson())
  .accessToken(accessToken)
  .useStreamingApi()
  .build()

val handler = object : Handler {
  override fun onStatus(status: Status) {
    println(status.content)
  }
  override fun onNotification(notification: Notification) {/* no op */}
  override fun onDelete(id: Long) {/* no op */}
}

val streaming = Streaming(client)
try {
  val shutdownable = streaming.localPublic(handler)
  Thread.sleep(10000L)
  shutdownable.shutdown()
} catch(e: Mastodon4jRequestException) {
  e.printStackTrace()
}
```

__java__

```java
MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson())
        .accessToken(accessToken)
        .useStreamingApi()
        .build();
Handler handler = new Handler() {
    @Override
    public void onStatus(@NotNull Status status) {
        System.out.println(status.getContent());
    }

    @Override
    public void onNotification(@NotNull Notification notification) {/* no op */}
    @Override
    public void onDelete(long id) {/* no op */}
};

Streaming streaming = new Streaming(client);
try {
    Shutdownable shutdownable = streaming.localPublic(handler);
    Thread.sleep(10000L);
    shutdownable.shutdown();
} catch (Exception e) {
    e.printStackTrace();
}
```


# Versioning

[Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html)

# Implementation Progress

## Methods

- [x] GET `/api/v1/accounts/:id`
- [x] GET `/api/v1/accounts/verify_credentials`
- [x] PATCH `/api/v1/accounts/update_credentials`
- [x] GET `/api/v1/accounts/:id/followers`
- [x] GET `/api/v1/accounts/:id/following`
- [x] GET `/api/v1/accounts/:id/statuses`
- [x] POST `/api/v1/accounts/:id/follow`
- [x] POST `/api/v1/accounts/:id/unfollow`
- [x] POST `/api/v1/accounts/:id/block`
- [x] POST `/api/v1/accounts/:id/unblock`
- [x] POST `/api/v1/accounts/:id/mute`
- [x] POST `/api/v1/accounts/:id/unmute`
- [x] GET `/api/v1/accounts/relationships`
- [x] GET `/api/v1/accounts/search`
- [x] POST `/api/v1/apps`
- [x] GET `/api/v1/blocks`
- [x] GET `/api/v1/favourites`
- [x] GET `/api/v1/follow_requests`
- [x] POST `/api/v1/follow_requests/:id/authorize`
- [x] POST `/api/v1/follow_requests/:id/reject`
- [x] POST `/api/v1/follows`
- [x] GET `/api/v1/instance`
- [x] POST `/api/v1/media`
- [x] GET `/api/v1/mutes`
- [x] GET `/api/v1/notifications`
- [x] GET `/api/v1/notifications/:id`
- [x] POST `/api/v1/notifications/clear`
- [x] GET `/api/v1/reports`
- [x] POST `/api/v1/reports`
- [x] GET `/api/v1/search`
- [x] GET `/api/v1/statuses/:id`
- [x] GET `/api/v1/statuses/:id/context`
- [x] GET `/api/v1/statuses/:id/card`
- [x] GET `/api/v1/statuses/:id/reblogged_by`
- [x] GET `/api/v1/statuses/:id/favourited_by`
- [x] POST `/api/v1/statuses`
- [x] DELETE `/api/v1/statuses/:id`
- [x] POST `/api/v1/statuses/:id/reblog`
- [x] POST `/api/v1/statuses/:id/unreblog`
- [x] POST `/api/v1/statuses/:id/favourite`
- [x] POST `/api/v1/statuses/:id/unfavourite`
- [x] GET `/api/v1/timelines/home`
- [x] GET `/api/v1/timelines/public`
- [x] GET `/api/v1/timelines/tag/:hashtag`

## Streaming

v1.0.0 or later

- [x] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`

## Auth

- [x] Generate Url for OAuth `/oauth/authorize`
- [x] POST password authorize `/oauth/token` v0.0.2 or later
- [x] POST `/oauth/token`

## Rx

v0.0.2 or later

- [x] RxAccounts
- [x] RxApps
- [x] RxBlocks
- [x] RxFavourites
- [x] RxFollowRequests
- [x] RxFollows
- [x] RxInstances
- [x] RxMedia
- [x] RxMutes
- [x] RxNotifications
- [x] RxReports
- [x] RxSearch
- [x] RxStatuses
- [x] RxTimelines

## Rx Streaming

v1.0.0 or later

- [ ] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`

# Contribution

## Reporting Issues

Found a problem? Want a new feature? First of all see if your issue or idea has already been reported. If don't, just open a new clear and descriptive [issues](https://github.com/sys1yagi/mastodon4j/issues)

# License

```
MIT License

Copyright (c) 2017 Toshihiro Yagi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
