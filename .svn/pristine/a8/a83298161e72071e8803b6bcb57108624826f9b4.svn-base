package com.heli.oa.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/21
 * Time: 11:38
 * Description: 全局异常处理器
 */
public class ExceptionResolverUtil implements HandlerExceptionResolver {

    /**钉钉电软部群,监控BUG用*/
    private static String WEBHOOK_TOKEN_Test = "https://oapi.dingtalk.com/robot/send?access_token=2ce772122aae47a1d631c2d7e4c025a4c500b254213e2805fb3740d87144344c";

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception e) {
        ModelAndView modelAndView=new ModelAndView();

        errorMessageToDing(e);
        //指向到错误界面
        modelAndView.setViewName("error");

        return modelAndView;
    }

    public static void errorMessageToDing(Exception e){
        e.printStackTrace();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Test);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\"呔，BUG！哪里跑？吃俺老孙一棒！\n@18330107406\n错误信息：\n "
                + e.toString().replaceAll("\"","\\\\\"")
                +"\n出错位置：\n"
                + getErrorLog(e) + "\"\n" +
                "     },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                "18330107406" +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        System.out.println(textMsg);
        try{
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }

    public static String getErrorLog(Exception e){
        StringBuilder errorLog = new StringBuilder();
        StackTraceElement[] element = e.getStackTrace();

        for (int i = 0 ;i < element.length; i++) {
            if ("com.heli".equals(element[i].getClassName().substring(0, 8))){
                errorLog.append(/*"\t\t\t\t\t\t\t"+*/element[i].getFileName() + "-> " + element[i].getMethodName() + "->" + element[i].getLineNumber()+"\n");
            }
        }
        return errorLog.toString();
    }

}
