package com.esoxjem.movieguide.details;

import android.os.AsyncTask;

import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.ReviewsWrapper;
import com.esoxjem.movieguide.SapphireAccess;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.VideoWrapper;
import com.esoxjem.movieguide.network.NetworkModule;
import com.esoxjem.movieguide.network.TmdbWebService;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private TmdbWebService tmdbWebService;

    @Override
    public TmdbWebService getTmdbWebService() {
        return tmdbWebService;
    }
    MovieDetailsInteractorImpl(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    @Override
    public Observable<List<Video>> getTrailers(final String id) {
        return tmdbWebService.trailers(id).map(VideoWrapper::getVideos);
    }

    @Override
    public Observable<List<Review>> getReviews(final String id) {
        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
    }


}
