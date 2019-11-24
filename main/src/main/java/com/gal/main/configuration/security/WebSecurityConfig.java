package com.gal.main.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

import com.gal.main.configuration.security.filters.TokenAuthenticationFilter;
import com.gal.main.configuration.security.providers.DomainUserDetails;
import com.gal.main.configuration.security.providers.TokenAuthenticationProvider;
import com.gal.main.configuration.security.providers.facebook.FacebookConnectionSignup;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DomainUserDetails domainUserDetails;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html",
            "/swagger-resources/configuration/security").permitAll();



        http.authorizeRequests()
            .antMatchers("/api/user/**").hasRole("ADMIN")
            .and()
            .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class);



        /*
         * .and()
         * .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class).exceptionHandling()
         * .authenticationEntryPoint(authenticationEntryPoint);
         */
    }

    @Bean
    public FilterRegistrationBean addTokenFilter(TokenAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }



    /*
     * @Bean
     * public ProviderSignInController providerSignInController() {
     * ((InMemoryUsersConnectionRepository) usersConnectionRepository)
     * .setConnectionSignUp(facebookConnectionSignup);
     * 
     * return new ProviderSignInController(
     * connectionFactoryLocator,
     * usersConnectionRepository,
     * new FacebookSignInAdapter());
     * }
     */


    /*
     * private SimpleUrlAuthenticationFailureHandler getMyFailureHandler() {
     * return new SimpleUrlAuthenticationFailureHandler();
     * }
     * 
     * private MySavedRequestAwareAuthenticationSuccessHandler getMySuccessHandler() {
     * return new MySavedRequestAwareAuthenticationSuccessHandler();
     * }
     */


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    /*
     * @Bean
     * public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
     * return new CustomBasicAuthenticationEntryPoint();
     * }
     */

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security", "/swagger-ui.html",
            "/webjars/**", "/swagger-resources/configuration/ui",
            "/swagge‌r-ui.html", "/docs/**",
            "/swagger-resources/configuration/security");
        web.ignoring().antMatchers(".*(swagger).*");
    }

    //web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");


}
