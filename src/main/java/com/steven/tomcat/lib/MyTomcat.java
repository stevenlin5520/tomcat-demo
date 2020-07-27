package com.steven.tomcat.lib;

import com.steven.tomcat.config.ServletMappingConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc Tomcat处理逻辑：先初始化servlet映射的请求url列表；然后建立
 * @author steven
 * @date 2020/7/27 12:30
 */
public class MyTomcat {

    private int port = 8080;
    private Map<String,String> urlServletMap = new HashMap<>(16);

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start(){
        // 初始化servlet映射的请求url列表
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("\n\n MyTomcat is started ...... \n\n");

            while (true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);

                // 请求转发
                dispatch(myRequest,myResponse);

                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 请求转发(将请求的url放入到映射表中查找，然后利用反射原理实例化请求类，再调用相关服务)
     * @param myRequest
     * @param myResponse
     */
    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        //System.out.println("MyTomcat is dispatch request's url ......");
        String clazz = urlServletMap.get(myRequest.getUrl());

        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(myRequest,myResponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化servlet映射的请求url列表
     */
    private void initServletMapping(){
        //System.out.println("Servlet request mapping ......");
        for(ServletMapping servletMapping : ServletMappingConfig.servletMappingList){
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }


    public static void main(String[] args) {
        new MyTomcat(8000).start();
    }
}
