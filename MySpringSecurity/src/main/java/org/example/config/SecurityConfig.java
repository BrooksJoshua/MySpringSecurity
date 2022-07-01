package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Joshua.H.Brooks
 * @description Spring-Security V5 之后强制密码必须加密
 * @date 2022-07-01 12:15
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        //TODO 后期改成加密的
        return NoOpPasswordEncoder.getInstance(); //不对密码进行加密， 暂时这样
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("aaa")
                .password("111")
                .roles("admin")
                .and()
                .withUser("bbb")
                .password("222")
                .roles("admin");
    }

    /**
     * 静态资源放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    /**
     * 配置自定义的登陆界面文件路径
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin") //⚠️注意一定带前面的/
                .usernameParameter("uname")
                .passwordParameter("pwd")
                .defaultSuccessUrl("/success") //重定向，  调用重载方法 defaultSuccessUrl(defaultSuccessUrl, false); 如果显示设置成true，就和successForwardUrl("/success")的功能效果一致了。 所以只用配置一个即可
                .successForwardUrl("/success") // 服务端跳转
                //.failureForwardUrl() //服务端跳转
                //.failureUrl() //重定向
                //.successHandler()
                //.failureHandler() //两个handler多用于前后端分离的场景。
                .permitAll()
                .and()
                .logout() //如果不配置，访问http://localhost:9999/logout 后会跳转到http://localhost:9999/login.html页面, 能在浏览器直接访问 侧面说明登陆默认是get请求
                //.logoutUrl("/logout") // 自定义登出的访问路径， 还是GET方式
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) // 如果业务场景必须使用POST登出， 则可以这样配置
                .logoutSuccessUrl("/login.html")// 此行可以不配置， 默认注销登陆成功就是跳转到登陆界面
                .invalidateHttpSession(true) //注销登陆后使session失效， 默认是true也可以不配置
                .clearAuthentication(true)//清除认证信息，默认也是true  也可以不配置
                .and()
                .csrf().disable();
    }
}
