package com.pttem.ecommerce.order;

import com.pttem.ecommerce.order.dao.base.BaseRepositoryCustomizedImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
