package iorichina.hellojava.hellospringboot.dto;

import com.fasterxml.jackson.databind.JsonNode;
import iorichina.hellojava.hellospringboot.util.JsonUtils;

/**
 * Created by iorichina on 2017/1/7.
 */
public class IpIpFindDTO {
    private String ret;
    private String msg;
    private IpIpFindDataDTO data;

    public static IpIpFindDTO valueOf(String[] body) {
        IpIpFindDTO ipIpFindDTO = new IpIpFindDTO();
        ipIpFindDTO.setRet("ok")
                .setData(IpIpFindDataDTO.valueOf(body));

        return ipIpFindDTO;
    }

    public static IpIpFindDTO valueOf(String body) {
        JsonNode jsonObject = JsonUtils.parseObject(body);
        if (null == jsonObject || !jsonObject.hasNonNull("ret")) {
            return null;
        }

        IpIpFindDTO ipIpFindDTO = new IpIpFindDTO();
        ipIpFindDTO.setRet(jsonObject.get("ret").textValue())
                .setMsg(jsonObject.get("msg").textValue())
                .setData(IpIpFindDataDTO.valueOf(jsonObject.get("data").textValue()));

        return ipIpFindDTO;
    }

    @Override
    public String toString() {
        return "IpIpFindDTO{" +
                "ret='" + ret + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getRet() {
        return ret;
    }

    public IpIpFindDTO setRet(String ret) {
        this.ret = ret;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public IpIpFindDTO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public IpIpFindDataDTO getData() {
        return data;
    }

    public IpIpFindDTO setData(IpIpFindDataDTO data) {
        this.data = data;
        return this;
    }
}
