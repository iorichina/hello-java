package iorichina.hellojava.helloface.exception;

import com.alibaba.fastjson.JSON;
import iorichina.hellojava.helloface.dto.AppCode;
import iorichina.hellojava.helloface.dto.WebResponseDto;
import iorichina.hellojava.helloface.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by iorihuang on 2017/6/14.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    public static String getFullUrl(HttpServletRequest request) {
        return request.getRequestURL()
                .append("?")
                .append(request.getQueryString() == null ? JSON.toJSONString(request.getParameterMap()) : request.getQueryString())
                .toString();
    }

    @ExceptionHandler(AppException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processAppException(
            AppException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[AppException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(e).toJSONString(request, response);
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processHttpSessionRequiredException(
            HttpSessionRequiredException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[HttpSessionRequiredException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("session has missed")
                .toJSONString(request, response);
    }

    @ExceptionHandler({
            HttpMediaTypeNotAcceptableException.class
            , HttpMediaTypeNotSupportedException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processHttpMediaTypeException(
            HttpMediaTypeException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[HttpMediaTypeException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("MediaType must be one of " + MimeTypeUtils.toString(e.getSupportedMediaTypes()))
                .toJSONString(request, response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[MethodNotSupportedException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("http method " + e.getMethod() + " not in " + e.getSupportedMethods())
                .toJSONString(request, response);
    }

    @ExceptionHandler({
            BindException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processArgumentBindException(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[ArgumentBindException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("argument could not bind")
                .toJSONString(request, response);
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processArgumentIsMissingException(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[ArgumentIsMissingException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("argument is missing")
                .toJSONString(request, response);
    }

    @ExceptionHandler({
            MethodArgumentConversionNotSupportedException.class
            , ConversionNotSupportedException.class
            , MethodArgumentTypeMismatchException.class
            , TypeMismatchException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processTypeMismatchException(
            TypeMismatchException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[TypeMismatchException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("argument type mismatch")
                .toJSONString(request, response);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class
            , HttpMessageNotWritableException.class
            , HttpMessageConversionException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processHttpMessageConversionException(
            HttpMessageConversionException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info("[HttpMessageConversionException][{}]{}",
                getFullUrl(request),
                LoggerUtil.getMessage(e));

        return WebResponseDto.getResponse(AppCode.ILLEGAL_PARAMETER)
                .setMsg("arguments could not converted")
                .toJSONString(request, response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processUnknownException(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.error("[UNKNOW_ERROR][{}]",
                getFullUrl(request),
                e);

        return WebResponseDto.getResponse(AppCode.INTERNAL_SERVER_ERROR)
                .toJSONString(request, response);
    }

}
