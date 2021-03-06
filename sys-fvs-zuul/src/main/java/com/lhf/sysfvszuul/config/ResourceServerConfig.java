package com.lhf.sysfvszuul.config;

import com.lhf.sysfvszuul.config.jwt.FvsJwt;
import com.lhf.sysfvszuul.handler.OauthAccessDeniedHandler;
import com.lhf.sysfvszuul.handler.OauthAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * <p></p>
 *
 * @author lhf
 * @since 2020/9/12 14:50
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        FvsJwt converter = new FvsJwt();
        converter.setSigningKey("fvs");
        return converter;
    }

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter tokenConverter) {
        return new JwtTokenStore(tokenConverter);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("8e9daef0-5494-48da-8f77-22ac923828bf")
                .authenticationEntryPoint(new OauthAuthenticationEntryPoint())
                .accessDeniedHandler(new OauthAccessDeniedHandler())
//                .tokenServices(tokenServices())
                .tokenStore(tokenStore(tokenConverter()))
        ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .antMatchers("/api/auth/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .cors();
    }

//    @Bean
//    public RemoteTokenServices tokenServices() {
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setClientId("e5170418-8560-460b-9296-d7bd95a06a5e");
//        tokenServices.setClientSecret("123");
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:8001/oauth/check_token");
//        return tokenServices;
//    }
}
