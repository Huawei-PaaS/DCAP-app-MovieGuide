package com.esoxjem.movieguide.details;

import android.os.AsyncTask;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.SapphireAccess;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.util.RxUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author arun
 */
class MovieDetailsPresenterImpl implements MovieDetailsPresenter {
    private MovieDetailsView view;
    private MovieDetailsInteractor movieDetailsInteractor;
    private FavoritesInteractor favoritesInteractor;
    private Disposable trailersSubscription;
    private Disposable reviewSubscription;

    MovieDetailsPresenterImpl(MovieDetailsInteractor movieDetailsInteractor, FavoritesInteractor favoritesInteractor) {
        this.movieDetailsInteractor = movieDetailsInteractor;
        this.favoritesInteractor = favoritesInteractor;
    }

    @Override
    public void setView(MovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(trailersSubscription, reviewSubscription);
    }

    @Override
    public void showDetails(Movie movie) {
        if (isViewAttached()) {
            view.showDetails(movie);
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void showTrailers(Movie movie) {
        trailersSubscription = movieDetailsInteractor.getTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTrailersSuccess, t -> onGetTrailersFailure());
    }

    private void onGetTrailersSuccess(List<Video> videos) {
        if (isViewAttached()) {
            view.showTrailers(videos);
        }
    }

    private void onGetTrailersFailure() {
        // Do nothing
    }


    @Override
    public void showReviews(Movie movie) {
        try {
            List<Review> reviews = new GetReviews(movie.getId()).execute().get();

            onGetReviewsSuccess(reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetReviews extends AsyncTask<Void, Void, List<Review>> {
        private String id = null;

        private GetReviews(String id) {
            this.id = id;
        }
        protected List<Review> doInBackground(Void... params) {
            List<Review> reviews = SapphireAccess.getShowReviews(this.id);
            return reviews;
        }
    }

    private void onGetReviewsSuccess(List<Review> reviews) {
        if (isViewAttached()) {
            view.showReviews(reviews);
        }
    }

    private void onGetReviewsFailure() {
        // Do nothing
    }

    @Override
    public void showFavoriteButton(Movie movie) {
        boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
        if (isViewAttached()) {
            if (isFavorite) {
                view.showFavorited();
            } else {
                view.showUnFavorited();
            }
        }
    }

    @Override
    public void onFavoriteClick(Movie movie) {
        if (isViewAttached()) {
            boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
            if (isFavorite) {
                favoritesInteractor.unFavorite(movie.getId());
                view.showUnFavorited();
            } else {
                favoritesInteractor.setFavorite(movie);
                view.showFavorited();
            }
        }
    }
}
