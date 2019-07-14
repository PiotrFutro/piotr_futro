package pl.pf.sonalake.io.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * klasa fabrykująca budowane beany restTemplate
 *
 * @author pfutro
 */
@Configuration
public class RestTemplateBeanFactory {

    @Bean
    @Autowired
    public RestTemplate nbpRestTemplate(RestTemplateBuilder restTemplateBuilder,
                                        @Value("${client.read.timeout}") Integer readTimeout,
                                        @Value("${client.connection.timeout}") Integer connectionTimeout) {
        return getRestTemplate(restTemplateBuilder, readTimeout, connectionTimeout);
    }


    private RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder, Integer readTimeout, Integer connectionTimeout) {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);

        return restTemplateBuilder
                .requestFactory(() -> {
                    return new BufferingClientHttpRequestFactory(requestFactory);
                })
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .build();

    }

}
