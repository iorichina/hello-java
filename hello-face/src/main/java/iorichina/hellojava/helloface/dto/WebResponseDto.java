package iorichina.hellojava.helloface.dto;

import com.alibaba.fastjson.JSON;
import iorichina.hellojava.helloface.constant.ViewEnum;
import iorichina.hellojava.helloface.exception.AppException;
import iorichina.hellojava.helloface.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Created by iorihuang on 2017/1/6.
 */
public class WebResponseDto<T> {
    private int code;
    private String msg;
    private int time;
    private T data;

    public WebResponseDto() {
        time = DateUtil.getTimestamp();
    }

    @SuppressWarnings("unchecked")
    public static <T> WebResponseDto<T> success(T data) {
        return getResponse(AppCode.SUCCESS).setData(data);
    }

    @SuppressWarnings("unchecked")
    public static <T> WebResponseDto<T> fail(AppCode appCode, String msg, T data) {
        return getResponse(appCode, msg).setData(data);
    }

    public static WebResponseDto getResponse(AppException e) {
        return new WebResponseDto().setCode(e.getCode()).setMsg(e.getError());
    }

    public static WebResponseDto getResponse(AppCode serviceRetEnum) {
        return new WebResponseDto().setCode(serviceRetEnum.getCode()).setMsg(serviceRetEnum.getDesc());
    }

    public static WebResponseDto getResponse(AppCode serviceRetEnum, String msg) {
        msg = null == msg ? serviceRetEnum.getDesc() : msg;
        return new WebResponseDto().setCode(serviceRetEnum.getCode()).setMsg(msg);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String toJSONString() {
        return toJSONString(
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse()
        );
    }

    public String toJSONString(HttpServletRequest request, HttpServletResponse response) {
        String result = JSON.toJSONString(this);

        String callback = request.getParameter("jsonpcallback");

        if (StringUtils.isBlank(callback)) {
            if (null != response) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            }

            return result;
        }

        if (null != response) {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.TEXT_HTML_VALUE);
        }
        // post + iframe : cors
        if ("scriptcallback".equals(callback) && "POST".equals(request.getMethod().toUpperCase())) {
            return "<script>window.name=\""
                    + result.replace("\"", "\\\"")
                    + "\";</script>";
        }

        return callback + "(" + result + ")";
    }

    public ModelAndView toModelAndView(ViewEnum view) {
        return new ModelAndView(view.viewName)
                .addObject("data", data)
                .addObject("time", time)
                .addObject("code", code)
                .addObject("msg", msg);
    }

    /**
     * @return
     */
    public int getCode() {
        return code;
    }

    public WebResponseDto setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public WebResponseDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public WebResponseDto setData(T data) {
        this.data = data;
        return this;
    }

}
