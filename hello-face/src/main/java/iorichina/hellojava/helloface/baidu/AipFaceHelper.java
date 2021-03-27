package iorichina.hellojava.helloface.baidu;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AipFaceHelper implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(AipFaceHelper.class);

    @Value("${baidu.appid}")
    private String APP_ID = "";
    @Value("${baidu.appkey}")
    private String API_KEY = "";
    @Value("${baidu.secretkey}")
    private String SECRET_KEY = "";

    private AipFace client;

    /**
     * {
     *  "result": [{
     *      "roll": 1.8166919946671,
     *      "location": {
     *          "top": 56,
     *          "left": 51,
     *          "width": 86,
     *          "height": 99
     *      },
     *      "face_probability": 0.99531674385071,
     *      "rotation_angle": 2,
     *      "pitch": -14.284966468811,
     *      "yaw": -5.8611116409302
     *  }],
     *  "log_id": 334486709,
     *  "result_num": 1
     * }
     *
     * {
     *  "error_code": 216616,
     *  "error_msg": "SDK123"
     * }
     * @param bytes
     * @param options
     * @return
     */
    public JSONObject detect(byte[] bytes, HashMap<String, String> options) {
        return client.detect(bytes, options);
    }

    public JSONObject detect(String path) {
        return client.detect(path, new HashMap<>());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("[AipFace]{}, {}, {}", APP_ID, API_KEY, SECRET_KEY);
        // 初始化一个FaceClient
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }
}
