package iorichina.hellojava.helloface.controller;

import iorichina.hellojava.helloface.dto.AppCode;
import iorichina.hellojava.helloface.dto.WebResponseDto;
import iorichina.hellojava.helloface.exception.AppException;
import iorichina.hellojava.helloface.service.FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/hello/face/api/face/")
@CrossOrigin
public class FaceApiController {
    private static final Logger logger = LoggerFactory.getLogger(FaceApiController.class);
    @Autowired
    private FaceService faceService;

    @Value("${face.max.size:2097152}")
    private long maxSize;

    @PostMapping("detect")
    public String detect(MultipartFile file) throws AppException, IOException {
        long size = file.getSize();
        if (size <= 0 || size > maxSize) {
            return WebResponseDto.fail(AppCode.ILLEGAL_PARAMETER,
                    (maxSize / 1024 / 1024) + "M limited",
                    null
            ).toJSONString();
        }

        return WebResponseDto.success(faceService.detect(file.getBytes())).toJSONString();
    }
}
