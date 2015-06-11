package com.kejiwen.architecture.constant;

public class FeatureConfig {

    // Global control on debug log
    public static final boolean DEBUG_LOG = true;

    // Switch for online server
    public static final boolean ONLINE_SERVER = true;

    public static final String OFFLINE_SERVER_URL = "http://p.api.tongbaotu.test/";
    public static final String ONLINE_SERVER_URL = "http://api2.tongbaotu.com/";

    public static final String API_HOST_NAME = ONLINE_SERVER ? ONLINE_SERVER_URL : OFFLINE_SERVER_URL;
}
