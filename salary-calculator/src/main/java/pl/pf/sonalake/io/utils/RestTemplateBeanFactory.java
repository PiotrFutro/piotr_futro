package pl.pf.sonalake.io.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * klasa fabrykująca beany restTemplate
 *
 * @author pfutro
 */

@Configuration
public class RestTemplateBeanFactory {

    @Bean
    @Autowired
    public RestTemplate nbpRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return getRestTemplate(restTemplateBuilder);
    }


    private RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);

        return restTemplateBuilder
                .requestFactory(() -> {
                    return new BufferingClientHttpRequestFactory(requestFactory);
                })
                .build();

    }

}
