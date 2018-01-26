package com.esoxjem.movieguide.favorites;

import sapphire.app.SapphireObject;
import sapphire.policy.ShiftPolicy;

/**
 * Created by s00432254 on 1/19/2018.
 */

public class DoPrint implements SapphireObject<ShiftPolicy> {

    int cnt = 0;

    public DoPrint() {}

    public DoPrint(int num) { this.cnt = num;}

    public void printCount() {
        System.out.println("Count = " + cnt);
        cnt++;
    }
}
