/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gradle.plugins.defaults.internal;

import com.liferay.gradle.plugins.cache.CacheExtension;
import com.liferay.gradle.plugins.cache.CachePlugin;
import com.liferay.gradle.plugins.cache.WriteDigestTask;
import com.liferay.gradle.plugins.cache.task.TaskCache;
import com.liferay.gradle.plugins.cache.task.TaskCacheApplicator;
import com.liferay.gradle.plugins.change.log.builder.BuildChangeLogTask;
import com.liferay.gradle.plugins.change.log.builder.ChangeLogBuilderPlugin;
import com.liferay.gradle.plugins.defaults.LiferayOSGiDefaultsPlugin;
import com.liferay.gradle.plugins.defaults.LiferayThemeDefaultsPlugin;
import com.liferay.gradle.plugins.defaults.internal.util.FileUtil;
import com.liferay.gradle.plugins.defaults.internal.util.GitUtil;
import com.liferay.gradle.plugins.defaults.internal.util.GradleUtil;
import com.liferay.gradle.plugins.defaults.tasks.PrintArtifactPublishCommandsTask;
import com.liferay.gradle.plugins.defaults.tasks.ReplaceRegexTask;
import com.liferay.gradle.plugins.defaults.tasks.WritePropertiesTask;
import com.liferay.gradle.util.Validator;

import groovy.lang.Closure;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Method;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.UncheckedIOException;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.artifacts.PublishArtifact;
import org.gradle.api.artifacts.PublishArtifactSet;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.maven.MavenDeployer;
import org.gradle.api.file.CopySpec;
import org.gradle.api.file.FileCollection;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.JavaBasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.MavenPlugin;
import org.gradle.api.plugins.MavenRepositoryHandlerConvention;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.Upload;
import org.gradle.util.GUtil;

/**
 * @author Andrea Di Giorgi
 */
public class LiferayRelengPlugin implements Plugin<Project> {

	public static final Plugin<Project> INSTANCE = new LiferayRelengPlugin();

	public static final String PRINT_ARTIFACT_PUBLISH_COMMANDS =
		"printArtifactPublishCommands";

	public static final String PRINT_STALE_ARTIFACT_TASK_NAME =
		"printStaleArtifact";

	public static final String RECORD_ARTIFACT_TASK_NAME = "recordArtifact";

	public static final String UPDATE_VERSION_TASK_NAME = "updateVersion";

	public static File getRelengDir(Project project) {
		File rootDir = GradleUtil.getRootDir(project, ".releng");

		if (rootDir == null) {
			return null;
		}

		File relengDir = new File(rootDir, ".releng");

		return new File(
			relengDir, FileUtil.relativize(project.getProjectDir(), rootDir));
	}

	@Override
	public void apply(final Project project) {
		File relengDir = getRelengDir(project);

		if (relengDir == null) {
			return;
		}

		GradleUtil.applyPlugin(project, ChangeLogBuilderPlugin.class);
		GradleUtil.applyPlugin(project, MavenPlugin.class);

		final BuildChangeLogTask buildChangeLogTask =
			(BuildChangeLogTask)GradleUtil.getTask(
				project, ChangeLogBuilderPlugin.BUILD_CHANGE_LOG_TASK_NAME);

		final WritePropertiesTask recordArtifactTask = _addTaskRecordArtifact(
			project, relengDir);

		_addTaskPrintArtifactPublishCommands(project, recordArtifactTask);
		_addTaskPrintStaleArtifact(project, recordArtifactTask);

		_configureTaskBuildChangeLog(buildChangeLogTask, relengDir);
		_configureTaskUploadArchives(project, recordArtifactTask);

		GradleUtil.withPlugin(
			project, JavaPlugin.class,
			new Action<JavaPlugin>() {

				@Override
				public void execute(JavaPlugin javaPlugin) {
					_configureTaskProcessResources(project, buildChangeLogTask);
				}

			});
	}

	private LiferayRelengPlugin() {
	}

