package com.quizz.configurations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.quizz.services.WebUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebMvcSecurityConfig {
    @Autowired
    private WebUserService webUserService;

    @Bean
    SecurityFilterChain WebfilterChain(HttpSecurity http) throws Exception {
	return http.securityMatcher(request -> !request.getRequestURI().startsWith("/api/")) 
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
			.requestMatchers("/",  "/login",
		 "/assets/**", "/css/**", "/js/**", "/images/**", "/webjars/**")
			.permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
		.formLogin(form -> form.loginPage("/auth/login").loginProcessingUrl("/auth/process-login")
			.usernameParameter("usernameOrEmail").passwordParameter("password")
			.successHandler(new AuthenticationSuccessHandler() {
			    @Override
			    public void onAuthenticationSuccess(HttpServletRequest request,
				    HttpServletResponse response, Authentication authentication)
				    throws IOException, ServletException {
				Map<String, String> urls = new HashMap<>();
				urls.put("ROLE_ADMIN", "/auth/welcome");
				urls.put("ROLE_USER", "/auth/welcome");
				List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication
					.getAuthorities();
				String role = authorities.get(0).getAuthority();
				String url = urls.get(role);
				System.out.println("Role: " + role);
				response.sendRedirect(url);

			    }
			}).failureUrl("/auth/login?error").permitAll())
		.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
			.logoutSuccessUrl("/auth/login?logout").permitAll())

		.exceptionHandling(ex -> ex.accessDeniedPage("/auth/access-denied")).build();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	builder.userDetailsService(webUserService);
    }
}
