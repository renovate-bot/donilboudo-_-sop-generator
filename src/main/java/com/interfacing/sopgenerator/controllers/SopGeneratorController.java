package com.interfacing.sopgenerator.controllers;

import com.interfacing.sopgenerator.services.SopBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

@Controller
@EnableAutoConfiguration
public class SopGeneratorController {
    @Autowired
    private SopBuilderService builderService;

    @GetMapping("/sop/process/{processId}")
    @ResponseBody
    public String generateSop(@PathVariable @NotNull String processId) {
        //call epc to get the process based on id
        /*
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://foo.com/resource");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close();
        */

        //
        builderService.buildSOP();

        return "New SOP with process Id: " + processId;
    }
}
