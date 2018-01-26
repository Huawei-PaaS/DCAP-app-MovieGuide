package com.esoxjem.movieguide;

import com.esoxjem.movieguide.favorites.DoPrint;
import com.esoxjem.movieguide.network.NetworkModule;
import com.esoxjem.movieguide.details.ShowReviews;
import com.esoxjem.movieguide.network.TmdbWebService;

import java.net.InetSocketAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sapphire.kernel.server.KernelServer;
import sapphire.kernel.server.KernelServerImpl;
import sapphire.oms.OMSServer;
import static sapphire.runtime.Sapphire.*;

public class SapphireAccess
{
    public static OMSServer server;
    public static DoPrint doPrint;
    public static NetworkModule nm;
    public static ShowReviews sr;

    public static ShowReviews setShowReviews(TmdbWebService tmdbWebService) {
        try {
            if (server == null) {
                Registry registry = LocateRegistry.getRegistry(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1]));
                server = (OMSServer) registry.lookup("SapphireOMS");

                KernelServer nodeServer = new KernelServerImpl(new InetSocketAddress(
                        Constants.hostAddress[0], Integer.parseInt(Constants.hostAddress[1])),
                        new InetSocketAddress(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1])));

                sr = (ShowReviews) server.getAppEntryPoint();
                sr.setTmdbWebService(tmdbWebService);
                TmdbWebService verification = sr.getTmdbWebService();
                System.out.println("Verified");
            }
//            newSr = (ShowReviews)new_(ShowReviews.class, tmdbWebService);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sr;
    }

    public static NetworkModule getNetworkModule() {
        if (server == null) {
            try {
                Registry registry = LocateRegistry.getRegistry(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1]));
                server = (OMSServer) registry.lookup("SapphireOMS");

                KernelServer nodeServer = new KernelServerImpl(new InetSocketAddress(
                        Constants.hostAddress[0], Integer.parseInt(Constants.hostAddress[1])),
                        new InetSocketAddress(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1])));

                nm = (NetworkModule) server.getAppEntryPoint();
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return nm;
    }

    public static DoPrint GetDoPrint() {

        if (server == null) {
            try {
                Registry registry = LocateRegistry.getRegistry(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1]));
                server = (OMSServer) registry.lookup("SapphireOMS");

                KernelServer nodeServer = new KernelServerImpl(new InetSocketAddress(
                        Constants.hostAddress[0], Integer.parseInt(Constants.hostAddress[1])),
                        new InetSocketAddress(Constants.omsAddress[0], Integer.parseInt(Constants.omsAddress[1])));

                doPrint = (DoPrint) server.getAppEntryPoint();
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return doPrint;
    }
}
