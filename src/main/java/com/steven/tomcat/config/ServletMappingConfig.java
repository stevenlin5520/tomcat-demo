package com.steven.tomcat.config;

import com.steven.tomcat.lib.ServletMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc Servlet的请求映射表
 * @author steven
 * @date 2020/7/27 12:30
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("book","/book","com.steven.tomcat.test.BookServlet"));
        servletMappingList.add(new ServletMapping("helloWorld","/world","com.steven.tomcat.test.HelloWorldServlet"));
    }

}
