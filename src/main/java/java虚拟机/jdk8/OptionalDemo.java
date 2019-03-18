package java虚拟机.jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @ClassName OptionalDemo
 * @Description stream流对象中
 * @Author root
 * @Date 19-3-6 下午5:38
 * @Version 1.0
 **/
public class OptionalDemo<T> {

	public static void main(String[] args) throws IOException {

		//  流构建器具有生命周期，其从构建阶段开始，在该阶段期间可以添加元素，然后转换到内置阶段，之后可能不添加元素。
		// 构建阶段从调用build()方法开始，它创建一个有序流，其元素是添加到流构建器的元素，按照它们被添加的顺序。
		DoubleStream doubleStream = DoubleStream.builder().add(8.00000D).add(1.00000D).add(1.00000D).add(1.02135D).add(4.02135D).build();
		DoubleStream doubleStream1 = DoubleStream.builder().add(8.00000D).add(1.00000D).add(1.00000D).add(1.02135D).add(4.02135D).build();
//		System.out.println(doubleStream.average().getAsDouble());
//		System.out.println(doubleStream.sum());
//		System.out.println(doubleStream.max().getAsDouble());
//		System.out.println(doubleStream.count());
//		System.out.println(doubleStream.distinct().count());
//		System.out.println(doubleStream.filter(d -> d < 2).sum());
//		doubleStream.forEach(d -> System.out.println(d));
//		System.out.println(doubleStream.parallel().map(x -> x * x).sum());
//		System.out.println(doubleStream.allMatch(value -> value == 1.0D));
//		System.out.println(doubleStream.anyMatch(value -> value == 1.0D));
		DoubleStream concatStream = DoubleStream.concat(doubleStream, doubleStream1);
//		System.out.println(concatStream.count());
		DoubleStream emptyStream = DoubleStream.empty();
//		System.out.println(emptyStream.count());
		concatStream.sorted().forEach(System.out::println);

//		System.out.println(concatStream.flatMap(d -> emptyStream).sum());


		String str = null;
//		System.out.println(Optional.of(str).orElse("it null"));
		System.out.println(Optional.ofNullable(null).orElse("it null too"));


		new Thread(() -> System.out.println(123)).start();

//		Stream<String> lines = Files.lines(Paths.get("/root"));
//		lines.forEach(System.out::println);

//		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

		Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8)
				.filter(n -> n > 2)     // 对元素过滤，保留大于2的元素
				.distinct()             // 去重，类似于SQL语句中的DISTINCT
				.skip(1)                // 跳过前面1个元素
				.limit(2)               // 返回开头2个元素，类似于SQL语句中的SELECT TOP
				.sorted()               // 对结果排序
				.forEach(System.out::println);


		List<String[]> collect = Stream.of("color=红色").map(s -> s.split("=")).collect(Collectors.toList());
		Stream.of("color=红色").map(s -> s.split("=")).flatMap(Arrays::stream).collect(Collectors.toList()).forEach(System.out::println);

		System.out.println(Stream.of("s", "2", "1").collect(Collectors.joining(",")));

		long sum = LongStream.rangeClosed(1, 10L).parallel().reduce(0, Long::sum);
		System.out.println(sum);


		LocalDate now = LocalDate.now();
		System.out.println(now + ": " + now.getYear() + "," + now.getMonth() + "," + now.getDayOfMonth());
		LocalDate localDate = LocalDate.of(1992, 3, 25);
		System.out.println(localDate.compareTo(now));
		LocalTime.of(11, 30, 30);

		System.out.println(Instant.now(Clock.systemDefaultZone()).toEpochMilli() + ", " + System.currentTimeMillis());

		System.out.println(Month.JANUARY.getValue());

		LocalDateTime start = LocalDateTime.of(2019, 4, 11, 11, 11, 11);
		LocalDateTime end = LocalDateTime.of(2019, Month.APRIL, 15, 12, 11, 11);
		Duration duration = Duration.between(start, end);
		System.out.println(duration.toDays() + "," + duration.toHours());

		Duration of1 = Duration.of(3, ChronoUnit.DAYS);
		System.out.println(of1.toDays() + "," + of1.toHours());


		Period period0 = Period.of(2019, 3, 11);
		Period period1 = Period.of(2019, 3, 15);
		System.out.println(period0.getDays() + "," + period1.getDays());
		Period period = Period.between(LocalDate.of(2019, 3, 11),
				LocalDate.of(2019, 3, 15));
		System.out.println(period.get(ChronoUnit.DAYS));


		LocalDate workDay = LocalDate.of(2019, 3, 15);
		LocalDate local = workDay.with(temporal -> {
			DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dayOfWeek == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			}
			if (dayOfWeek == DayOfWeek.SUNDAY) {
				dayToAdd = 2;
			}
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
		System.out.println(local);

		System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
		System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("年: YYYY, 月: MM, 日: dd", Locale.CHINESE)));
	}

}
