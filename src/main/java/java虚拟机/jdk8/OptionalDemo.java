package java虚拟机.jdk8;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * @ClassName OptionalDemo
 * @Description stream流对象中
 * @Author root
 * @Date 19-3-6 下午5:38
 * @Version 1.0
 **/
public class OptionalDemo {

	public static void main(String[] args) {

		DoubleStream doubleStream = DoubleStream.builder().add(8.00000D).add(1.00000D).add(1.02135D).add(4.02135D).build();
//		Arrays.asList(doubleStream.limit(2L).toArray()).forEach(doubles -> {
//			for (Double d : doubles) {
//				System.out.println(d);
//			}
//		});
//		doubleStream.close();
		System.out.println(doubleStream.average().getAsDouble());

	}

}
