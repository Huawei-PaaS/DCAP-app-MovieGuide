package com.esoxjem.movieguide;

import com.esoxjem.movieguide.details.GetReviewList;
import com.google.gson.Gson;

import java.net.InetSocketAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import sapphire.kernel.server.KernelServer;
import sapphire.kernel.server.KernelServerImpl;
import sapphire.oms.OMSServer;

public class SapphireAccess
{
    public static OMSServer server;
    public static GetReviewList lr;

    public static List<Review> getShowReviews(String id) {
        List<Review> reviews = null;

        try {
            if (server == null) {
                Registry registry = LocateRegistry.getRegistry(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1]));
                server = (OMSServer) registry.lookup("SapphireOMS");

                KernelServer nodeServer = new KernelServerImpl(new InetSocketAddress(
                        Constants.hostAddress[0], Integer.parseInt(Constants.hostAddress[1])),
                        new InetSocketAddress(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1])));
            }

            if (lr == null) {
                lr = (GetReviewList) server.getAppEntryPoint();
            }

            String responseStr = lr.GetReviews(id);
            reviews = getReviewListFromGson(responseStr);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return reviews;
    }

    private static List<Review> getReviewListFromGson(String input) {
        List<Review> reviews = null;
        try {
            ReviewsWrapper reviewsWrapper = new Gson().fromJson(input, ReviewsWrapper.class);
            reviews = reviewsWrapper.getReviews();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
