package com.steven.tomcat;

import com.steven.tomcat.lib.MyTomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.HttpServlet;

@SpringBootApplication
public class TomcatDemoApplication extends HttpServlet {

    public static void main(String[] args) {
        SpringApplication.run(TomcatDemoApplication.class, args);
        new MyTomcat(8080).start();
    }
}
