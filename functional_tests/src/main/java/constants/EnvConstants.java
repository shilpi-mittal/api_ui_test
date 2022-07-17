package constants;

import utils.TestEnvironmentUtils;

// contains constants
public class EnvConstants {
    public static final String PROTOCOL = TestEnvironmentUtils.getProperty("PROTOCOL");
    public static final String HOSTNAME = TestEnvironmentUtils.getProperty("HOSTNAME");
}
