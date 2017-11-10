package com.yoler.potato.util;

/**
 * Created by Admin on 2017/11/9.
 */

public class Constant {
    private final static String URL_PREFIX_HTTPS = "https://111.205.92.112:18090/grape";
    private final static String URL_PREFIX_HTTP = "http://172.20.25.226:8080/grape";

    public final static String GET_DATE_DIR = URL_PREFIX_HTTP + "/getDateDir";
    public final static String GET_PATIENT_BY_DATE = URL_PREFIX_HTTP + "/getPatientByDate";
    public final static String GET_PATIENT_BY_NAME = URL_PREFIX_HTTP + "/getPatientByName";
    public final static String GET_PATIENT_CONDITION = URL_PREFIX_HTTP + "/getPatientCondition";
}
