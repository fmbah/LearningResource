### JDK8(https://lw900925.github.io/java/java8-stream-api.html)
#####1. lambda表达式(@FunctionalInterface)
    * list.sort((o1, o2) -> o1.compareTo(o2));
    * new Thread(() -> system.out.println("run..."));
    * list.forEach(System.out::println);
    * list.sort(Integer::compareTo)
#####2. stream
    * List<String> userNames = users.stream().filter(user -> user.getAge() > 20).sorted(comparing(User::getCreationDate)).map(User::getUserName).collect(toList());
    * Stream<Stream> stream = Stream.empty()
    * Stream<Stream> stream = Stream.of("a", "b", "c")
    * Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
    * Stream.generate(() -> ThreadLocalRandom.current().nextInt()).limit(5).forEach(System.out::println);
    * Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8)
      				.filter(n -> n > 2)     // 对元素过滤，保留大于2的元素
      				.distinct()             // 去重，类似于SQL语句中的DISTINCT
      				.skip(1)                // 跳过前面1个元素
      				.limit(2)               // 返回开头2个元素，类似于SQL语句中的SELECT TOP
      				.sorted()               // 对结果排序
      				.forEach(System.out::println);
    * Stream.of("color=红色").map(s -> s.split("=")).flatMap(Arrays::stream).collect(Collectors.toList()).forEach(System.out::println)
    * System.out.println(Stream.of("s", "2", "1").collect(Collectors.joining(",")))
    * LongStream.rangeClosed(1, 10L).parallel().reduce(0, Long::sum);
#####3. 日期类
    * LocalDate
    * LocalTime
    * LocalDateTime
    * Instant.now(Clock.systemDefaultZone()).toEpochMilli()
    * LocalDateTime start = LocalDateTime.of(2019, 4, 11, 11, 11, 11);
    * LocalDateTime end = LocalDateTime.of(2019, Month.APRIL, 15, 12, 11, 11);
    * Duration duration = Duration.between(start, end); 
    * System.out.println(duration.toHours());
    * Duration duration = Duration.of(3, ChronoUnit.DAYS)
    * Period period = Period.between(LocalDate.of(2019, 3, 11), LocalDate.of(2019, 3, 15))
    * System.out.println(period.get(ChronoUnit.DAYS))
    * LocalDate workDay = LocalDate.of(2019, 3, 15);
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
    * import static java.time.temporal.TemporalAdjusters.*;
    * date.with(nextOrSame(DayOfWeek.SUNDAY));
    * System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("年: YYYY, 月: MM, 日: dd", Locale.CHINESE)));
      		
    