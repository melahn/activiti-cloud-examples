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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActivitiOAuth2FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Value("${keycloak.auth-server-url}")
    protected String authServer;

    @Value("${keycloak.realm}")
    protected String realm;

    @Value("${keycloak.resource}")
    protected String resource;

    @Value("${keycloaktestuser}")
    protected String keycloaktestuser;

    @Value("${keycloaktestpassword}")
    protected String keycloaktestpassword;

    @Override
    public void apply(RequestTemplate template) {
        AccessTokenResponse details = getDetails();
        String headerValue = String.format("%s %s",
                                           details.getTokenType(),
                                           details.getToken());
        template.header(AUTHORIZATION,
                        headerValue);
    }

    protected AccessTokenResponse getDetails() {
        AccessTokenResponse token = Keycloak.getInstance(authServer,
                                                         realm,
                                                         keycloaktestuser,
                                                         keycloaktestpassword,
                                                         resource).tokenManager().getAccessToken();
        return token;
    }

//    @Override
//    public ClientHttpResponse intercept(HttpRequest httpRequest,
//                                        byte[] bytes,
//                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
//        AccessTokenResponse token = getDetails();
//        httpRequest.getHeaders().set(AUTHORIZATION, "Bearer " + token.getToken());
//        return clientHttpRequestExecution.execute(httpRequest, bytes);
//    }
}
