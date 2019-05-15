package com.wanfangdata;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.auth.ApiKeyAuth;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName OAuthConfig
 * @Author liuwq
 * @Date 2019/5/15 8:49
 * @Version 1.0
 **/
@Configuration
public class OAuthConfig {
    @Bean
    public ApiClient apiClient() throws IOException {
        ApiClient client = new ApiClient();
        InputStream cacertReader = new FileInputStream("/var/run/secrets/kubernetes.io/serviceaccount/token");
        client.setSslCaCert(cacertReader);
        client.setBasePath("https://kubernetes/");
        client.getHttpClient().setReadTimeout(60, TimeUnit.MINUTES);
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) client.getAuthentication("BearerToken");
        FileReader fileReader = new FileReader("/var/run/secrets/kubernetes.io/serviceaccount/token");
        StringWriter stringWriter = new StringWriter();
        IOUtils.copy(fileReader,stringWriter);
        apiKeyAuth.setApiKey(new String(stringWriter.getBuffer()));
        return client;
    }
}
