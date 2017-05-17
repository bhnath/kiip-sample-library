package com.bhnath.kiipsamplelibrary;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.bhnath.kiipsamplelibrary.dialog.HackerNewsDialog;
import com.bhnath.kiipsamplelibrary.model.HackerNewsItem;
import com.bhnath.kiipsamplelibrary.network.HackerNewsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Entry class for library.
 */
public class HackerNews {

    private static final int MAX_STORIES = 20;

    private boolean isLoadComplete = false;
    private Activity activity;
    private List<HackerNewsItem> list;

    /**
     * Initialize the library by fetching the top 20 hacker news articles.
     * @param context - application context
     */
    public void initSDK(Context context) {
        activity = (Activity) context;
        list = new ArrayList<>();

        Call<int[]> stories = HackerNewsApi.getHackerNewsApiClient().getTopStories();
        stories.enqueue(new Callback<int[]>() {
            @Override
            public void onResponse(Call<int[]> call, Response<int[]> response) {
                int[] items = response.body();
                for (int i = 0; i < MAX_STORIES; i++) {
                    final int j = i;
                    Call<HackerNewsItem> hnItems = HackerNewsApi.getHackerNewsApiClient().getStory(items[i]);
                    hnItems.enqueue(new Callback<HackerNewsItem>() {
                        @Override
                        public void onResponse(Call<HackerNewsItem> call, Response<HackerNewsItem> response) {
                            HackerNewsItem item = response.body();
                            list.add(item);

                            // Load is only complete once all stories have loaded.
                            if (j == MAX_STORIES - 1) {
                                isLoadComplete = true;
                            }
                        }

                        @Override
                        public void onFailure(Call<HackerNewsItem> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<int[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Display dialog once data load is complete.
     */
    public void showHackerNews() {
        if(!isLoadComplete) {
            Toast.makeText(activity, "Connection not ready yet.", Toast.LENGTH_SHORT).show();
        } else {
            HackerNewsDialog dialog = new HackerNewsDialog();
            dialog.setHackerNewsList(list);
            dialog.show(activity.getFragmentManager(), activity.getString(R.string.app_name));
        }
    }
}
