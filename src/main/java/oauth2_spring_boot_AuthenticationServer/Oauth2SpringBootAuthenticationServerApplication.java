package oauth2_spring_boot_AuthenticationServer;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class Oauth2SpringBootAuthenticationServerApplication {

	private static final Log log = LogFactory.getLog(Oauth2SpringBootAuthenticationServerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2SpringBootAuthenticationServerApplication.class, args);
	}
	
	@RequestMapping("/user")
    public Principal user(Principal user) {
		log.info("=======================wywolanie user w Auth Server ============= "+ user.toString());
        return user;
    }
	
	
}
