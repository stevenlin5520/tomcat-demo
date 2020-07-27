package com.steven.tomcat.test;

import com.steven.tomcat.lib.MyRequest;
import com.steven.tomcat.lib.MyResponse;
import com.steven.tomcat.lib.MyServlet;

import java.io.IOException;

/**
 * @desc
 * @author steven
 * @date 2020/7/27 12:30
 */
public class BookServlet extends MyServlet {

    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("mytomcat book get ....");
            System.out.println("BookServlet.doGet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("mytomcat book post ....");
            System.out.println("BookServlet.doPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}