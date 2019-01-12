package com.developer.springOracle3.util.date;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class FDate {

    private static final float PERSIAN_EPOCH = 1948320.5F;
    private static final float GREGORIAN_EPOCH = 1721425.5F;
    private static int e_year;
    private static int e_day;
    private static int e_month;
    private static int f_year;
    private static int f_day;
    private static int f_month;

    private FDate() {
    }

    private static float persian_to_jd(
            int year, int month, int day) {
        int epBase = year - (year >= 0 ? 474 : 473);

        int epYear = 474 + epBase % 2820;

        return day + (month <= 7 ? (month - 1) * 31 : (month - 1) * 30 + 6) + (float) Math.floor((epYear * 682 - 110)
                / 2816) + (epYear - 1) * 365 + (float) Math.floor(epBase / 2820) * 1029983.0F + 1948319.5F;
    }

    private static boolean leap_gregorian(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    private static String jd_to_persian(float jd) {
        jd = (float) Math.floor(jd) + 0.5F;

        float dEpoch = jd - persian_to_jd(475, 1, 1);
        float cycle = (float) Math.floor(dEpoch / 1029983.0F);
        float cYear = dEpoch % 1029983.0F;
        float yCycle;
        if (cYear == 1029982.0F) {
            yCycle = 2820.0F;
        } else {
            float aux1 = (float) Math.floor(cYear / 366.0F);
            float aux2 = cYear % 366.0F;
            yCycle = (float) Math.floor((2134.0F * aux1 + 2816.0F * aux2 + 2815.0F) / 1028522.0F) + aux1 + 1.0F;
        }
        int year = (int) (yCycle + 2820.0F * cycle + 474.0F);
        if (year <= 0) {
            year--;
        }
        float yDay = jd - persian_to_jd(year, 1, 1) + 1.0F;

        int month = yDay <= 186.0F ? (int) Math.ceil(yDay / 31.0F) : (int) Math.ceil(round((yDay - 6.0F) / 30.0F, 4));

        int day = (int) (jd - persian_to_jd(year, month, 1)) + 1;

        f_day = day;
        f_month = month;
        f_year = year;

        return pad(year) + "/" + pad(month) + "/" + pad(day);
    }

    private static String pad(int a) {
        if (a < 10) {
            return "0" + a;
        }
        return String.valueOf(a);
    }

    private static double round(
            double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, 4);
        return bd.doubleValue();
    }

    private static float gregorian_to_jd(
            int year, int month, int day) {
        return (float) (1721424.5F + 365 * (year - 1) + Math.floor((year - 1) / 4) + -1.0D * Math.floor((year - 1) /
                100) + Math.floor((year - 1) / 400) + Math.floor((367 * month - 362) / 12 + (leap_gregorian(year) ?
                -1 : month <= 2 ? 0 : -2) + day));
    }

    private static String jd_to_gregorian(float jd) {
        float wjd = (float) (Math.floor(jd - 0.5D) + 0.5D);
        float dEpoch = wjd - 1721425.5F;
        float quadricent = (float) Math.floor(dEpoch / 146097.0F);
        float dqc = dEpoch % 146097.0F;
        float cent = (float) Math.floor(dqc / 36524.0F);
        float dCent = dqc % 36524.0F;
        float quad = (float) Math.floor(dCent / 1461.0F);
        float dQuad = dCent % 1461.0F;
        float yIndex = (float) Math.floor(dQuad / 365.0F);
        int year = (int) (quadricent * 400.0F + cent * 100.0F + quad * 4.0F + yIndex);
        if ((cent != 4.0F) && (yIndex != 4.0F)) {
            year++;
        }
        float yearDay = wjd - gregorian_to_jd(year, 1, 1);
        float leapAdj = leap_gregorian(year) ? 1 : wjd < gregorian_to_jd(year, 3, 1) ? 0 : 2;

        int month = (int) Math.floor(((yearDay + leapAdj) * 12.0F + 373.0F) / 367.0F);
        int day = (int) (wjd - gregorian_to_jd(year, month, 1)) + 1;
        e_day = day;
        e_month = month;
        e_year = year;

        return year + "/" + month + "/" + day;
    }

    public static Date Farsi_to_georgian(
            int year, int month, int day) {
        float a = persian_to_jd(year, month, day);
        jd_to_gregorian(a);
        Calendar c1 = new GregorianCalendar(e_year, e_month - 1, e_day);
        return c1.getTime();
    }

    public static Date Farsi_to_georgian() {
        return Farsi_to_georgian(f_year, f_month, f_day);
    }

    public static String gregorian_to_Farsi(
            int year, int month, int day) {
        return jd_to_persian(gregorian_to_jd(year, month, day));
    }

    public static String Farsi_from_Georgian(final Date date) {
        if (date == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return jd_to_persian(gregorian_to_jd(c.get(1), c.get(2) + 1, c.get(5)));
    }

    ////////////////////////////////added new to this class///////////////
    public static Date Farsi_to_Gregorian(Date date){
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        return FDate.Farsi_to_georgian(calendar.get(1),calendar.get(2)+1,calendar.get(5));
    }
    public static String Gregorian_to_Farsi(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return FDate.Farsi_from_Georgian(calendar.getTime());

    }

}


/* Location:
 * Qualified Name:     util.FDate
 * JD-Core Version:    0.7.0.1
 */