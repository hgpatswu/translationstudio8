/**
 * ProjectConfigManager.java
 *
 * Version information :
 *
 * Date:Mar 26, 2012
 *
 * Copyright notice :
 * 本文件及其附带的相关文件包含机密信息，仅限瀚特盛科技有限公司指定的，与本项目有关的内部人员和客户联络人员查阅和使用。 
 * 如果您不是本保密声明中指定的收件者，请立即销毁本文件，禁止对本文件或根据本文件中的内容采取任何其他行动， 
 * 包括但不限于：泄露本文件中的信息、以任何方式制作本文件全部或部分内容之副本、将本文件或其相关副本提供给任何其他人。
 */
package net.heartsome.cat.ts.core.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.heartsome.cat.ts.core.resource.Messages;

import org.eclipse.core.resources.IProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jason
 * @version
 * @since JDK1.6
 */
public final class ProjectConfigerFactory {
	private static Logger logger = LoggerFactory.getLogger(ProjectConfigerFactory.class);

	public static Map<String, ProjectConfiger> confgerMap = new HashMap<String, ProjectConfiger>();

	public static ProjectConfiger getProjectConfiger(IProject project) {
		ProjectConfiger configer = confgerMap.get(project.getName());
		if (configer == null) {
			String projectFile = project.getLocation() + System.getProperty("file.separator") + ".config";
			try {
				configer = new ProjectConfiger(projectFile);
				confgerMap.put(project.getName(), configer);
			} catch (IOException e) {
				logger.error(Messages.getString("file.ProjectConfigerFactory.logger1") + projectFile, e);
				return null;
			}
		}
		return configer;
	}
}
