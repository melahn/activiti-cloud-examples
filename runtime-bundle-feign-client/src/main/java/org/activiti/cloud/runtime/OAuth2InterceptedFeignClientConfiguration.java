/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.runtime;

import java.io.IOException;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Configuration
@ConditionalOnClass({ Feign.class })
@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
public class OAuth2InterceptedFeignClientConfiguration {


//    @Bean(name = "oauth2RequestInterceptor")
    @Bean
    public RequestInterceptor getOAuth2RequestInterceptor() throws IOException {
        return new ActivitiOAuth2FeignRequestInterceptor();
    }

//    @Bean
//    @ConditionalOnBean(OAuth2ClientContext.class)
//    public RequestInterceptor activitiOAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext){
//        return new ActivitiOAuth2FeignRequestInterceptor(oauth2ClientContext);
//    }
//
//    @Bean
//    public DefaultOAuth2ClientContext oauth2ClientContext() {
//        return new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
//    }


}