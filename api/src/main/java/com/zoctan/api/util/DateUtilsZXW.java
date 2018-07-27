/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.zoctan.api.util;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by zxw on 2018/7/11 0011.
 * 个人用工具类
 */
public class DateUtilsZXW {
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_Time_FORMAT_WITHOUT_SYMBOL = "yyyyMMddHHmmss";
    private static final String DATE_FORMAT_HMS = "HH:mm:ss";
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 2010/12/01
     */
    public static String FORMAT_SHORT = "yyyy/MM/dd";
    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


    public static String getCurrentTime() {
        return new DateTime().toString(DATE_FORMAT_DEFAULT);
    }

    public static String getFormat(String format) {
        return new DateTime().toString(format);
    }

    public static String getTime() {
        return new DateTime().toString(DATE_FORMAT_HMS);
    }

    public static String getCurrentDay() {
        return new DateTime().toString(DATE_FORMAT_YMD);
    }

    //返回当前年月日
    public static String getNowStringDate() {
        Date date = new Date();
        return new SimpleDateFormat("yyyy年MM月dd日").format(date);
    }

    //返回当前年月日
    public static Date getNowDateDate() {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy年MM月dd日").parse(getNowStringDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    //返回当前年月日
    public static int getNowHour() {
        Date date = new Date();
        return Integer.parseInt(new SimpleDateFormat("hh").format(date));
    }

    //返回当前年月日
    public static int getNowHour(Date date) {
        return Integer.parseInt(new SimpleDateFormat("hh").format(date));
    }

    //返回当前年份
    public static int getYear() {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public static int getMonth() {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    //判断闰年
    public static boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    //返回某月天数
    public static int getDaysByYearAndMonth(int year, int month) {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }

    // 获取本月天数
    public static int getThisMonthDays() {
        Calendar calendar = Calendar.getInstance();
        return getDaysByYearAndMonth(getYear(), getMonth());
    }

    //返回当月星期天数
    public static int getThisMonthSundays(int year, int month) {
        int sundays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar setDate = Calendar.getInstance();
        //从第一天开始
        int day;
        for (day = 1; day <= getDaysByYearAndMonth(year, month); day++) {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日")) {
                sundays++;
            }
        }
        return sundays;
    }

    public static Date AddDaysAfterNow(int days) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
//        String start = format.format(c.getTime());
//        System.out.println(start);

        return c.getTime();
    }

    public static Date getDateByDayNumber(int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return c.getTime();
    }

    public static Date getMinAfterNow(int MinNumber) {
        long currentTime = System.currentTimeMillis();
//        2、在这个基础上加上20分钟：
        currentTime += MinNumber * 60 * 1000;
//        3、格式化时间，获取到的就是当前时间半个小时之后的时间
        return new Date(currentTime);
    }

    public static boolean check(Date date) {
        return date.getTime() - new Date().getTime() > 20 * 60 * 1000;
    }

    public static String getClassType() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(weekNumber);
        String class_type = "";
        if (weekNumber != 7 && weekNumber != 1) {           // 周日为1周六为7。。。。。。
            if (8 <= hour && hour < 16) {       // 工作日白天班
                class_type = "A";
            } else {
                class_type = "B";       // 工作日晚班
            }
        } else {
            class_type = "C";           // 24小时班
        }
        return class_type;
    }

    // 将2018-1-22类型的数据改为2018-01-22
    public static String format(String date) {
        String[] temp = date.split("-");
        String returnDate = "";
        for (int i = 1; i < temp.length; i++) {
            if (Integer.parseInt(temp[i]) < 10) {
                temp[i] = "-0" + temp[i];
            } else {
                temp[i] = "-" + temp[i];
            }
        }
        for (int j = 0; j < temp.length; j++) {
            returnDate += temp[j];
        }
        return returnDate;
    }

    /**
     * 获取今年剩余的秒数
     *
     * @return 秒数
     */
    public static int splitDateAndTime() {
        DateTime start = new DateTime();
        DateTime end =
                new DateTime().dayOfYear().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return Seconds.secondsBetween(start, end).getSeconds();
    }


    public static boolean isValidDate(String str) {
        // String str = "2018/07/13";
        DateFormat formatter = new SimpleDateFormat(FORMAT_SHORT);
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidTime(String str) {
        // String str = "13:01:03";
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_HMS);
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDateTime(String str) {
        // String str = "13:01:03";
        DateFormat formatter = new SimpleDateFormat(DATE_Time_FORMAT_WITHOUT_SYMBOL);
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {

//        System.out.println(format("2018-1"));
        System.out.println(isValidDate("2018/07/13"));
        System.out.println(isValidTime("13:01:03"));
        System.out.println(isValidDateTime("20180713130103"));

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            System.out.println(DateUtils.check(sdf.parse("2017-10-26 1:20:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/


//        System.out.println(getMinAfterNow(20));;
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.AddDaysAfterNow(-7)));

    }


}
