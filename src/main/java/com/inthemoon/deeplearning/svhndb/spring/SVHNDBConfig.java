package com.inthemoon.deeplearning.svhndb.spring;

import com.inthemoon.deeplearning.svhndb.spring.service.ImageSetService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Dims on 02.03.2017.
 */
@Configuration
@ComponentScan
@PropertySource(value="file:application.properties")
public class SVHNDBConfig {


}
