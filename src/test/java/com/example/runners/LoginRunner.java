package com.example.runners;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/login")
@ConfigurationParameter(key = "cucumber.glue", value = "com.example.stepdefinitions")
@ConfigurationParameter(key = "cucumber.plugin", value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
public class LoginRunner {
}
