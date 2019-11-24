package com.gal.main;

import com.gal.entities.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gal.entities.GalClient;
import com.gal.entities.Order;
import com.gal.entities.OrderItem;
import com.gal.entities.Payment;
import com.gal.entities.ServiceType;
import com.gal.entities.Tag;

@SpringBootApplication
@ComponentScan("com.gal")
@Configuration
@EnableAutoConfiguration
@MappedTypes({GalClient.class, ServiceType.class,Order.class,OrderItem.class,Tag.class,Payment.class,User.class})
@MapperScan("com.gal.domain.impl")
public class GalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalApiApplication.class, args);
	}
}
