package java虚拟机.日期处理;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author fb
 * @title: Test
 * @projectName LearningResource
 * @description: 日期处理
 * @date 2019/10/2813:02
 */
public class Test {

    @org.junit.Test
    public void test() {
        Date date = new Date();
        System.out.println(date);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 多线程操作同一个format的时候会出现设置时间重复
        // 查阅源码 SimpleDateFormat
        // Convert input date to time field list
        // calendar.setTime(date);
        String dateStr = format.format(date);
        System.out.println("sdf format: " + dateStr);
        try {
            // 查阅源码 SimpleDateFormat CalendarBuilder
            // parsedDate = calb.establish(calendar).getTime();
            // 问题：a、重置日期对象cal的属性值
            //       b、使用calb中中属性设置cal
            //       c、返回设置好的cal对象   操作不是原子性操作，高并发异常
            // cal.clear();
            //        // Set the fields from the min stamp to the max stamp so that
            //        // the field resolution works in the Calendar.
            //        for (int stamp = MINIMUM_USER_STAMP; stamp < nextStamp; stamp++) {
            //            for (int index = 0; index <= maxFieldIndex; index++) {
            //                if (field[index] == stamp) {
            //                    cal.set(index, field[MAX_FIELD + index]);
            //                    break;
            //                }
            //            }
            //        }
            //
            //        if (weekDate) {
            //            int weekOfYear = isSet(WEEK_OF_YEAR) ? field[MAX_FIELD + WEEK_OF_YEAR] : 1;
            //            int dayOfWeek = isSet(DAY_OF_WEEK) ?
            //                                field[MAX_FIELD + DAY_OF_WEEK] : cal.getFirstDayOfWeek();
            //            if (!isValidDayOfWeek(dayOfWeek) && cal.isLenient()) {
            //                if (dayOfWeek >= 8) {
            //                    dayOfWeek--;
            //                    weekOfYear += dayOfWeek / 7;
            //                    dayOfWeek = (dayOfWeek % 7) + 1;
            //                } else {
            //                    while (dayOfWeek <= 0) {
            //                        dayOfWeek += 7;
            //                        weekOfYear--;
            //                    }
            //                }
            //                dayOfWeek = toCalendarDayOfWeek(dayOfWeek);
            //            }
            //            cal.setWeekDate(field[MAX_FIELD + WEEK_YEAR], weekOfYear, dayOfWeek);
            //        }
            //        return cal;
            Date parseDate = format.parse(dateStr);
            System.out.println("sdf parse: " + parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate of = LocalDate.of(2019, 10, 28);
        System.out.println(of);

        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);

        System.out.println(year + "-" + year1);
        System.out.println(month + "-" + month1);
        System.out.println(day + "-" + day1);
        System.out.println(dayOfWeek + "-" + dayOfWeek1);

        LocalTime localTime = LocalTime.of(13, 51, 10);
        LocalTime localTime1 = LocalTime.now();
        //获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        //获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        //获取秒
        int second = localTime.getSecond();
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);

        System.out.println(localTime + "-" + localTime1);
        System.out.println(hour + "-" + hour1);
        System.out.println(minute + "-" + minute1);
        System.out.println(second + "-" + second1);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);

        LocalDate localDate2 = localDateTime.toLocalDate();

        LocalTime localTime2 = localDateTime.toLocalTime();

        Instant instant = Instant.now();
        long currentSecond = instant.getEpochSecond();
        long currentMilli = instant.toEpochMilli();

        LocalDateTime localDateTime9 = LocalDateTime.of(2019, Month.SEPTEMBER, 10,
                14, 46, 56);
//增加一年
        localDateTime9 = localDateTime9.plusYears(1);
        localDateTime9 = localDateTime9.plus(1, ChronoUnit.YEARS);
//减少一个月
        localDateTime9 = localDateTime9.minusMonths(1);
        localDateTime9 = localDateTime9.minus(1, ChronoUnit.MONTHS);
        //修改年为2019
        localDateTime9 = localDateTime9.withYear(2020);
//修改为2022
        localDateTime9 = localDateTime9.with(ChronoField.YEAR, 2022);

        LocalDate localDate9 = LocalDate.now();
        LocalDate localDate1 = localDate9.with(firstDayOfYear());

        LocalDate localDate91 = LocalDate.of(2019, 9, 10);
        String s1 = localDate91.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate91.format(DateTimeFormatter.ISO_LOCAL_DATE);
//自定义格式化
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate91.format(dateTimeFormatter);




        //SpringBoot中应用LocalDateTime
        //
        //将LocalDateTime字段以时间戳的方式返回给前端 添加日期转化类
        //public class LocalDateTimeConverter extends JsonSerializer<LocalDateTime> {
        //
        //    @Override
        //    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        //    gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        //    }
        //}
        //
        //并在LocalDateTime字段上添加@JsonSerialize(using = LocalDateTimeConverter.class)注解，如下：
        //@JsonSerialize(using = LocalDateTimeConverter.class)
        //protected LocalDateTime gmtModified;
        //
        //将LocalDateTime字段以指定格式化日期的方式返回给前端 在LocalDateTime字段上添加@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")注解即可，如下：
        //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        //protected LocalDateTime gmtModified;
        //
        //对前端传入的日期进行格式化 在LocalDateTime字段上添加@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")注解即可，如下：
        //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        //protected LocalDateTime gmtModified;
    }


}
