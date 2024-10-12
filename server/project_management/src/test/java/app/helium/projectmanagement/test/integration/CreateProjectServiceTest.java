package app.helium.projectmanagement.test.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import app.helium.projectmanagement.core.application.command.CreateProjectCommand;
import app.helium.projectmanagement.core.application.service.ProjectService;
import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.project.ProjectId;
import app.helium.projectmanagement.project.ProjectMessage;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;

@IntegrationTest
public class CreateProjectServiceTest {

    @Autowired
    private ProjectService projectService;
    @SpyBean
    private KafkaTemplate<ProjectId, ProjectMessage> kafkaTemplate;

    @Test
    void create_project_with_valid_data_should_success_and_publish_message() {
        Project project = projectService.createProject(
                CreateProjectCommand.builder()
                        .defaultAssigneeId(UUID.randomUUID())
                        .projectLeadId(UUID.randomUUID())
                        .iconURL("https://media.helium.com/project-icon-url")
                        .key("project")
                        .name("Project Name")
                        .description("New Project")
                        .build()
        );

        assertNotNull(project);
        verify(kafkaTemplate, times(1))
                .send(eq("app.helium.project"), any(ProjectId.class), any(ProjectMessage.class));
    }
}
