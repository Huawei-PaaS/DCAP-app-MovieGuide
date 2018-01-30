package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sapphire.app.SapphireObject;
import sapphire.policy.ShiftPolicy;

/**
 * @author arun
 */
public class GetReviewList implements SapphireObject<ShiftPolicy> {

    public GetReviewList() {}

    public String GetReviews(String id) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://api.themoviedb.org/3/movie/"+ id + "/reviews?api_key=" + BuildConfig.TMDB_API_KEY;

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
