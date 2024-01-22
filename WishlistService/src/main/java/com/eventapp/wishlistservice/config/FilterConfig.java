package com.eventapp.wishlistservice.config;

import com.eventapp.wishlistservice.filter.JwtWishlistFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean getFilter() {

        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtWishlistFilter());
        filter.addUrlPatterns("/api/v1/wishlist/*");
        return filter;
    }
}
