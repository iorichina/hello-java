package iorichina.hellojava.hellobaidu;

import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://ai.baidu.com/ai-doc/OCR/Al1zvpylt">表格文字识别V2</a>
 */
class OcrTableV2 {
    public static final String API_KEY = "PuyGEOFnXqGM6jMGcfUnoQQy";
    public static final String SECRET_KEY = "pS3G0EGw1u3pIg2HGGdMQz5r56MREHFD";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().readTimeout(10L, TimeUnit.SECONDS).build();

    public static void main(String[] args) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        // pdf_file 可以通过 getFileContentAsBase64("C:\fakepath\中成药部分.pdf") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
        String pdf_file = getFileContentAsBase64("D:\\Users\\iorihuang\\Desktop\\中成药部分.pdf", true);
        String accessToken = getAccessToken();
        for (int i = 1; i <= 5; i++) {
            RequestBody body = RequestBody.create(mediaType, "pdf_file=" + pdf_file + "&return_excel=true&pdf_file_num=" + i);
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/rest/2.0/ocr/v1/table?access_token=" + accessToken)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            String string = response.body().string();
//            System.out.println(string);
            String excel_file = new JSONObject(string).getString("excel_file");
            System.out.println("writing page:" + i);
            File output = new File("D:\\Users\\iorihuang\\Desktop\\中成药部分-" + i + ".xlsx");
            if (!output.exists()) {
                output.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(output);
            byte[] decode = Base64.getDecoder().decode(excel_file);
            fos.write(decode);
            fos.close();
        }
        System.out.println("done");
        //todo merge
    }

    /**
     * 获取文件base64编码
     *
     * @param path      文件路径
     * @param urlEncode 如果Content-Type是application/x-www-form-urlencoded时,传true
     * @return base64编码信息，不带文件头
     * @throws IOException IO异常
     */
    static String getFileContentAsBase64(String path, boolean urlEncode) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(path));
        String base64 = Base64.getEncoder().encodeToString(b);
        if (urlEncode) {
            base64 = URLEncoder.encode(base64, "utf-8");
        }
        return base64;
    }


    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }

}