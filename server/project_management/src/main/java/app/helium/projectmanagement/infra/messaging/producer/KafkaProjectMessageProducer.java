package app.helium.projectmanagement.infra.messaging.producer;

import app.helium.projectmanagement.core.application.service.MessageProducer;
import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.infra.messaging.constant.Event;
import app.helium.projectmanagement.infra.messaging.mapper.KafkaProjectMessageMapper;
import app.helium.projectmanagement.project.Data;
import app.helium.projectmanagement.project.Metadata;
import app.helium.projectmanagement.project.ProjectId;
import app.helium.projectmanagement.project.ProjectMessage;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.mapstruct.factory.Mappers;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProjectMessageProducer implements MessageProducer<UUID, Project> {
    private final KafkaTemplate<ProjectId, ProjectMessage> kafkaTemplate;
    private final KafkaProjectMessageMapper mapper = Mappers.getMapper(KafkaProjectMessageMapper.class);

    @Override
    public void sendMessage(UUID projectId,  Project project) {
        Instant now = Instant.now();
        String producerID = (String) kafkaTemplate
                .getProducerFactory()
                .getConfigurationProperties()
                .get(ProducerConfig.CLIENT_ID_CONFIG);
        UUID eventId = UUID.randomUUID();

        Metadata messageMetadata = Metadata.newBuilder()
                .setEvent(Event.PROJECT_CREATED)
                .setTime(now.toString())
                .setSource(Event.PROJECT_SOURCE)
                .setSubject(project.getId().toString())
                .setProducer(producerID)
                .setId(eventId.toString())
                .build();

        ProjectId messageKey = ProjectId.newBuilder()
                .setId(project.getId().toString())
                .build();

        Data messageData = mapper.mapToProjectMessageData(project);
        ProjectMessage projectMessage = ProjectMessage.newBuilder()
                .setData(messageData)
                .setMetadata(messageMetadata)
                .build();

        kafkaTemplate.send(Event.PROJECT_TOPIC, messageKey, projectMessage);
    }
}
