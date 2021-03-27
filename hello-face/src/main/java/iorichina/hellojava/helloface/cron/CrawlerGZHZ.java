package iorichina.hellojava.helloface.cron;

import org.apache.http.Consts;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.jetty.http.HttpHeader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CrawlerGZHZ {
    public void yuYue() throws IOException {
        String uri = "http://gzhzyw.gzjd.gov.cn/hzzxwx/common/ajaxSubmitYuYueListByZwwYuYue.do";
        CloseableHttpClient client = HttpClients.custom().build();
        HttpPost request = new HttpPost(uri);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        request.setHeader("Origin", "http://gzhzyw.gzjd.gov.cn");
        request.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");
        request.setHeader("X-Requested-With", "XMLHttpRequest");
        request.setHeader(HttpHeaders.REFERER, "http://gzhzyw.gzjd.gov.cn/hzzxwx/hzAdvisory/birthRegion.do?bis.id=228&redirectType=1&openId=orEamjgIeRjOktfX7jy57giT2fM8");
        request.setHeader(HttpHeader.COOKIE.name(), "JSESSIONID=BB37CD61EE6E1837128D50994A852014.HzzxWebAppCluster2");
        request.setEntity(new StringEntity("account=gzga_th&password=123&BizID=A&Date=2017-12-29&Time=15:00&IDCard=450222198612251329&WeChat=orEamjgIeRjOktfX7jy57giT2fM8-qwerASDF-2017114164033&Phone=18022411747&PersonName=林水英&ITEM_CODE=440100-172-FW-035-02&ITEM_NAME=投靠父母", ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), Consts.UTF_8)));
        client.execute(request);
    }
}
