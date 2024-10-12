package app.helium.projectmanagement.test.integration;

import app.helium.projectmanagement.ProjectManagementApplication;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {ApplicationTestConfig.class, ProjectManagementApplication.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public @interface IntegrationTest {

}
