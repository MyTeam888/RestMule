package org.epsilonlabs.rescli.test.util;

import org.epsilonlabs.rescli.core.util.PropertiesUtil;
import java.util.Properties;

public class PrivateProperties {

	private static final String GITHUB_PRIVATE_PROPERTIES_FILE = "githubprivate.properties"; 
	
	public static String get(String property){
		Properties properties = PropertiesUtil.load(PrivateProperties.class, GITHUB_PRIVATE_PROPERTIES_FILE);
		return properties.getProperty(property);
	}

}