package iorichina.hellojava.hellospringboot.dto;

import com.fasterxml.jackson.databind.JsonNode;
import iorichina.hellojava.hellospringboot.util.JsonUtils;

/**
 * Created by iorichina on 2017/1/7.
 */
public class IpIpFindDataDTO {
    private String country;
    private String province;
    private String city;
    // 学校或单位
    private String com;
    // 运营商字段（只有购买了带有运营商版本的数据库才会有）
    private String isp;
    private Double latitude;
    private Double longitude;
    private String areaZone;
    private String timeZone;
    private String cityCode;
    private String internationalTelCode;
    private String countryCode;
    private String continentCode;

    public static IpIpFindDataDTO valueOf(String[] data) {
        IpIpFindDataDTO ipIpFindDataDTO = new IpIpFindDataDTO();
        ipIpFindDataDTO.setCountry(data[0]);
        ipIpFindDataDTO.setProvince(data[1]);
        ipIpFindDataDTO.setCity(data[2]);
        ipIpFindDataDTO.setCom(data[3]);
        ipIpFindDataDTO.setIsp(data[4]);
        ipIpFindDataDTO.setLatitude(Double.parseDouble(new String("0" + data[5])));
        ipIpFindDataDTO.setLongitude(Double.parseDouble(new String("0" + data[6])));
        ipIpFindDataDTO.setAreaZone(data[7]);
        ipIpFindDataDTO.setTimeZone(data[8]);
        ipIpFindDataDTO.setCityCode(data[9]);
        ipIpFindDataDTO.setInternationalTelCode(data[10]);
        ipIpFindDataDTO.setCountryCode(data[11]);
        ipIpFindDataDTO.setContinentCode(data[12]);

        return ipIpFindDataDTO;
    }

    public static IpIpFindDataDTO valueOf(String data) {
        JsonNode jsonArray = JsonUtils.parseObject(data);
        if (null == jsonArray) {
            return null;
        }

        IpIpFindDataDTO ipIpFindDataDTO = new IpIpFindDataDTO();
        ipIpFindDataDTO.setCountry(jsonArray.get(0).textValue());
        ipIpFindDataDTO.setProvince(jsonArray.get(1).textValue());
        ipIpFindDataDTO.setCity(jsonArray.get(2).textValue());
        ipIpFindDataDTO.setCom(jsonArray.get(3).textValue());
        ipIpFindDataDTO.setIsp(jsonArray.get(4).textValue());
        ipIpFindDataDTO.setLatitude(jsonArray.get(5).doubleValue());
        ipIpFindDataDTO.setLongitude(jsonArray.get(6).doubleValue());
        ipIpFindDataDTO.setAreaZone(jsonArray.get(7).textValue());
        ipIpFindDataDTO.setTimeZone(jsonArray.get(8).textValue());
        ipIpFindDataDTO.setCityCode(jsonArray.get(9).textValue());
        ipIpFindDataDTO.setInternationalTelCode(jsonArray.get(10).textValue());
        ipIpFindDataDTO.setCountryCode(jsonArray.get(11).textValue());
        ipIpFindDataDTO.setContinentCode(jsonArray.get(12).textValue());

        return ipIpFindDataDTO;
    }

    @Override
    public String toString() {
        return "IpIpFindDataDTO{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", com='" + com + '\'' +
                ", isp='" + isp + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", areaZone='" + areaZone + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", internationalTelCode='" + internationalTelCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", continentCode='" + continentCode + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public IpIpFindDataDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public IpIpFindDataDTO setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public IpIpFindDataDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCom() {
        return com;
    }

    public IpIpFindDataDTO setCom(String com) {
        this.com = com;
        return this;
    }

    public String getIsp() {
        return isp;
    }

    public IpIpFindDataDTO setIsp(String isp) {
        this.isp = isp;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public IpIpFindDataDTO setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public IpIpFindDataDTO setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getAreaZone() {
        return areaZone;
    }

    public IpIpFindDataDTO setAreaZone(String areaZone) {
        this.areaZone = areaZone;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public IpIpFindDataDTO setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getCityCode() {
        return cityCode;
    }

    public IpIpFindDataDTO setCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public String getInternationalTelCode() {
        return internationalTelCode;
    }

    public IpIpFindDataDTO setInternationalTelCode(String internationalTelCode) {
        this.internationalTelCode = internationalTelCode;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public IpIpFindDataDTO setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public IpIpFindDataDTO setContinentCode(String continentCode) {
        this.continentCode = continentCode;
        return this;
    }
}
