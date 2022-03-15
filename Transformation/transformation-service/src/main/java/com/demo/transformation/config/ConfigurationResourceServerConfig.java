package com.demo.transformation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ConfigurationResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "transformation-resource";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()

                .authorizeRequests()
//				.antMatchers("/").permitAll()
//				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/v1/utilities").hasAuthority("READ_UTILITY")


                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

//        http.headers().frameOptions().disable();
//		http.csrf().disable();
//		http.csrf().ignoringAntMatchers("/h2-console/**");
	}

}