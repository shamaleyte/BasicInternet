package com.example.basicinternet;


import java.util.ArrayList;
import java.util.Arrays;

public class HpsConstants
{
    static public final String APP_IDENTIFIER = "e97fe507";
    static public final String ACCESS_TOKEN = "0b7679a99ac288f1";
    static public final String HASH_ALGORITHM = "SHA-1";
    static public final int HASH_ALGORITHM_DIGEST_LENGTH = 20;
    static public final String ENCODING_STANDARD = "UTF-8";
    static public final String NOTIFICATIONS_CHANNEL = "BasicInternet";
    static public final String NOTIFICATIONS_TITLE = "BasicInternet";
    static public final String LOG_PREFIX = " :: HpsApplication :: ";
    static public final String LOG_BY_SHAMALEYTE = " :: SHAMALEYTE :: ";
    static public final ArrayList<String> STANDARD_HYPE_SERVICES = new ArrayList<>(Arrays.asList(
            "Jobs", "Sports", "News", "Weather", "Music", "Movies"));
    static public final int REQUEST_ACCESS_COARSE_LOCATION_ID = 0;
}
