

package com.infinera.metro.alarm;

import com.infinera.metro.alarm.acceptancetest.applicationdriver.AlarmServiceApi;
import com.infinera.metro.alarm.acceptancetest.configuration.TestConfiguration;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;

    @Bean
    public TestConfiguration testConfig() {
        return new TestConfiguration();
    }

//    @Bean
//    public AlarmService_Nodes_Test addNodeAcceptanceTest() {
//        return new AlarmService_Nodes_Test();
//    }

    //Part of Application Driver
    @Bean
    public AlarmServiceApi nodeApi() {
        return new AlarmServiceApi(restTemplate(), testConfig());
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
