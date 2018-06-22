package com.ruc.ir;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;
import java.io.File;

public class SpeechRecognition {
    //设置APPID/AK/SK
    public static final String APP_ID = "11420280";
    public static final String API_KEY = "PHoiZulMUMGepN4O1CvwC0in";
    public static final String SECRET_KEY = "BOt3N9YrNGQAgGx8qd7WDWc9lhXLmD13";

    public String getRecognitionResult(){
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.asr("H:\\Downloads\\Chrome\\audio\\我的坚果云\\myRecording00.wav", "pcm", 16000, null);
        System.out.println(res.toString(2));

        //删除音频文件
        String filePath = "H:\\Downloads\\Chrome\\audio\\我的坚果云\\";
        String fileName = "myRecording00.wav";

        File file = new File(filePath + fileName);
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }

        return  res.toString(2);

    }
}
