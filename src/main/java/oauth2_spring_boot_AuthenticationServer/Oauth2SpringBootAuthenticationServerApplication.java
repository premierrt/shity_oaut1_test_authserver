package oauth2_spring_boot_AuthenticationServer;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class Oauth2SpringBootAuthenticationServerApplication  extends AuthorizationServerConfigurerAdapter {

	private static final Log log = LogFactory.getLog(Oauth2SpringBootAuthenticationServerApplication.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
	}

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    
	public static void main(String[] args) {
		SpringApplication.run(Oauth2SpringBootAuthenticationServerApplication.class, args);
	}
	
//	@RequestMapping("/user")
//    public Principal user(Principal user) {
//		log.info("=======================wywolanie user w Auth Server ============= "+ user.toString());
//        return user;
//    }
	
	
	/**
	 * Configure the non-security features of the Authorization Server endpoints, like token store, token customizations, user approvals and grant types. You shouldn't need to do anything by default, unless you need password grants, in which case you need to provide an AuthenticationManager
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
	}
	
	
	@Override
	public void configure(
			org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer clients)
			throws Exception {
		// TODO Auto-generated method stub
		clients.inMemory().withClient("SOMEAPP").secret("SECRET")
		.authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
		.scopes("level_2","level_1");
		
	}


}
