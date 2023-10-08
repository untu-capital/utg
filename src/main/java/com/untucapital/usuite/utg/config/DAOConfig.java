//package com.untucapital.usuite.utg.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",basePackages = {"com.untucapital.usuite.utg.repository"},transactionManagerRef = "transactionManager")
//public class DAOConfig {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.driverClassName}")
//    private String driverClass;
//    @Value("${spring.second-datasource.url}")
//    private String url2;
//
//    @Value("${spring.second-datasource.username}")
//    private String username2;
//
//    @Value("${spring.second-datasource.password}")
//    private String password2;
//    @Value("${spring.second-datasource.driverClassName}")
//    private String driverClass2;
//
//
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                       @Qualifier("datasource") DataSource dataSource) {
//        Map<String,Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto","update");
//        properties.put("hibernate.dialect","org.hibernate.dialect.SQLServer2012Dialect");
//        // properties.put("hibernate.physical_naming_strategy", CamelCaseSplittingFieldNamingStrategy.class.getName());
//        return builder.dataSource(dataSource)
//                .properties(properties)
//                .packages("com.untucapital.usuite.utg.model")
//                .persistenceUnit("User").build();
//    }
//    @Primary
//    @Bean(name="transactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
//        return new JpaTransactionManager(entityManagerFactory);
//
//    }
//
//    @Bean(name = "secondJdbcTemplate")
//    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "datasource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//    @Bean(name = "secondDataSource")
//    public DataSource secondDataSource() {
//        DriverManagerDataSource
//
//                dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClass2);
//        dataSource.setUrl(url2);
//        dataSource.setUsername(username2);
//        dataSource.setPassword(password2);
//        return dataSource;
//    }
//
//
//}
