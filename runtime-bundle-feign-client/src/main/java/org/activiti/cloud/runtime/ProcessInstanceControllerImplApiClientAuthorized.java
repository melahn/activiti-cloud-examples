package org.activiti.cloud.runtime;

import io.swagger.api.ProcessDefinitionControllerImplApi;
import io.swagger.configuration.ClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${apiDocumentation.name:apiDocumentation}", url="${apiDocumentation.url:http://activiti-cloud-sso-idm-kub:30080/rb-my-app}", configuration = ClientConfiguration.class)
public interface ProcessInstanceControllerImplApiClientAuthorized extends ProcessDefinitionControllerImplApi{

}
