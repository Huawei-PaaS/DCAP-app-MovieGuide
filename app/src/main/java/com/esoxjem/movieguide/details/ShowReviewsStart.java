package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.favorites.DoPrint;
import com.esoxjem.movieguide.network.TmdbWebService;

import sapphire.app.AppEntryPoint;
import sapphire.app.AppObjectNotCreatedException;
import sapphire.common.AppObjectStub;

import static sapphire.runtime.Sapphire.new_;

/**
 * Created by s00432254 on 1/18/2018.
 */

public class ShowReviewsStart implements AppEntryPoint {

    @Override
    public AppObjectStub start() throws AppObjectNotCreatedException {
        return (AppObjectStub) new_(ShowReviews.class);
    }

    public AppObjectStub start(TmdbWebService tmdbWebService) throws AppObjectNotCreatedException {
        return (AppObjectStub) new_(ShowReviews.class, tmdbWebService);
    }
}
