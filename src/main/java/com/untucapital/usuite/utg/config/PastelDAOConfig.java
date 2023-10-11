package com.untucapital.usuite.utg.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Component
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "pastelEntityManagerFactory",
        basePackages 	 = {"com.untucapital.usuite.utg.repository2"},
        transactionManagerRef = "pastelTransactionManager"
)
public class PastelDAOConfig {

     @Value("${spring.second-datasource.url}")
        private String url2;

        @Value("${spring.second-datasource.username}")
        private String username2;

        @Value("${spring.second-datasource.password}")
        private String password2;

        @Value("${spring.second-datasource.driverClassName}")
        private String driverClass2;


        @Primary
        @Bean(name= "pastelDataSource")
        public DataSource dataSource() {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setUrl(url2);
            ds.setUsername(username2);
            ds.setPassword(password2);
            ds.setDriverClassName(driverClass2);
            return ds;
        }


        @Primary
        @Bean(name= "pastelEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManager() {
            LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
            bean.setDataSource(dataSource());
            JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            bean.setJpaVendorAdapter(adapter);
            HashMap<String,Object> properties = new HashMap<String, Object>();
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            bean.setJpaPropertyMap(properties);
            bean.setPackagesToScan("com.untucapital.usuite.utg.entity");
            return bean;

        }

        @Primary
        @Bean("pastelTransactionManager")
        public PlatformTransactionManager transactionManager(@Qualifier("pastelEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
            return new JpaTransactionManager(entityManagerFactory);
        }



//
//
//        @Value("${spring.second-datasource.url}")
//        private String url2;
//
//        @Value("${spring.second-datasource.username}")
//        private String username2;
//
//        @Value("${spring.second-datasource.password}")
//        private String password2;
//
//        @Value("${spring.second-datasource.driverClassName}")
//        private String driverClass2;
//
//
//        @Primary
//        @Bean(name = "pastelEntityManagerFactory")
//        public LocalContainerEntityManagerFactoryBean pastelEntityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                           @Qualifier("datasource") DataSource dataSource) {
//            Map<String, Object> properties = new HashMap<>();
//            properties.put("hibernate.hbm2ddl.auto", "update");
//            properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
//            // properties.put("hibernate.physical_naming_strategy", CamelCaseSplittingFieldNamingStrategy.class.getName());
//            return builder.dataSource(dataSource)
//                    .properties(properties)
//                    .packages("com.untucapital.usuite.utg.entity")
//                    .persistenceUnit("User").build();
//        }
//
//        @Primary
//        @Bean(name = "pastelTransactionManager")
//        public PlatformTransactionManager transactionManager(@Qualifier("pastelEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//            return new JpaTransactionManager(entityManagerFactory);
//
//        }
//
////        @Bean(name = "secondJdbcTemplate")
////        public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
////            return new JdbcTemplate(dataSource);
////        }
//
//        @Bean(name = "datasource")
//        public DataSource secondDataSource() {
//            DriverManagerDataSource
//
//                    dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName(driverClass2);
//            dataSource.setUrl(url2);
//            dataSource.setUsername(username2);
//            dataSource.setPassword(password2);
//            return dataSource;
//        }



}
