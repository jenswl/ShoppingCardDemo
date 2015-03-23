package demo;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@EnableSwagger
public class ShoppingCardApplication {

    @Resource
    private SpringSwaggerConfig springSwaggerConfig;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCardApplication.class, args);
    }

    @Bean
    public Filter corsFilter() {
        return new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "validation-requested-with, Content-Type, api_key, Authorization");


                filterChain.doFilter(servletRequest, response);
            }

            @Override
            public void destroy() {

            }
        };
    }

    @Bean
    SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .swaggerGroup("card")
                .excludeAnnotations(Controller.class)
                .includePatterns("/cards/.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Simple shopping card API",
                "Offers simple crud functionalities",
                "",
                "",
                "",
                ""
        );
    }


}
