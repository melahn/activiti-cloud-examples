package org.activiti.cloud.runtime.controller;

import io.swagger.model.PagedResourcesProcessDefinitionResource;
import org.activiti.cloud.runtime.ProcessInstanceControllerImplApiClientAuthorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private ProcessInstanceControllerImplApiClientAuthorized processInstanceControllerImplApiClientAuthorized;

    @RequestMapping(value = "/process-instances", method = RequestMethod.GET)
    public ResponseEntity<PagedResourcesProcessDefinitionResource> find() {

        ResponseEntity<PagedResourcesProcessDefinitionResource> pagedResourcesProcessDefinitionResource = processInstanceControllerImplApiClientAuthorized.getProcessDefinitionsUsingGET();

        return pagedResourcesProcessDefinitionResource;
    }

}
