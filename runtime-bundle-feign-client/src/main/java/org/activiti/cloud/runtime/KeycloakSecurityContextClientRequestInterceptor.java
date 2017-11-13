package org.activiti.cloud.runtime;

import java.io.IOException;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityContextClientRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";


    @Value("${keycloak.auth-server-url}")
    protected String authServer;

    @Value("${keycloak.realm}")
    protected String realm;

    @Value("${keycloak.resource}")
    protected String resource;

    @Value("${keycloakclientuser}")
    protected String keycloaktestuser;

    @Value("${keycloakclientpassword}")
    protected String keycloaktestpassword;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        AccessTokenResponse token = getAccessTokenResponse();
        httpRequest.getHeaders().set(AUTHORIZATION_HEADER, "Bearer " + token.getToken());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    private AccessTokenResponse getAccessTokenResponse() {
        return Keycloak.getInstance(authServer, realm, keycloaktestuser, keycloaktestpassword, resource).tokenManager().getAccessToken();
    }


    public void setKeycloaktestuser(String keycloaktestuser) {
        this.keycloaktestuser = keycloaktestuser;
    }

    public String getKeycloaktestuser(){
        return keycloaktestuser;
    }
}