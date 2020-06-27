package com.yida.springbootshirotest.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */
@Configuration
public class dbConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }


    //配置druid的监控
    //1.配置管理后台的Servlet,druid提供的这个servlet名字叫:StatViewServlet
    /*@Bean
    public ServletRegistrationBean setDruidServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet());
        bean.setUrlMappings(Arrays.asList(new String[]{"/druid/*"}));//设置本servlet映射的url
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");//设置登录后台的用户名,生产环境最好复杂点
        initParams.put("loginPassword","123456");//设置登录后台的密码,生产环境最好复杂点
        initParams.put("allow","");//设置允许访问后台的ip地址,空字符串表示所有地址访问,生产环境可以指定具体ip
        //initParams.put("deny","10.121.78.125");//设置拒绝指定ip地址访问,这些就不设置了
        bean.setInitParameters(initParams);
        return bean;
    }*/


    //2.配置web监控的过滤器filter,druid提供的filter名字叫 :WebStatFilter
    /*@Bean
    public FilterRegistrationBean setDruidFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());
        bean.setUrlPatterns(Arrays.asList(new String[]{"/*"}));//设置过滤的url
        //注意:有些url是不能过滤的,比如:静态资源,druid的servlet.
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,*.jpg,*.png,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }*/
}
