package com.example.taskboard.model.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Service
@Order(1)
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Random r = new Random();
        int random = r.nextInt(10000);

        httpServletResponse.addIntHeader("My custom filter with random number 2", random);

        filterChain.doFilter(request, response);
    }

}

//  double min = 1;
//  double max = 99;
//  double random = Math.random() * (max - min) + min;

//  httpServletResponse.setHeader("My custom filter with random number", String.valueOf(random));
//  httpServletResponse.addHeader("My custom filter with random number 3", String.valueOf(random));
