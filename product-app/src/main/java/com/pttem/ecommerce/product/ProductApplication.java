package com.pttem.ecommerce.product;

import com.pttem.ecommerce.product.dao.base.BaseRepositoryCustomizedImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

@EntityScan({
		"com.pttem"
})
@EnableJpaRepositories(value = {
		"com.pttem"
}, repositoryBaseClass = BaseRepositoryCustomizedImpl.class)
@ComponentScan(basePackages = {
		"com.pttem"
})

@SpringBootApplication
public class ProductApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
