package com.inthemoon.deeplearning.svhndb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Configuraion of database access and Spring JPA
 *
 * Created by Dims on 16.02.2017.
 */
@Configuration
@EnableJpaRepositories("com.inthemoon.deeplearning.svhndb.repo")
@ImportResource("classpath:data_source.xml")
public class DataConfig {

   final DataSource dataSource;

   @Autowired
   public DataConfig(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean ans =
         new LocalContainerEntityManagerFactoryBean();
      ans.setDataSource(dataSource);
      ans.setJpaVendorAdapter(jpaVendorAdapter());
      ans.setPackagesToScan("com.inthemoon.deeplearning.svhndb.model");
      return ans;
   }

   @Bean
   public JpaVendorAdapter jpaVendorAdapter() {
      HibernateJpaVendorAdapter ans = new HibernateJpaVendorAdapter();
      ans.setShowSql(false);
      ans.setGenerateDdl(true);
      ans.setDatabase(Database.H2);
      return ans;
   }

   @Bean
   public PlatformTransactionManager transactionManager() {
      JpaTransactionManager ans = new JpaTransactionManager();
      ans.setEntityManagerFactory(entityManagerFactory().getObject());

      return ans;
   }
}
