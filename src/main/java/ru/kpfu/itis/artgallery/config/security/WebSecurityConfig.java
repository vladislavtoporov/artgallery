package ru.kpfu.itis.artgallery.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kpfu.itis.artgallery")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ConnectionFactoryLocator connectionFactoryLocator;
    private final UsersConnectionRepository usersConnectionRepository;
    private final SocialConnectionSignup socialConnectionSignup;
    private UserDetailsService userDetailsService;
    private AuthenticationService authenticationService;
    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, AuthenticationService authenticationService, @Qualifier("dataSource") DataSource dataSource, ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SocialConnectionSignup socialConnectionSignup) {
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
        this.dataSource = dataSource;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.usersConnectionRepository = usersConnectionRepository;
        this.socialConnectionSignup = socialConnectionSignup;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.rememberMe()
                .key("rem-me-key")
                .tokenRepository(persistentTokenRepository())
                .rememberMeParameter("remember-me-param")
                .rememberMeCookieName("my-remember-me")
                .tokenValiditySeconds(1209600);

        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN", "STAFF")
                .antMatchers("/**").permitAll()

                .and()
                .formLogin().loginPage("/signIn")
                .usernameParameter("login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login")
                .failureUrl("/signIn?error")
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/signIn")
                .deleteCookies("JSESSIONID")
                .permitAll();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
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
    // @Primary
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(socialConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SocialSignInAdapter());
    }
}
