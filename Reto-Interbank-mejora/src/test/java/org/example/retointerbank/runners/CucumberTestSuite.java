package org.example.retointerbank.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME; // agregado para filtrar por tags

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/compra_chaquetas.feature")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@solo")
public class CucumberTestSuite {
}