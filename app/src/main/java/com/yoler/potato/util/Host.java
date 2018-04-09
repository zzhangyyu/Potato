package com.yoler.potato.util;

/**
 * Created by Admin on 2017/11/9.
 */

public class Host {
    private final static String URL_PREFIX = "http://www.rzit.top/grape";

    public final static String GET_CONSILIA_DATE_DIR = URL_PREFIX + "/patient/getConsiliaDateDir";
    public final static String GET_CONSILIA_DATE_INTRO = URL_PREFIX + "/patient/getConsiliaDateIntro";

    public final static String GET_CONSILIA_PATIENT_DIR = URL_PREFIX + "/patient/getConsiliaPatientDir";
    public final static String GET_CONSILIA_PATIENT_INTRO = URL_PREFIX + "/patient/getConsiliaPatientIntro";

    public final static String GET_CONSILIA_DETAIL = URL_PREFIX + "/patient/getConsiliaDetail";

    public final static String GET_USER_FAVOURITE_PATIENT= URL_PREFIX + "/user/getUserFavouritePatient";
}