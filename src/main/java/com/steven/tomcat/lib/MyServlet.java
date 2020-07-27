package com.steven.tomcat.lib;

/**
 * @desc 自定义请求服务抽象类
 * @author steven
 * @date 2020/7/27 12:30
 */
public abstract class MyServlet {

    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse){
        if("POST".equalsIgnoreCase(myRequest.getMethod())){
            //System.out.println("Myservlet is executing service of 'GET' ......");
            doPost(myRequest,myResponse);
        }else if("GET".equalsIgnoreCase(myRequest.getMethod())){
            //System.out.println("Myservlet is executing service of 'POST' ......");
            doGet(myRequest,myResponse);
        }
    }

}
