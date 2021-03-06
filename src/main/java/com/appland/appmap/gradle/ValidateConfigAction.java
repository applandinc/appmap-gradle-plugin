package com.appland.appmap.gradle;

import java.io.File;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Task;
import org.gradle.api.provider.Provider;

/**
 * Action to validates the configuration for appmap exists and is readable.
 */
public class ValidateConfigAction implements Action<Task> {

  private final Provider<File> configFile;

  public ValidateConfigAction(Provider<File> configFile) {
    this.configFile = configFile;
  }

  @Override
  public void execute(Task task) {
    if (!isConfigFileValid()) {
      throw new GradleException(
          "Config file " + configFile.get().getPath() + " not found or not readable."
      );
    }
  }

  protected boolean isConfigFileValid() {
    return AppMapPluginExtension.isConfigFileValid(configFile.get());
  }
}