	private PrintArtifactPublishCommandsTask
		_addTaskPrintArtifactPublishCommands(
			Project project, final WritePropertiesTask recordArtifactTask) {

		final PrintArtifactPublishCommandsTask
			printArtifactPublishCommandsTask = GradleUtil.addTask(
				project, PRINT_ARTIFACT_PUBLISH_COMMANDS,
				PrintArtifactPublishCommandsTask.class);

		printArtifactPublishCommandsTask.setArtifactPropertiesFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return recordArtifactTask.getOutputFile();
				}

			});

		printArtifactPublishCommandsTask.setDescription(
			"Prints the artifact publish commands if this project has been " +
				"changed since the last publish.");

		_configureTaskEnabledIfStale(
			printArtifactPublishCommandsTask, recordArtifactTask);

		String projectPath = project.getPath();

		if (projectPath.startsWith(":apps:") ||
			projectPath.startsWith(":private:apps:")) {

			_configureTaskEnabledIfLeaf(printArtifactPublishCommandsTask);
			_configureTaskEnabledIfDependenciesArePublished(
				printArtifactPublishCommandsTask);
		}

		GradleUtil.withPlugin(
			project, LiferayOSGiDefaultsPlugin.class,
			new Action<LiferayOSGiDefaultsPlugin>() {

				@Override
				public void execute(
					LiferayOSGiDefaultsPlugin liferayOSGiDefaultsPlugin) {

					_configureTaskPrintArtifactPublishCommandsForOSGi(
						printArtifactPublishCommandsTask);
				}

			});

		project.afterEvaluate(
			new Action<Project>() {

				@Override
				public void execute(Project project) {
					TaskContainer taskContainer = project.getTasks();

					Task task = taskContainer.findByName(
						UPDATE_VERSION_TASK_NAME);

					if (task instanceof ReplaceRegexTask) {
						ReplaceRegexTask replaceRegexTask =
							(ReplaceRegexTask)task;

						Map<String, FileCollection> matches =
							replaceRegexTask.getMatches();

						printArtifactPublishCommandsTask.prepNextFiles(
							matches.values());
					}

					if (GradleUtil.hasPlugin(project, CachePlugin.class)) {
						CacheExtension cacheExtension = GradleUtil.getExtension(
							project, CacheExtension.class);

						for (TaskCache taskCache : cacheExtension.getTasks()) {
							printArtifactPublishCommandsTask.prepNextFiles(
								new File(
									taskCache.getCacheDir(),
									TaskCacheApplicator.DIGEST_FILE_NAME));
						}
					}

					if (GradleUtil.hasPlugin(
							project, LiferayThemeDefaultsPlugin.class)) {

						WriteDigestTask writeDigestTask =
							(WriteDigestTask)GradleUtil.getTask(
								project,
								LiferayThemeDefaultsPlugin.
									WRITE_PARENT_THEMES_DIGEST_TASK_NAME);

						printArtifactPublishCommandsTask.prepNextCommitFile(
							"digest", writeDigestTask.getDigestFile());
					}
				}

			});

		return printArtifactPublishCommandsTask;
	}

	private Task _addTaskPrintStaleArtifact(
		Project project, final WritePropertiesTask recordArtifactTask) {

		final Task task = project.task(PRINT_STALE_ARTIFACT_TASK_NAME);

		task.doLast(
			new Action<Task>() {

				@Override
				public void execute(Task task) {
					Project project = task.getProject();

					File projectDir = project.getProjectDir();

					System.out.println(projectDir.getAbsolutePath());
				}

			});

		task.setDescription(
			"Prints the project directory if this project has been changed " +
				"since the last publish.");
		task.setGroup(JavaBasePlugin.VERIFICATION_GROUP);

		_configureTaskEnabledIfStale(task, recordArtifactTask);

		GradleUtil.withPlugin(
			project, LiferayOSGiDefaultsPlugin.class,
			new Action<LiferayOSGiDefaultsPlugin>() {

				@Override
				public void execute(
					LiferayOSGiDefaultsPlugin liferayOSGiDefaultsPlugin) {

					_configureTaskPrintStaleArtifactForOSGi(task);
				}

			});

		return task;
	}

	private WritePropertiesTask _addTaskRecordArtifact(
		Project project, File destinationDir) {

		final WritePropertiesTask writePropertiesTask = GradleUtil.addTask(
			project, RECORD_ARTIFACT_TASK_NAME, WritePropertiesTask.class);

		writePropertiesTask.property(
			"artifact.git.id",
			new Callable<String>() {

				@Override
				public String call() throws Exception {
					return GitUtil.getGitResult(
						writePropertiesTask.getProject(), "rev-parse", "HEAD");
				}

			});

		writePropertiesTask.setDescription(
			"Records the commit ID and the artifact URLs.");
		writePropertiesTask.setOutputFile(
			new File(destinationDir, "artifact.properties"));

		Configuration configuration = GradleUtil.getConfiguration(
			project, Dependency.ARCHIVES_CONFIGURATION);

		PublishArtifactSet publishArtifactSet = configuration.getArtifacts();

		Action<PublishArtifact> action = new Action<PublishArtifact>() {

			@Override
			public void execute(final PublishArtifact publishArtifact) {
				writePropertiesTask.property(
					new Callable<String>() {

						@Override
						public String call() throws Exception {
							String key = publishArtifact.getClassifier();

							if (Validator.isNull(key)) {
								key = "artifact.url";
							}
							else {
								key = "artifact." + key + ".url";
							}

							return key;
						}

					},
					new Callable<String>() {

						@Override
						public String call() throws Exception {
							return _getArtifactRemoteURL(
								writePropertiesTask.getProject(),
								publishArtifact, false);
						}

					});
			}

		};

		publishArtifactSet.all(action);

		return writePropertiesTask;
	}

	private void _configureTaskBuildChangeLog(
		BuildChangeLogTask buildChangeLogTask, File destinationDir) {

		buildChangeLogTask.setChangeLogFile(
			new File(destinationDir, "liferay-releng.changelog"));
	}

	private void _configureTaskEnabledIfDependenciesArePublished(Task task) {
		task.onlyIf(
			new Spec<Task>() {

				@Override
				public boolean isSatisfiedBy(Task task) {
					try {
						Project project = task.getProject();

						if (FileUtil.contains(
								project.getBuildFile(),
								"version: \"default\"")) {

							return false;
						}

						return true;
					}
					catch (IOException ioe) {
						throw new UncheckedIOException(ioe);
					}
				}

			});
	}

	private void _configureTaskEnabledIfLeaf(Task task) {
		task.onlyIf(
			new Spec<Task>() {

				@Override
				public boolean isSatisfiedBy(Task task) {
					Project project = task.getProject();

					for (Configuration configuration :
							project.getConfigurations()) {

						if (_hasProjectDependencies(configuration)) {
							return false;
						}
					}

					return true;
				}

				private boolean _hasProjectDependencies(
					Configuration configuration) {

					for (Dependency dependency :
							configuration.getDependencies()) {

						if (dependency instanceof ProjectDependency) {
							return true;
						}
					}

					return false;
				}

			});
	}

	private void _configureTaskEnabledIfRelease(Task task) {
		task.onlyIf(
			new Spec<Task>() {

				@Override
				public boolean isSatisfiedBy(Task task) {
					Project project = task.getProject();

					if (GradleUtil.hasStartParameterTask(
							project, task.getName()) ||
						!GradleUtil.isSnapshot(project)) {

						return true;
					}

					return false;
				}

			});
	}

	private void _configureTaskEnabledIfStale(
		Task task, final WritePropertiesTask recordArtifactTask) {

		String force = GradleUtil.getTaskPrefixedProperty(task, "force");

		if (Boolean.parseBoolean(force)) {
			return;
		}

		task.onlyIf(
			new Spec<Task>() {

				@Override
				public boolean isSatisfiedBy(Task task) {
					if (FileUtil.exists(
							task.getProject(), ".lfrbuild-releng-ignore")) {

						return false;
					}

					return true;
				}

			});

		task.onlyIf(
			new Spec<Task>() {

				@Override
				public boolean isSatisfiedBy(Task task) {
					Properties artifactProperties;

					File artifactPropertiesFile =
						recordArtifactTask.getOutputFile();

					if (artifactPropertiesFile.exists()) {
						artifactProperties = GUtil.loadProperties(
							artifactPropertiesFile);
					}
					else {
						artifactProperties = new Properties();
					}

					return _isStale(
						recordArtifactTask.getProject(), artifactProperties);
				}

			});
	}

	private void _configureTaskPrintArtifactPublishCommandsForOSGi(
		PrintArtifactPublishCommandsTask printArtifactPublishCommandsTask) {

		Project project = printArtifactPublishCommandsTask.getProject();

		if (GradleUtil.isTestProject(project)) {
			printArtifactPublishCommandsTask.setEnabled(false);
		}

		printArtifactPublishCommandsTask.setFirstPublishExcludedTaskName(
			LiferayOSGiDefaultsPlugin.UPDATE_FILE_VERSIONS_TASK_NAME);
	}

	private void _configureTaskPrintStaleArtifactForOSGi(Task task) {
		if (GradleUtil.isTestProject(task.getProject())) {
			task.setEnabled(false);
		}
	}

	private void _configureTaskProcessResources(
		Project project, final BuildChangeLogTask buildChangeLogTask) {

		Copy copy = (Copy)GradleUtil.getTask(
			project, JavaPlugin.PROCESS_RESOURCES_TASK_NAME);

		copy.from(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return buildChangeLogTask.getChangeLogFile();
				}

			},
			new Closure<Void>(project) {

				@SuppressWarnings("unused")
				public void doCall(CopySpec copySpec) {
					copySpec.into("META-INF");
				}

			});
	}

	private void _configureTaskUploadArchives(
		Project project, Task recordArtifactTask) {

		Task uploadArchivesTask = GradleUtil.getTask(
			project, BasePlugin.UPLOAD_ARCHIVES_TASK_NAME);

		uploadArchivesTask.dependsOn(recordArtifactTask);

		_configureTaskEnabledIfRelease(recordArtifactTask);
	}

	private StringBuilder _getArtifactRemoteBaseURL(
			Project project, boolean cdn)
		throws Exception {

		Upload upload = (Upload)GradleUtil.getTask(
			project, BasePlugin.UPLOAD_ARCHIVES_TASK_NAME);

		RepositoryHandler repositoryHandler = upload.getRepositories();

		MavenDeployer mavenDeployer = (MavenDeployer)repositoryHandler.getAt(
			MavenRepositoryHandlerConvention.DEFAULT_MAVEN_DEPLOYER_NAME);

		Object repository = mavenDeployer.getRepository();

		// org.apache.maven.artifact.ant.RemoteRepository is not in the
		// classpath

		Class<?> repositoryClass = repository.getClass();

		Method getUrlMethod = repositoryClass.getMethod("getUrl");

		String url = (String)getUrlMethod.invoke(repository);

		if (cdn) {
			url = url.replace("http://", "http://cdn.");
			url = url.replace("https://", "https://cdn.");
		}

		StringBuilder sb = new StringBuilder(url);

		if (sb.charAt(sb.length() - 1) != '/') {
			sb.append('/');
		}

		String group = String.valueOf(project.getGroup());

		sb.append(group.replace('.', '/'));

		sb.append('/');

		return sb;
	}

	private String _getArtifactRemoteURL(
			Project project, PublishArtifact publishArtifact, boolean cdn)
		throws Exception {

		StringBuilder sb = _getArtifactRemoteBaseURL(project, cdn);

		String name = GradleUtil.getArchivesBaseName(project);

		sb.append(name);

		sb.append('/');
		sb.append(project.getVersion());
		sb.append('/');
		sb.append(name);
		sb.append('-');
		sb.append(project.getVersion());

		String classifier = publishArtifact.getClassifier();

		if (Validator.isNotNull(classifier)) {
			sb.append('-');
			sb.append(classifier);
		}

		sb.append('.');
		sb.append(publishArtifact.getExtension());

		return sb.toString();
	}

	private boolean _isStale(
		final Project project, Properties artifactProperties) {

		Logger logger = project.getLogger();

		final String artifactGitId = artifactProperties.getProperty(
			"artifact.git.id");

		if (Validator.isNull(artifactGitId)) {
			if (logger.isInfoEnabled()) {
				logger.info("{} has never been published", project);
			}

			return true;
		}

		String result = GitUtil.getGitResult(
			project, "log", "--format=%s", artifactGitId + "..HEAD", ".");

		String[] lines = result.split("\\r?\\n");

		for (String line : lines) {
			if (logger.isInfoEnabled()) {
				logger.info(line);
			}

			if (Validator.isNull(line)) {
				continue;
			}

			if (!line.contains(
					PrintArtifactPublishCommandsTask.IGNORED_MESSAGE_PATTERN)) {

				return true;
			}
		}

		if (GradleUtil.hasPlugin(project, LiferayThemeDefaultsPlugin.class)) {
			WriteDigestTask writeDigestTask =
				(WriteDigestTask)GradleUtil.getTask(
					project,
					LiferayThemeDefaultsPlugin.
						WRITE_PARENT_THEMES_DIGEST_TASK_NAME);

			String digest = writeDigestTask.getDigest();
			String oldDigest = writeDigestTask.getOldDigest();

			if (logger.isInfoEnabled()) {
				logger.info(
					"Digest for {} is {}, old digest is {}", writeDigestTask,
					digest, oldDigest);
			}

			if (!Objects.equals(digest, oldDigest)) {
				return true;
			}
		}

		return false;
	}

}