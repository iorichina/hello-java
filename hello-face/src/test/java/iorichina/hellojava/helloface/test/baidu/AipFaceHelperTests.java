package iorichina.hellojava.helloface.test.baidu;

import iorichina.hellojava.helloface.baidu.AipFaceHelper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AipFaceHelperTests {
    private static final Logger logger = LoggerFactory.getLogger(AipFaceHelperTests.class);

    @Autowired
    private AipFaceHelper aipFaceHelper;

    @Test
    public void detectTest() {
        String methodName = new Exception().getStackTrace()[0].getMethodName();
        JSONObject res = aipFaceHelper.detect(
                getClass()
                        .getClassLoader()
                        .getResource("face1.gif")
                        .getPath()
        );
        Assert.assertNotNull(res);
        logger.info("[{}][res]\n{}", methodName, res.toString(2));
        Assert.assertFalse(res.has("error_code"));
        Assert.assertTrue(res.getInt("result_num") > 0);
    }

}
