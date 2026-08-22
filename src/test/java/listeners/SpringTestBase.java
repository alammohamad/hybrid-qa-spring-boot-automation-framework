package listeners;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = config.SpringTestConfig.class)
public class SpringTestBase extends AbstractTestNGSpringContextTests {
}
