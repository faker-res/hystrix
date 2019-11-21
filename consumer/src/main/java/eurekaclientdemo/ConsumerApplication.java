package eurekaclientdemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 *
 * @author Gunnar Hillert
 *
 */
@SpringBootApplication
@RestController
@EnableCircuitBreaker
public class ConsumerApplication {
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("invoke")
	@HystrixCommand(fallbackMethod = "method")
	public String execute(String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		return restTemplate.getForObject("http://provider/execute?name={name}", String.class, map);
	}
	
	public String method(String name) {
		return "断路器打开:" + name;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
