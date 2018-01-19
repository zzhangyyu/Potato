package com.yoler.potato.util;

/**
 * Created by Admin on 2017/11/9.
 */

public class Constant {
    private final static String URL_PREFIX_HTTPS = "https://111.205.92.112:18090/grape";
    private final static String URL_PREFIX_HTTP = "http://172.20.25.226:8080/grape";

    public final static String GET_CONSILIA_DATE_DIR = URL_PREFIX_HTTPS + "/patient/getConsiliaDateDir";
    public final static String GET_CONSILIA_DATE_INTRO = URL_PREFIX_HTTPS + "/patient/getConsiliaDateIntro";

    public final static String GET_CONSILIA_PATIENT_DIR = URL_PREFIX_HTTPS + "/patient/getConsiliaPatientDir";
    public final static String GET_CONSILIA_PATIENT_INTRO = URL_PREFIX_HTTPS + "/patient/getConsiliaPatientIntro";

    public final static String GET_CONSILIA_DETAIL = URL_PREFIX_HTTPS + "/patient/getConsiliaDetail";

    public final static String GET_USER_FAVOURITE_PATIENT= URL_PREFIX_HTTPS + "/user/getUserFavouritePatient";
}