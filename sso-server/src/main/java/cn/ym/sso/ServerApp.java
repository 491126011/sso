package cn.ym.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.ym.sso")
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class,args);
    }
}
