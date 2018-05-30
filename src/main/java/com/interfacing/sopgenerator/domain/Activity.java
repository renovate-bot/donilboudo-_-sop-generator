package com.interfacing.sopgenerator.domain;

import java.util.List;

public class Activity {
    private String name;
    private String simpleSteps;
    private String bulletSteps;
    private List<String> roles;
    private List<String> assets;
    private List<String> inputs;
    private List<String> outputs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleSteps() {
        return simpleSteps;
    }

    public void setSimpleSteps(String simpleSteps) {
        this.simpleSteps = simpleSteps;
    }

    public String getBulletSteps() {
        return bulletSteps;
    }

    public void setBulletSteps(String bulletSteps) {
        this.bulletSteps = bulletSteps;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getAssets() {
        return assets;
    }

    public void setAssets(List<String> assets) {
        this.assets = assets;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }
}
