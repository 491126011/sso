package cn.ym.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.ym.sso")
public class ClientApp1 {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp1.class,args);
    }
}
