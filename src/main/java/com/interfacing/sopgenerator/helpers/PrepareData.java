package com.interfacing.sopgenerator.helpers;

import com.interfacing.sopgenerator.domain.*;
import com.interfacing.sopgenerator.domain.Process;

import java.util.ArrayList;
import java.util.List;

public class PrepareData {

    public Process buildProcess() {
        Process process = new Process();
        process.setName("Perform IT Pre-boarding Activities");
        process.setVersion("1.0");
        process.setStatus("In Progress");
        process.setGoal("This value-added activity’s objective is to provide new hires with the equipment that they need as quickly as possible, and as inexpensively as possible.");
        process.setDescription("This value-added activity’s objective is to provide new hires with the equipment that they need as quickly as possible, and as inexpensively as possible.");

        buildActivity(process);

        return process;
    }

    private void buildActivity(Process process) {
        List<String> assets = new ArrayList<>();
        assets.add("MESD (Su)");
        assets.add("Outlook (Su)");

        List<String> roles = new ArrayList<>();
        roles.add("IT Specialist (R)");

        List<String> inputs = new ArrayList<>();
        inputs.add("Equipment:[in stock]");

        List<String> outputs = new ArrayList<>();
        outputs.add("Employee");

        Activity activity1 = createActivity("S1.2.1 New ticket for new hire", assets, roles, inputs, outputs,
                "", "The ticket contains all of the information that is needed to execute this sub-process. This includes user information, equipment (software, hardware and supplies) requirements, urgency/start dates, access rights and more. HR must be consulted if any of the required information is missing (see \"Provide user credentials\" to see how).");
        Activity activity2 = createActivity("S1.2.2 Analyze ticket", assets, roles, inputs, outputs,
                "", "A notification is received by email every time that a pre-boarding ticket is opened at https://helpdesk.quadient.group ");
        Activity activity3 = createActivity("S1.2.3 Equipment in stock?", assets, roles, inputs, outputs,
                "", "The ticket contains all of the information that is needed to execute this sub-process. This includes user information, equipment (software, hardware and supplies) requirements, urgency/start dates, access rights and more. HR must be consulted if any of the required information is missing (see \"Provide user credentials\" to see how).");
        Activity activity4 = createActivity("S1.2.4 Procure goods and services", null, null, null, null,
                null, null);
        Activity activity5 = createActivity("S1.2.5 Allocate equipment to user", assets, roles, inputs, outputs,
                "", "The ticket contains all of the information that is needed to execute this sub-process. This includes user information, equipment (software, hardware and supplies) requirements, urgency/start dates, access rights and more. HR must be consulted if any of the required information is missing (see \"Provide user credentials\" to see how).");
        Activity activity6 = createActivity("S1.2.6 Configure machine and user account", assets, roles, inputs, outputs,
                "", "");
        Activity activity7 = createActivity("S1.2.1 New ticket for new hire", assets, roles, inputs, outputs,
                "", "The ticket contains all of the information that is needed to execute this sub-process. This includes user information, equipment (software, hardware and supplies) requirements, urgency/start dates, access rights and more. HR must be consulted if any of the required information is missing (see \"Provide user credentials\" to see how).");

        process.getActivities().add(activity1);
        process.getActivities().add(activity2);
        process.getActivities().add(activity3);
        process.getActivities().add(activity4);
        process.getActivities().add(activity5);
        process.getActivities().add(activity6);
        process.getActivities().add(activity7);

    }

    private Activity createActivity(String name, List<String> assets, List<String> roles, List<String> inputs,
                                    List<String> outputs, String bulletSteps, String simpleSteps) {

        Activity activity = new Activity();
        activity.setName(name);
        activity.setAssets(assets);
        activity.setRoles(roles);
        activity.setInputs(inputs);
        activity.setOutputs(outputs);
        activity.setSimpleSteps(simpleSteps);
        activity.setBulletSteps(bulletSteps);

        return activity;
    }
}
