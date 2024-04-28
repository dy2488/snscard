package com.snscard.web.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
@Configuration
public class ErrorCodePageHandler implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPage=new ErrorPage[3];
        errorPage[0]=new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
        errorPage[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");
        errorPage[2]=new ErrorPage(HttpStatus.BAD_REQUEST,"/400.html");
    }
}
