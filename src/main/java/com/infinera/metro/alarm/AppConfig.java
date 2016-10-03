

package com.infinera.metro.alarm;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import com.infinera.metro.alarm.acceptancetest.applicationdriver.NodeApi;
import com.infinera.metro.alarm.acceptancetest.testimplementation.AddNodeAcceptanceTest;
import com.infinera.metro.alarm.acceptancetest.configuration.TestConfiguration;

@Configuration
public class AppConfig {
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;

    @Bean
    public TestConfiguration testConfig() {
        return new TestConfiguration();
    }

    @Bean
    public AddNodeAcceptanceTest addNodeAcceptanceTest() {
        return new AddNodeAcceptanceTest(nodeApi(), testConfig());
    }

    //Part of Application Driver
    @Bean
    public NodeApi nodeApi() {
        return new NodeApi(restTemplate(), testConfig());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate(httpRequestFactory());
    }

    @Bean
    public HttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        HttpClient defaultHttpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
        return defaultHttpClient;
    }
}
