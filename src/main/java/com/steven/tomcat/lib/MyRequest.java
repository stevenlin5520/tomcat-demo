package com.steven.tomcat.lib;

import java.io.IOException;
import java.io.InputStream;

/**
 * @desc 自定义请求对象
 * @author steven
 * @date 2020/7/27 12:30
 */
public class MyRequest {

    private String url;
    private String method;

    /**
     * 通过InputStream将请求地址，请求方法解析
     * @param inputStream
     * @throws IOException
     */
    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if((length = inputStream.read(httpRequestBytes)) > 0){
            httpRequest = new String(httpRequestBytes,0,length);
        }
        //System.out.println("httpRequest = " + httpRequest);

        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
