package com.steven.tomcat.config;

import com.steven.tomcat.lib.ServletMapping;

import java.io.File;
import java.util.*;

/**
 * @author steven
 * @desc 启动加载Servlet，将映射写入到内存中
 * @date 2021/4/7 17:43
 */
public class InitLoadServlet {
    //完整映射表
    public static List<ServletMapping> MAPPING_LIST = new ArrayList<>(16);
    //http请求映射表
    public static Map<String,String> URL_MAPPIING_MAP = new HashMap<>(10);
    //唯一的映射Set
    private static Set<String> URL_KEY_SET = new HashSet<>();
    private final static List<String> upperCaseList = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N",
            "O","P","Q","R","S","T","U","V","W","X","Y","Z");

    public static void init() throws Exception{
        //项目根路径
        String path = InitLoadServlet.class.getResource("/").getPath();
        if(path.startsWith("/")){
            path = path.replaceFirst("/","");
        }else if(path.startsWith("file:/")){
            path = path.replaceFirst("file:/","");
        }
        System.out.println("path = " + path);
        File file = new File(path);
        if(!file.exists()){
            System.out.println("fail: tomcat init Servlet fail");
            return;
        }

        System.out.println("tomcat init Servlet mapping ...");
        File[] files = file.listFiles();
        for (File listFile : files) {
            scanServlet(listFile);
        }
    }

    /**
     * 递归扫描文件，并放入列表中
     * @param file
     */
    private static void scanServlet(File file){
        try{
            if(!file.exists()){
                return;
            }
            String filePath = file.getPath();

            if(file.isFile()){
                if(!file.getName().contains(".class")){
                    return;
                }
                String name = file.getName();

                String className = name.substring(0,name.length()-6);
//                String className = name.substring(0,name.length()-5);
//                String packageClass = filePath.substring(filePath.indexOf("com"), filePath.length() - 5);
                String packageClass = filePath.substring(filePath.indexOf("com"), filePath.length() - 6);
                packageClass = packageClass.replaceAll("/",".").replaceAll("\\\\",".");

                Class<?> aClass = Class.forName(packageClass);
                final Class<?> superclass = aClass.getSuperclass();
                if(superclass == null){
                    return;
                }
                String superClass = superclass.getName();
                if ("com.steven.tomcat.lib.MyServlet".equals(superClass)) {
                    String url = generateHttpUrl(className);
                    if(URL_KEY_SET.contains(url)){
                        throw new Exception("请求映射路径重复："+url);
                    }
                    System.out.println("Servlet: "+packageClass+"  mapped to url:"+url);
                    MAPPING_LIST.add(new ServletMapping(className,url,packageClass));
                    URL_MAPPIING_MAP.put("/"+url,packageClass);
                    URL_KEY_SET.add(url);
                }

                return;
            }
            File[] files = file.listFiles();
            if(files != null && files.length > 0){
                for (File itemFile : files) {
                    scanServlet(itemFile);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 生成驼峰命名的请求方法
     * @param className 类名
     * @return
     */
    private static String generateHttpUrl(String className){
        className = className.replace("Servlet","");
        String prefix = className.split("")[0];
        if(upperCaseList.contains(prefix)){
            className = className.replace(prefix,prefix.toLowerCase());
        }

        return className;
    }
}
