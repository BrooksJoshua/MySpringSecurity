package DateAndTimeFormatting;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Joshua.H.Brooks
 * @description
 * 对ZonedDateTime或LocalDateTime进行格式化，需要使用DateTimeFormatter类，
 * DateTimeFormatter可以通过格式化字符串和Locale对日期和时间进行定制输出。
 * @date 2022-07-28 10:33
 */
public class DateTimeFormatterUtil {
    public static void main(String[] args) {
        //自定义输出格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println("===================================");
        //自定义格式解析
        LocalDateTime localDateTime = LocalDateTime.parse("2001/07/27 22:22:22", dtf);
        System.out.println(localDateTime);

        System.out.println("########       测试二        #########");

        //
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("系统所属时区的当前日期时间: \r\n"+zonedDateTime);

        DateTimeFormatter formatter01 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ZZZZ");
        System.out.println(formatter01.format(zonedDateTime));

        DateTimeFormatter formatter02 = DateTimeFormatter.ofPattern("yyyy MMM dd EE:HH:mm", Locale.CHINA);
        //2022 七月 28 星期四:10:42
        System.out.println("中国格式输出:\r\n"+formatter02.format(zonedDateTime));

        DateTimeFormatter formatter03 = DateTimeFormatter.ofPattern("E, MMMM/dd/yyyy HH:mm", Locale.US);
        //Thu, July/28/2022 10:42
        System.out.println("美国格式输出:\r\n"+formatter03.format(zonedDateTime));



        //DateTimeFormatter预定义静态变量
        System.out.println(localDateTime);
        System.out.println(DateTimeFormatter.ISO_DATE.format(localDateTime));
        System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(localDateTime));
    }
}
