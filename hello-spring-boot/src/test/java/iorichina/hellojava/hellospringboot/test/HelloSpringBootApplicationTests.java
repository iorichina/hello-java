package iorichina.hellojava.hellospringboot.test;

import iorichina.hellojava.hellospringboot.HelloSpringBootApplication;
import iorichina.hellojava.hellospringboot.component.IpIpService;
import iorichina.hellojava.hellospringboot.dto.IpIpFindDTO;
import iorichina.hellojava.hellospringboot.exception.IpIpException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.LongAdder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = HelloSpringBootApplication.class)
public class HelloSpringBootApplicationTests {
    @Autowired
    IpIpService ipIpBO;

    private LongAdder longAdder;

    @Test
    public void ipip() {
        try {
            IpIpFindDTO ipIpFindDTO = ipIpBO.findByIp("14.20.165.3");
            System.out.println(ipIpFindDTO);
            assertThat(ipIpFindDTO.getData()).isNotNull();
            assertThat(ipIpFindDTO.getData().getCity()).isEqualTo("中山");
        } catch (IpIpException e) {
            e.printStackTrace();
        }
    }

}
