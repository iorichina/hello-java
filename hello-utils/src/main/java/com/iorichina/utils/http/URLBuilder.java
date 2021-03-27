package com.iorichina.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class: URLBuilder
 * User: Gilad Tiram
 * Date: 6/12/13
 * Time: 4:02 PM
 * <p/>
 * <p/>
 * Utility that helps to build URL String
 */
public class URLBuilder {
    private static final Logger logger = LoggerFactory.getLogger(URLBuilder.class);
    public static final String ENCODING_UTF8 = "UTF-8";

    /**
     * Build URL string from Map of params. Nested Map and Collection is also supported
     *
     * @param params   Map of params for constructing the URL Query String
     * @param encoding encoding type. If not set the "UTF-8" is selected by default
     * @return String of type key=value&...key=value
     * @throws java.io.UnsupportedEncodingException if encoding isnot supported
     */
    public static String httpBuildQuery(Map<String, Object> params, String encoding) {
        if (isEmpty(encoding)) {
            encoding = "UTF-8";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append('&');
            }

            String name = entry.getKey();
            Object value = entry.getValue();


            if (value instanceof Map) {
                List<String> baseParam = new ArrayList<String>();
                baseParam.add(name);
                String str = buildUrlFromMap(baseParam, (Map) value, encoding);
                sb.append(str);

            } else if (value instanceof Collection) {
                List<String> baseParam = new ArrayList<String>();
                baseParam.add(name);
                String str = buildUrlFromCollection(baseParam, (Collection) value, encoding);
                sb.append(str);

            } else {
                sb.append(encodeParam(name));
                sb.append("=");
                sb.append(encodeParam(value));
            }


        }
        return sb.toString();
    }

    private static String buildUrlFromMap(List<String> baseParam, Map<Object, Object> map, String encoding) {
        StringBuilder sb = new StringBuilder();
        String token;

        //Build string of first level - related with params of provided Map
        for (Map.Entry<Object, Object> entry : map.entrySet()) {

            if (sb.length() > 0) {
                sb.append('&');
            }

            String name = String.valueOf(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Map) {
                List<String> baseParam2 = new ArrayList<String>(baseParam);
                baseParam2.add(name);
                String str = buildUrlFromMap(baseParam2, (Map) value, encoding);
                sb.append(str);

            } else if (value instanceof List) {
                List<String> baseParam2 = new ArrayList<String>(baseParam);
                baseParam2.add(name);
                String str = buildUrlFromCollection(baseParam2, (List) value, encoding);
                sb.append(str);
            } else {
                token = getBaseParamString(baseParam) + "[" + name + "]=" + encodeParam(value);
                sb.append(token);
            }
        }

        return sb.toString();
    }

    private static String buildUrlFromCollection(List<String> baseParam, Collection coll, String encoding) {
        StringBuilder sb = new StringBuilder();
        String token;
        if (!(coll instanceof List)) {
            coll = new ArrayList(coll);
        }
        List arrColl = (List) coll;

        //Build string of first level - related with params of provided Map
        for (int i = 0; i < arrColl.size(); i++) {

            if (sb.length() > 0) {
                sb.append('&');
            }

            Object value = (Object) arrColl.get(i);
            if (value instanceof Map) {
                List<String> baseParam2 = new ArrayList<String>(baseParam);
                baseParam2.add(String.valueOf(i));
                String str = buildUrlFromMap(baseParam2, (Map) value, encoding);
                sb.append(str);

            } else if (value instanceof List) {
                List<String> baseParam2 = new ArrayList<String>(baseParam);
                baseParam2.add(String.valueOf(i));
                String str = buildUrlFromCollection(baseParam2, (List) value, encoding);
                sb.append(str);
            } else {
                token = getBaseParamString(baseParam) + "[" + i + "]=" + encodeParam(value);
                sb.append(token);
            }
        }

        return sb.toString();
    }


    private static String getBaseParamString(List<String> baseParam) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseParam.size(); i++) {
            String s = baseParam.get(i);
            if (i == 0) {
                sb.append(s);
            } else {
                sb.append("[" + s + "]");
            }
        }
        return sb.toString();
    }

    /**
     * Check if String is either empty or null
     *
     * @param str string to check
     * @return true if string is empty. Else return false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    private static String encodeParam(Object param) {
        try {
            return URLEncoder.encode(String.valueOf(param), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encodeParam {}", param, e);
            return String.valueOf(param);
        }
    }


    /**
     * url encode 星域
     *
     * @param text
     * @param enc
     * @return
     */
    public static String UrlEncode(String text, String enc) {
        String result = "";
        try {
            result = URLEncoder.encode(text, enc);
        } catch (UnsupportedEncodingException e) {
            logger.warn("UrlEncode fail with encoding {}: {}", enc, text, e);
        }

        return result.replace("%21", "!")
                .replace("%40", "@")
                .replace("%24", "$")
                .replace("%7E", "~")
                .replace("%2C", ",")
                .replace("%27", "'")
                .replace("%28", "(")
                .replace("%29", ")")
                .replace("+", "%20");
    }

    public static String UrlDecode(String text, String enc) {
        String result = "";
        try {
            result = URLDecoder.decode(text, enc);
        } catch (UnsupportedEncodingException e) {
            logger.warn("UrlDecode fail with encoding {}: {}", enc, text, e);
        }
        return result;
    }

    public static String createLinkEncodeString(Map<String, Object> params, String enc) {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> sets = params.entrySet();
        for (Map.Entry<String, Object> e : sets) {
            sb.append(UrlEncode(e.getKey(), enc));
            sb.append("=");
            sb.append(UrlEncode(e.getValue().toString(), enc).replace("=", "%3D"));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}