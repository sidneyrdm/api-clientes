package io.github.sidneyrdm.clientes.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    @Bean
public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("ISO-8859-1");
        messageSource.setDefaultLocale( Locale.getDefault() );
        return messageSource;
     }

     /*
     @Bean
     public LocalValidatorFactoryBean factoryBean(){
         LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
         bean.setValidationMessageSource(messageSource());
        return bean;
    }*/
  }
