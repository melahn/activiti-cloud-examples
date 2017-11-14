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
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ActivitiOAuth2FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private static final String AUTHORIZATION_HEADER = "Authorization";


//    private final OAuth2ClientContext oauth2ClientContext;
//
//    public ActivitiOAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
//        Assert.notNull(oauth2ClientContext,
//                       "Context can not be null");
//        this.oauth2ClientContext = oauth2ClientContext;
//    }

//    @Override
//    public void apply(RequestTemplate template) {
//
//
//
//
//        if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
//            System.out.println("The Authorization token has been already set");
//        } else if (oauth2ClientContext.getAccessTokenRequest().getExistingToken() == null) {
//            System.out.println("Can not obtain existing token for request, if it is a non secured request, ignore.");
//        } else {
//            System.out.println("Constructing Header {} for Token {}" + AUTHORIZATION_HEADER +  BEARER_TOKEN_TYPE);
//            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
//                                                                oauth2ClientContext.getAccessTokenRequest().getExistingToken().toString()));
//        }
//    }


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
        //httpRequest.getHeaders().set(AUTHORIZATION_HEADER, "Bearer " + token.getToken());
            template.header(AUTHORIZATION,
                        headerValue);
    }

    protected AccessTokenResponse getDetails() {

        System.out.println("somehting");
        AccessTokenResponse token = Keycloak.getInstance(authServer, realm, keycloaktestuser, keycloaktestpassword, resource).tokenManager().getAccessToken();

        return token;
    }
}
