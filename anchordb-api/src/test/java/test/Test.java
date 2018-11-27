package test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class Test {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		  
		List strings = IntStream.rangeClosed(1, 10_000_000)
		  .mapToObj(Integer::toString) 
		  .collect(Collectors.toList());
		
		System.out.println(Collectors.toList());

		  
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(
		  "Generated " + strings.size() + " strings in " + totalTime + " ms.");
		 
		startTime = System.currentTimeMillis();
		  
		String appended = (String) strings.stream()
		  .limit(100_000)
		  .reduce("", (l, r) -> l.toString() + r.toString());
		  
		totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Created string of length " + appended.length() 
		  + " in " + totalTime + " ms.");
		
		System.out.println("----");
		
		Stream.iterate(0, i -> i < 10, i -> i + 1)
		  .forEach(System.out::println);
		
		System.out.println("stream take while");
		Stream<String> stream1 = Stream.iterate("", s -> s + "s")
				  .takeWhile(s -> s.length() < 10);
		
		stream1.forEach(System.out::println);

		
		var sql = "";
	
		
		System.out.println(Optional.of(sql).isEmpty());
		}

}
