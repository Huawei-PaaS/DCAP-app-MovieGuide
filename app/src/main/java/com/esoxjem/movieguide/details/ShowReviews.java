package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.ReviewsWrapper;
import com.esoxjem.movieguide.network.TmdbWebService;

import java.util.List;

import io.reactivex.Observable;
import sapphire.app.SapphireObject;

/**
 * @author arun
 */
public class ShowReviews implements SapphireObject {

    public TmdbWebService tmdbWebService;

    public ShowReviews() {
        this.tmdbWebService = null;
    }

    public ShowReviews(TmdbWebService tmdbWebService ) {
        this.tmdbWebService = tmdbWebService;
    }

    public void setTmdbWebService (TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

//    public Observable<List<Review>> getReviews(final String id) {
//        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
//    }

    public List<Review> getReviews(final String id, TmdbWebService tmdbWebService) {
        Observable<ReviewsWrapper> result = tmdbWebService.reviews(id);
        Observable<List<Review>> observableList = result.map(ReviewsWrapper::getReviews);
        List<List<Review>> myList = observableList.toList().blockingGet();
        return myList.get(0);
    }

    public List<Review> getReviews(final String id) {
        Observable<ReviewsWrapper> result = tmdbWebService.reviews(id);
        Observable<List<Review>> observableList = result.map(ReviewsWrapper::getReviews);
        List<List<Review>> myList = observableList.toList().blockingGet();
        return myList.get(0);
    }

//    public Observable<List<Review>> getReviews(final String id) {
//        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
//    }
//    public Observable<List<Review>> getReviews(final String id, TmdbWebService tmdbWebService) {
//        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
//    }

    public TmdbWebService getTmdbWebService() {
        return this.tmdbWebService;
    }
}
