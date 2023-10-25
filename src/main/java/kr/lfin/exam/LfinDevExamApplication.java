package kr.lfin.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LfinDevExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(LfinDevExamApplication.class, args);
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customizePageable() {
		return p -> {
			p.setOneIndexedParameters(true);
			p.setMaxPageSize(10);
		};
	}

}
