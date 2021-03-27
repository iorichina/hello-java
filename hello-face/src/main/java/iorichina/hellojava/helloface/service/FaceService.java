package iorichina.hellojava.helloface.service;

import iorichina.hellojava.helloface.baidu.AipFaceHelper;
import iorichina.hellojava.helloface.dto.AppCode;
import iorichina.hellojava.helloface.dto.FaceAreaDto;
import iorichina.hellojava.helloface.exception.AppException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FaceService {
    private static final Logger logger = LoggerFactory.getLogger(FaceService.class);

    @Autowired
    private AipFaceHelper aipFaceHelper;

    public FaceAreaDto detect(byte[] bytes) throws AppException {
        logger.info("[detect][length]{}", bytes.length);
        long start = System.currentTimeMillis();
        JSONObject json = aipFaceHelper.detect(bytes, new HashMap<>());
        logger.info("[detect][spend]{}ms", System.currentTimeMillis() - start);
        logger.info("[detect][result]{}", json);

        if (null == json || json.has("error_code")) {
            logger.error("[detect]{}", json.toString());
            throw AppException.getException(AppCode.FAIL)
                    .setError(json.getString("error_msg"));
        }
        JSONObject location = json.getJSONArray("result")
                .getJSONObject(0)
                .getJSONObject("location");
        return new FaceAreaDto(location.getInt("left"),
                location.getInt("top"),
                location.getInt("width"),
                location.getInt("height"));
    }

}
