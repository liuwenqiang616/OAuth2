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
    public ApiClient apiClient() throws IOException, ApiException {
           ApiClient client = new ApiClient();
        InputStream cacertReader = new FileInputStream("/var/run/secrets/kubernetes.io/serviceaccount/ca.crt");
        client.setSslCaCert(cacertReader);
        client.setBasePath("https://kubernetes.default/");
        client.getHttpClient().setReadTimeout(60, TimeUnit.MINUTES);
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) client.getAuthentication("BearerToken");
        FileReader fileReader = new FileReader("/var/run/secrets/kubernetes.io/serviceaccount/token");
        StringWriter stringWriter = new StringWriter();
        IOUtils.copy(fileReader,stringWriter);
        apiKeyAuth.setApiKey(new String(stringWriter.getBuffer()));
        io.kubernetes.client.Configuration.setDefaultApiClient(client);

        CoreV1Api coreV1Api = new CoreV1Api();
        V1NamespaceList v1NamespaceList = coreV1Api.listNamespace(null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null);
        System.out.println(v1NamespaceList);
        return client;
    }
}
