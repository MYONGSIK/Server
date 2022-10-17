package com.example.myongsick.global.util;

import java.time.LocalDate;

public class DayOfTheWeek {

    public static String DayOfTheWeekConvert(LocalDate localDate){

        switch (localDate.getDayOfWeek()){
            case SUNDAY:
                return "일요일";
            case MONDAY:
                return "월요일";
            case TUESDAY:
                return "화요일";
            case WEDNESDAY:
                return "수요일";
            case THURSDAY:
                return "목요일";
            case FRIDAY:
                return "금요일";
            case SATURDAY:
                return "토요일";
        }
        return null;
    }
}
