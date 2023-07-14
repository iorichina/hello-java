package iorichina.hellojava.hellobaidu;

import okhttp3.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://ai.baidu.com/ai-doc/OCR/Al1zvpylt">表格文字识别V2</a>
 */
class OcrTableV2 {
    public static final String API_KEY = "PuyGEOFnXqGM6jMGcfUnoQQy";
    public static final String SECRET_KEY = "pS3G0EGw1u3pIg2HGGdMQz5r56MREHFD";
    static byte[] newLine = System.getProperty("line.separator").getBytes();

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().readTimeout(10L, TimeUnit.SECONDS).build();

    public static void main(String[] args) throws IOException {
        new OcrTableV2().ocr();
    }

    private void ocr() throws IOException {
        String path = "F:\\online\\biaoge\\";
        // pdf_file 可以通过 getFileContentAsBase64("C:\fakepath\中成药部分.pdf") 方法获取,如果Content-Type是application/x-www-form-urlencoded时,第二个参数传true
        String pdf_file = getFileContentAsBase64(path + "中成药部分.pdf", true);
        String accessToken = getAccessToken();
        try (FileOutputStream csv = new FileOutputStream(path + "中成药部分.csv")) {
            csv.write("药品分类代码,药品分类,药品分类等级,编号,药品名称,备注".getBytes());
            csv.write(newLine);
            for (int i = 1; i <= 5; i++) {
                handlePage(path, pdf_file, i, accessToken, csv);
            }
        }
        System.out.println("done");
    }

    private void handlePage(String path, String pdf_file, int pageNum, String accessToken, OutputStream csv) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create("pdf_file=" + pdf_file + "&return_excel=true&pdf_file_num=" + pageNum, mediaType);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/ocr/v1/table?access_token=" + accessToken)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        ResponseBody responseBody = Optional.ofNullable(response).map(Response::body).orElseThrow();
        String string = responseBody.string();
//            System.out.println(string);
        String excel_file = null;
        try {
            excel_file = new JSONObject(string).getString("excel_file");
        } catch (JSONException e) {
            System.out.println(string);
            throw e;
        }
        System.out.println("handlePage:" + pageNum);

        byte[] decode = Base64.getDecoder().decode(excel_file);

        try (FileOutputStream fos = new FileOutputStream(path + "中成药部分-" + pageNum + ".xlsx")) {
            fos.write(decode);

            handlePageWorkbook(decode, csv);
        }
    }

    String latestCode = "";
    String latestType = "";

    private void handlePageWorkbook(byte[] decode, OutputStream csv) throws IOException {
        //excel解析
        XSSFWorkbook wb = new XSSFWorkbook(new ByteArrayInputStream(decode));
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //药品分类代码
            String code = HelloUtils.content(SheetUtil.getCellWithMerges(sheet, row.getRowNum(), 0));
            if (code.contains("药品分类代码")) {
                continue;
            }
            if (StringUtil.isNotBlank(code)) {
                latestCode = code;
            }

            //药品分类
            String type = null;
            for (int i = 1; i <= 4; i++) {
                type = HelloUtils.content(SheetUtil.getCellWithMerges(sheet, row.getRowNum(), i));
                if (StringUtil.isNotBlank(type)) {
                    latestType = type;
                }
            }
            if (null != type) {
                continue;
            }

            //药品分类等级
            String level = HelloUtils.content(SheetUtil.getCellWithMerges(sheet, row.getRowNum(), 5));

            //编号
            String sn = HelloUtils.content(SheetUtil.getCellWithMerges(sheet, row.getRowNum(), 6));

            //药品名称
            String name = HelloUtils.content(SheetUtil.getCellWithMerges(sheet, row.getRowNum(), 7));

            //备注
            String desc = HelloUtils.content(SheetUtil.getCellWithMerges(sheet, row.getRowNum(), 8));

            String line = latestCode + "," + latestType + "," + level + "," + sn + "," + name + "," + desc;
            csv.write(line.getBytes());
            csv.write(newLine);
        }
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