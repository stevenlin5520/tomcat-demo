package com.steven.tomcat.lib;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @desc 自定义响应对象
 * @author steven
 * @date 2020/7/27 12:30
 */
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 自定义HTTP协议的输出
     * @param content
     * @throws IOException
     */
    public void write(String content) throws IOException {

        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }

}
