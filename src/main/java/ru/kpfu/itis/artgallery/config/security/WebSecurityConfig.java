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

    private ConnectionFactoryLocator connectionFactoryLocator;
    private UsersConnectionRepository usersConnectionRepository;
    private SocialConnectionSignup socialConnectionSignup;
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

//    @Bean
//    @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
//    public ConnectionFactoryLocator connectionFactoryLocator() {
//        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//        registry.addConnectionFactory(new FacebookConnectionFactory(
//                "2099439913406356",
//                "71aa9054670830a21ed3b359703b81f2"));
//        registry.addConnectionFactory(new TwitterConnectionFactory(
//                "YyWOLJ4zabQ9GoGwETjbm8E2L",
//                "eVfznnXmgCem27wqAw88cN70b2xljg86KtNjUDQXPBINTLKSMr"));
////                environment.getProperty("netflix.consumerKey"),
////                environment.getProperty("netflix.consumerSecret")));
//        return registry;
//    }
//
//    @Bean
//    public ConnectController connectController() {
//        return new ConnectController(connectionFactoryLocator(), connectionRepository());
//    }
//
//    @Bean
//    @Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
//    public UsersConnectionRepository usersConnectionRepository() {
//        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), Encryptors.noOpText());
//    }
//
//    @Bean
//    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//    public ConnectionRepository connectionRepository() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
//        }
//        return usersConnectionRepository().createConnectionRepository(authentication.getName());
//    }
//
//    @Bean
//    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//    public TwitterApiHelper twitter() {
//        Connection<TwitterApiHelper> twitter = connectionRepository().findPrimaryConnection(TwitterApiHelper.class);
//        return twitter != null ? twitter.getApi() : null;
//    }

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
                .failureUrl("/signIn/error")
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
