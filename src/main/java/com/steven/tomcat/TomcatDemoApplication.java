package com.steven.tomcat;

import com.steven.tomcat.config.InitLoadServlet;
import com.steven.tomcat.lib.MyTomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.HttpServlet;

@SpringBootApplication
public class TomcatDemoApplication extends HttpServlet {

    public static void main(String[] args) {
        SpringApplication.run(TomcatDemoApplication.class, args);

        /**
         * 初始化servlet映射的请求url列表
         */
        try {
            InitLoadServlet.init();
        }catch(Exception e){
            e.printStackTrace();
        }

        new MyTomcat(8088).start();
    }
}
