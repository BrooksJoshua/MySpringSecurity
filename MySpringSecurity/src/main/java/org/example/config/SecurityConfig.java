package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.PrintWriter;

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

    @Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("Alex").password("123").roles("admin").build());
        manager.createUser(User.withUsername("Brooks").password("456").roles("user").build());
        return manager;
    }

    /**
     * 角色继承配置
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    /**
     * 这种方式优先级高于上面那种， 两个都配置的情况下， 只有这个会生效， 用上面配置的用户信息登陆会报BadCredential
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("aaa")
//                .password("111")
//                .roles("admin")
//                .and()
//                .withUser("bbb")
//                .password("222")
//                .roles("admin");
//    }

    /**
     * 静态资源放行
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    /**
     * 配置自定义的登陆界面文件路径
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin") //⚠️注意一定带前面的/
                .usernameParameter("uname")
                .passwordParameter("pwd")
                //.defaultSuccessUrl("/success") //重定向，  调用重载方法 defaultSuccessUrl(defaultSuccessUrl, false); 如果显示设置成true，就和successForwardUrl("/success")的功能效果一致了。 所以只用配置一个即可
                //.successForwardUrl("/success") // 服务端跳转
                .successHandler( //配置了successHandler后在里面设置 response.setContentType("application/json;charset=utf-8");表示登陆成功不是跳转页面而是返回JSON数据。
//                        new AuthenticationSuccessHandler() {
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                                //...
//                            }
//                        }
                        /**
                         *  用lambda表达式代替上面的匿名对象，
                         */
                        (request,response,authentication)->{
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter out = response.getWriter();
                            out.write(new ObjectMapper().writeValueAsString(authentication)); // 将authentication.getPrincipal()信息写回客户端。
                            out.flush();
                            out.close();
                        }
                )
                //.failureForwardUrl() //服务端跳转
                //.failureUrl() //重定向
                //.successHandler()
                .failureHandler(
                        (request,response,exception)->{
                            RespBean respBean = RespBean.build();
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter out = response.getWriter();
                            if(exception instanceof LockedException){
                                respBean.setMsg("账号被锁定");
                            }else if(exception instanceof CredentialsExpiredException){
                                respBean.setMsg("密码过期");
                            }else if(exception instanceof AccountExpiredException){
                                respBean.setMsg("账户过期");
                            }else if(exception instanceof BadCredentialsException){
                                respBean.setMsg("用户名或密码不对");
                            }else if(exception instanceof DisabledException){
                                respBean.setMsg("账户禁用");
                            }else{
                                respBean.setMsg("未知错误\\n"+exception.getLocalizedMessage());
                            }
                            respBean.setStatus(500);
                            out.write(new ObjectMapper().writeValueAsString(respBean));
                            out.flush();
                            out.close();
                        }
                ) //两个handler多用于前后端分离的场景。
                .permitAll()
                .and()
                .logout() //如果不配置，访问http://localhost:9999/logout 后会跳转到http://localhost:9999/login.html页面, 能在浏览器直接访问 侧面说明登陆默认是get请求
                //.logoutUrl("/logout") // 自定义登出的访问路径， 还是GET方式
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) // 如果业务场景必须使用POST登出， 则可以这样配置
                //.logoutSuccessUrl("/login.html")// 此行可以不配置， 默认注销登陆成功就是跳转到登陆界面
                .logoutSuccessHandler((request,response,authentication)->{
                    PrintWriter out = response.getWriter();
                    RespBean respBean = RespBean.build();
                    respBean.setMsg("注销登录成功");
                    respBean.setStatus(200);
                    response.setContentType("application/json;charset=utf-8");
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                })
                .invalidateHttpSession(true) //注销登陆后使session失效， 默认是true也可以不配置
                .clearAuthentication(true)//清除认证信息，默认也是true  也可以不配置
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request,response,exception)->{
                    RespBean respBean = RespBean.build();
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    respBean.setMsg("尚未登陆!");
                    respBean.setStatus(401);
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                });
    }
}
