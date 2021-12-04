package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.project.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetails;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
// �������� ���� �ּ� �߰�.
		web.ignoring().antMatchers("/css/**", "/js/**", "/image/**");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
		System.out.println(passwordEncoder().encode("yju0987"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// .antMatchers("/mypoint/**").hasAnyAuthority("QUERY", "WRITE") // ���� ����.
				.antMatchers("/manager").hasRole("admin")
				.antMatchers("/insert").hasRole("admin")
				.antMatchers("/update").hasRole("admin")
				.antMatchers("/manreg").hasRole("admin")
				.antMatchers("/list").hasRole("admin")// // 관리자만 접근 가능
				.antMatchers("/**").permitAll() // // 위 경우를 빼고 모든 권한을 줌=로그인 필요 없음
				.anyRequest().authenticated().and().formLogin()
				// .loginPage("/login")// �� ���� ����� �������� �����ϴ� ���� ��µ�.
				.defaultSuccessUrl("/manager") // �α��� �����ϸ� �� �ּ�.
				// .usernameParameter("username") // �α��� ������ username �� ������� �ʾҴٸ� ���⼭ ó��
				.permitAll().and().logout().logoutUrl("/logout")
				// .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // �ٸ� �ּ� ��
				// ��.
				.logoutSuccessUrl("/") // �α׾ƿ� ���� �� ������.
				.invalidateHttpSession(true).and().exceptionHandling().accessDeniedPage("/denied") // 403 ���� ó��.
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}