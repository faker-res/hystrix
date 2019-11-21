package eurekaclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gunnar Hillert
 *
 */
@SpringBootApplication
@RestController
public class ProviderApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProviderApplication.class, args);
	}

	@GetMapping("execute")
	public String execute(String name) {
		return "hi," + name;
	}

}
