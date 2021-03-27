package iorichina.hellojava.helloface.controller;

import iorichina.hellojava.helloface.constant.ViewEnum;
import iorichina.hellojava.helloface.dto.WebResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/hello/face/ui/face/")
public class FaceUiController {
    @GetMapping("detect.html")
    public ModelAndView detect() throws Exception {
        return WebResponseDto.success(null)
                .toModelAndView(ViewEnum.FACE_DETECT);
    }
}
