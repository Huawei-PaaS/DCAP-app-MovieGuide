package com.esoxjem.movieguide.favorites;

import sapphire.app.SapphireObject;
import sapphire.policy.ShiftPolicy;

import static sapphire.runtime.Sapphire.new_;

/**
 * Created by s00432254 on 1/18/2018.
 */

public class FavoritesStoreManager implements SapphireObject<ShiftPolicy>{
//    private FavoritesStore favoritesStore;
//
//    public FavoritesStoreManager() {
//        this.favoritesStore = (FavoritesStore) new_(FavoritesStore.class);
//    }
//
//    public FavoritesStore getFavoritesStore() {
//        return this.favoritesStore;
//    }

    private DoPrint doPrint;

    public FavoritesStoreManager() {
        this.doPrint = (DoPrint) new_(DoPrint.class);
    }

    public DoPrint getDoPrint() {
        return this.doPrint;
    }

}

