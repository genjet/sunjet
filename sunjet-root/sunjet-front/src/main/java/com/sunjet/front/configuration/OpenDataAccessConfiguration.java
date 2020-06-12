package com.sunjet.front.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.sunjet.common.dao" })
@EntityScan("com.sunjet.common.entity")
public class OpenDataAccessConfiguration {
}
