package ge.pandora.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;



/**
 * Created by zviad on 3/15/16.
 */
@Configuration
@EnableWebMvc
//@EnableTransactionManagement
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public DataSource oracleDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(getConfig("spring.datasource.driver-class-name"));
//        dataSource.setUrl(getConfig("spring.datasource.url"));
//        dataSource.setUsername(getConfig("spring.datasource.username"));
//        dataSource.setPassword(getConfig("spring.datasource.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(oracleDataSource());
////		em.setPackagesToScan(getConfig("entityManager.packages.to.scan"));
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {
//            {
//                this.setDatabasePlatform(getConfig("spring.jpa.database-platform"));
//                String showSql = getConfig("spring.jpa.show-sql");
//                if (showSql != null) {
//                    this.setShowSql(showSql.equals("true"));
//                }
//            }
//        });
//        Properties entityManagerProperties = new Properties();
//        entityManagerProperties.put("hibernate.temp.use_jdbc_metadata_defaults", getConfig("hibernate.temp.use_jdbc_metadata_defaults"));
//        entityManagerProperties.put("hibernate.format_sql", getConfig("hibernate.format_sql"));
//        em.setJpaProperties(entityManagerProperties);
//        return em;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }

}
