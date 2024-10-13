package app.helium.projectplanning.test.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import app.helium.projectmanagement.project.Data;
import app.helium.projectmanagement.project.Metadata;
import app.helium.projectmanagement.project.ProjectId;
import app.helium.projectmanagement.project.ProjectMessage;
import app.helium.projectplanning.infra.repository.ProjectRepository;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@IntegrationTest
public class ConsumeProjectMessageIntegrationTest {

    @Autowired
    private KafkaTemplate<ProjectId, ProjectMessage> kafkaTemplate;

    @Autowired
    private ProjectRepository projectRepository;

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @Test
    void project_created_message_should_be_consumed_and_new_project_should_saved_to_database()
            throws InterruptedException {
        UUID id = UUID.fromString("0379d155-3e67-4679-94b1-e031361f1e42");
        UUID messageId = UUID.fromString("e7daa58a-cf9c-49b0-b26b-d7773e7b895d");
        ProjectId messageKey = ProjectId.newBuilder().setId(id.toString()).build();
        Instant now = Instant.now();
        String projectKey = "LUMON";
        UUID eventId = UUID.randomUUID();

        Metadata messageMetadata = Metadata.newBuilder()
                .setEvent("project_created")
                .setTime(now.toString())
                .setSource("testing")
                .setSubject("testing")
                .setProducer("producer_for_testing")
                .setId(eventId.toString())
                .build();

        Data messageData = Data.newBuilder()
                .setId(messageId.toString())
                .setKey(projectKey)
                .setProjectLeadId(null)
                .setDefaultAssigneeId(null)
                .build();

        ProjectMessage message = ProjectMessage.newBuilder()
                .setData(messageData)
                .setMetadata(messageMetadata)
                .build();

        kafkaTemplate.send(topic, messageKey, message);

        Thread.sleep(2000);

        assertTrue(projectRepository.existsById(messageId));
    }
}
