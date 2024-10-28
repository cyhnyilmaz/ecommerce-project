package com.pttem.ecommerce.user;

import com.pttem.ecommerce.user.dao.base.BaseRepositoryCustomizedImpl;
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
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
