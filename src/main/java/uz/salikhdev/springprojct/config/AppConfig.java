package uz.salikhdev.springprojct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public JdbcTemplate provideJdcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
