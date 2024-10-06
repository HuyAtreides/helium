package app.helium.projectmanagement.test.integration;

import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.infra.messaging.producer.KafkaProjectMessageProducer;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class KafkaProjectProducerTest {

    @Autowired
    private KafkaProjectMessageProducer projectMessageProducer;

    @Test
    public void message_can_be_produced_to_kafka() {
        UUID id = UUID.randomUUID();
        Project project = Project.builder()
                .key("helium")
                .id(id)
                .defaultAssigneeId(UUID.randomUUID())
                .projectLeadId(UUID.randomUUID())
                .name("Helium")
                .description("A project planning software")
                .build();
        projectMessageProducer.sendMessage(id, project);
    }
}
