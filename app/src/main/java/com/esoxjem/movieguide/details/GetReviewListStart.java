package com.esoxjem.movieguide.details;

import sapphire.app.AppEntryPoint;
import sapphire.app.AppObjectNotCreatedException;
import sapphire.common.AppObjectStub;

import static sapphire.runtime.Sapphire.new_;

/**
 * Created by s00432254 on 1/18/2018.
 */

public class GetReviewListStart implements AppEntryPoint {

    @Override
    public AppObjectStub start() throws AppObjectNotCreatedException {
        return (AppObjectStub) new_(GetReviewList.class);
    }
}
