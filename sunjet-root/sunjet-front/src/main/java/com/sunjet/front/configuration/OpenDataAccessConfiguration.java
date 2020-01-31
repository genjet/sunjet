package com.sunjet.front.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = { "com.sunjet.common.dao" })
@EntityScan("com.sunjet.common.entity")
public class OpenDataAccessConfiguration {

	// @Bean(name = "openTransactionManager")
	// @Primary
	// PlatformTransactionManager openTransactionManager(
	// @Qualifier("openEntityManager") LocalContainerEntityManagerFactoryBean
	// entityManager) {
	// return new JpaTransactionManager(entityManager.getObject());
	// }
	//
	// @Bean(name = "openEntityManager")
	// @Primary
	// LocalContainerEntityManagerFactoryBean
	// openEntityManager(@Qualifier("openDataSource") DataSource dataSource) {
	// LocalContainerEntityManagerFactoryBean factoryBean = new
	// LocalContainerEntityManagerFactoryBean();
	// HibernateJpaVendorAdapter jpaVendorAdapter = new
	// HibernateJpaVendorAdapter();
	// factoryBean.setDataSource(dataSource);
	// factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
	// factoryBean.setPackagesToScan("com.fubon.lifeinsurance.tare.callin.datasource.open.model",
	// "com.fubon.lifeinsurance.tare.share.common.model.open");
	// factoryBean.setPersistenceUnitName("openEntityManager");
	// Map<String, String> properties = new HashMap<>();
	// properties.put("hibernate.dialect",
	// "org.hibernate.dialect.SQLServer2012Dialect");
	// properties.put("hibernate.connection.release_mode", "auto");
	// factoryBean.setJpaPropertyMap(properties);
	// return factoryBean;
	// }
	//
	// @Bean(name = "openJdbcTemplate")
	// @Primary
	// public NamedParameterJdbcTemplate openNamedParameterJdbcTemplate(
	// @Qualifier("openDataSource") DataSource dataSource) {
	// return new NamedParameterJdbcTemplate(dataSource);
	// }
}
