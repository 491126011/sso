package cn.ym.sso.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sso.server")
public class SsoConfig {

    private String url;

    private String loginPath;



}
