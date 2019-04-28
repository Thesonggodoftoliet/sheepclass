package com.sheepclass.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class pickWord {

    public static byte[] ReadImages(String images) {
        File f = new File(images);
        BufferedImage bi;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 获取图片的后缀名
            String name = f.getName().substring(f.getName().indexOf(".") + 1, f.getName().length());
            bi = ImageIO.read(f);
            ImageIO.write(bi, name, baos); // 经测试转换的图片是格式这里就什么格式，否则会失真
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> pickWordString(String spicture){
        String host = "http://spwzsb.market.alicloudapi.com";
        String path = "/word/video/recognize";
        String method = "POST";
        String appcode = "373b2cc3e84f45acbeb8b88096e4b400";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        //bodys.put("contentBase64", "contentBase64");

        //图片路径
        String picture = spicture;
        //读取图片文件
        byte[] content = ReadImages(picture);
        //将2进转换成ENCODEBASE64
        String encode = new String(encodeBase64(content));
        System.out.println("encode"+encode);
        bodys.put("contentBase64", encode);
        //返回值
        List<String> wordlist = new ArrayList<String>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            // System.out.println(response.toString());
            //获取response的body

            JSONObject jsonobject = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            JSONArray jsonarray = (JSONArray) jsonobject.get("data");
            System.out.println(jsonarray.size());

            for(int i = 0 ; i <jsonarray.size();i++) {
                Object o =(jsonarray.get(i));
                JSONObject jsont = JSONObject.fromObject(o);
                // 去掉多余的符号
                String contents = jsont.get("contents").toString().replace("[", "");
                contents = contents.replace("\"", "");
                contents = contents.replace(",", "");
                contents = contents.replace("]", "");
                wordlist.add(contents);
                System.out.println(wordlist.get(i));
            }
            // System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordlist;
    }

    public static void main(String[] args) {
        String host = "http://spwzsb.market.alicloudapi.com";
        String path = "/word/video/recognize";
        String method = "POST";
        String appcode = "373b2cc3e84f45acbeb8b88096e4b400";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        //bodys.put("contentBase64", "contentBase64");

        //图片路径
        String picture = "/Users/chenan/sheepclass/web/SCREEN-SHOT/history_sanguomp4103.jpg";
        //读取图片文件
        byte[] content = ReadImages(picture);
        //将2进转换成ENCODEBASE64
        String encode = new String(encodeBase64(content));
        System.out.println("encode"+encode);
        bodys.put("contentBase64", encode);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
