package cn.thinkjoy.sample;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootStartup {

	private static final Logger logger = LoggerFactory.getLogger(BootStartup.class);

	private static final String SPRING_CONFIG_KEY = "spring.config";

	private static final String SPRING_CONFIG_PATH = "classpath*:META-INF/spring/*.xml";

	private static volatile boolean running = false;

	private ConfigurableApplicationContext context;

	public BootStartup() {

	}

	private void run(String[] args) {
		String configPath = System.getProperty(SPRING_CONFIG_KEY);
		if (StringUtils.isBlank(configPath)) {
			configPath = SPRING_CONFIG_PATH;
		}

		context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
		context.registerShutdownHook();
		context.start();
	}

	public static void main(String[] args) {
		logger.info("Starting BootStartup");

		synchronized (BootStartup.class) {
			if (!running) {
				running = true;
				new BootStartup().run(args);
			}
		}
	}

}

