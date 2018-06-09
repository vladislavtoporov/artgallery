package ru.kpfu.itis.artgallery.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import ru.kpfu.itis.artgallery.providers.JwtTokenAuthenticationProvider;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.sql.DataSource;
import java.util.Properties;

@EnableWebSecurity
public class MultiWebSecurityConfig {
    private SocialConnectionSignup socialConnectionSignup;
    private UserDetailsService userDetailsService;
    private AuthenticationService authenticationService;
    private DataSource dataSource;

    @Autowired
    public MultiWebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                  AuthenticationService authenticationService,
                                  @Qualifier("dataSource") DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
        this.dataSource = dataSource;
//        this.socialConnectionSignup = socialConnectionSignup;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Session getSessionSender() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
        props.put("username", "vladislav.itis@gmail.com");
        props.put("password", "javaitis");


        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
            }
        };
        return Session.getInstance(props, auth);
    }

    @Configuration
    @Order(2)
    public class ApiSecurityAdapter extends WebSecurityConfigurerAdapter {

        private JwtTokenAuthenticationProvider jwtTokenAuthenticationProvider;

        @Autowired
        public ApiSecurityAdapter(JwtTokenAuthenticationProvider jwtTokenAuthenticationProvider) {
            this.jwtTokenAuthenticationProvider = jwtTokenAuthenticationProvider;
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/jwt/**")
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/**").permitAll();
            http.csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jwtTokenAuthenticationProvider);
        }
    }

    @Configuration
    @Order(4)
    public class WebSecurityAdapter2 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/client/**").antMatcher("/api/**").authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder()).passwordEncoder(encoder());
        }
    }

    @Configuration
    public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.rememberMe()
                    .key("rem-me-key")
                    .tokenRepository(persistentTokenRepository())
                    .rememberMeParameter("remember-me-param")
                    .rememberMeCookieName("my-remember-me")
                    .tokenValiditySeconds(1209600);

            http.authorizeRequests()
                    .antMatchers("/admin").hasAnyAuthority("ADMIN", "STAFF", "SUPERVISER", "ARTIST")
                    .antMatchers("/admin/users/**").hasAnyAuthority("ADMIN", "SUPERVISER")
                    .antMatchers("/admin/exhibits/**").hasAnyAuthority("ADMIN", "STAFF", "SUPERVISER", "ARTIST")
                    .antMatchers("/admin/expositions/**").hasAnyAuthority("ADMIN", "STAFF", "SUPERVISER", "ARTIST")
                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN", "STAFF", "SUPERVISER")
                    .antMatchers("/rest/**").hasAnyAuthority("ADMIN", "STAFF", "SUPERVISER", "ARTIST")
                    .antMatchers("/upload/**").hasAnyAuthority("ADMIN", "STAFF", "SUPERVISER", "ARTIST")
                    .antMatchers("/**").permitAll()

                    .and()
                    .formLogin().loginPage("/signIn")
                    .usernameParameter("login")
                    .defaultSuccessUrl("/")
                    .loginProcessingUrl("/login")
                    .failureUrl("/signIn/error")
                    .permitAll()

                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/signIn")
                    .deleteCookies("JSESSIONID")
                    .permitAll();

            // Spring Social Config.
            http.apply(new SpringSocialConfigurer())
                    .postLoginUrl("/")
                    .alwaysUsePostLoginUrl(true);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.authenticationProvider(authenticationProvider());
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder()).passwordEncoder(encoder());
        }
    }

}
