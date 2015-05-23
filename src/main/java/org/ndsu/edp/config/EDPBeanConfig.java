package org.ndsu.edp.config;

import org.ndsu.edp.dal.EDPDal;
import org.ndsu.edp.dal.EDPDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EDPBeanConfig {

	   @Bean 
	   public EDPDataSourceFactory dataSource(){
	      return new EDPDataSourceFactory();
	   }
	   
	   @Bean 
	   public EDPDal edpDal(){
	      return new EDPDal(dataSource());
	   }
}
