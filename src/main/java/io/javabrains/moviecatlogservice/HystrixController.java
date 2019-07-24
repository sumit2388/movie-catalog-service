package io.javabrains.moviecatlogservice;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//  use this url to check hystrix working  http://localhost:8081/hystrix/hello
// hystrix dashboard 
// a. hit url http://localhost:8081/hystrix/
// b . write this in box :: http://localhost:8081/actuator/hystrix.stream
// c. press monitor stream

@RestController
@RequestMapping("/hystrix")
public class HystrixController {
	
	@HystrixCommand(fallbackMethod="fallBackHello", commandKey="hello", groupKey="hello")
	@RequestMapping("/hello")
	public List<String> getcatalog(){

		List<String> list= new ArrayList<>();
		 if (RandomUtils.nextBoolean()) {
		        throw new EmptyStackException();
		    }
		return list;		
	}
	
	@RequestMapping("/fallBackHello")
	public List<String> fallBackHello(){

	List<String> list= new ArrayList<>();
	list.add("sumit");
		return list;		
	}
	
	
}
