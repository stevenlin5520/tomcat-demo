package com.steven.tomcat.test;

import com.steven.tomcat.lib.MyRequest;
import com.steven.tomcat.lib.MyResponse;
import com.steven.tomcat.lib.MyServlet;

import java.io.IOException;

/**
 * @desc MyServlet实现类，进行测试
 * @author steven
 * @date 2020/7/27 12:30
 */
public class HelloWorldServlet extends MyServlet {

    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("hello world get request .....");
            System.out.println("HelloWorldServlet.doGet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("hello world post request .....");
            System.out.println("HelloWorldServlet.doPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}