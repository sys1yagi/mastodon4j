package com.sys1yagi.mastodon4j.sample;

import com.google.gson.Gson;
import com.sys1yagi.mastodon4j.MastodonClient;
import com.sys1yagi.mastodon4j.api.Handler;
import com.sys1yagi.mastodon4j.api.entity.Notification;
import com.sys1yagi.mastodon4j.api.entity.Status;
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException;
import com.sys1yagi.mastodon4j.api.method.Streaming;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class StreamPublicTimeline {
    public static void main(String[] args) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        // require authentication even if public streaming
        String accessToken = "";
        MastodonClient client = new MastodonClient("mstdn.jp", httpClient, new Gson(), accessToken);
        Handler handler = new Handler() {
            @Override
            public void onStatus(@NotNull Status status) {
                System.out.println(status.getContent());
            }

            @Override
            public void onNotification(@NotNull Notification notification) {

            }

            @Override
            public void onDelete(long id) {

            }
        };
        Streaming streaming = new Streaming(client, handler);
        try {
            streaming.federatedPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
