package iorichina.hellojava.hellospringboot.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by iorihuang on 2017/1/7.
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
        JSONObject jsonObject = JSON.parseObject(body);
        if (!jsonObject.containsKey("ret")) {
            return null;
        }

        IpIpFindDTO ipIpFindDTO = new IpIpFindDTO();
        ipIpFindDTO.setRet(jsonObject.getString("ret"))
                .setMsg(jsonObject.getString("msg"))
                .setData(IpIpFindDataDTO.valueOf(jsonObject.getString("data")));

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
