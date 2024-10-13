package app.helium.projectplanning.infra.messaging.consumer;

import app.helium.projectmanagement.project.ProjectId;
import app.helium.projectmanagement.project.ProjectMessage;
import app.helium.projectplanning.core.application.service.ProjectPlanningService;
import app.helium.projectplanning.core.domain.model.Project;
import app.helium.projectplanning.infra.messaging.mapper.ProjectMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProjectMessageConsumer {
    private final ProjectPlanningService projectPlanningService;
    private final ProjectMessageMapper mapper = Mappers.getMapper(ProjectMessageMapper.class);

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            concurrency = "${spring.kafka.consumer.concurrency}",
            topics = "${spring.kafka.consumer.topic}"
    )
    void consume(ConsumerRecord<ProjectId, ProjectMessage> record) {
        log.info("message=receive project message: {}", record.value().toString());
        Project project = mapper.mapToProject(record.value().getData());
        projectPlanningService.addNewProject(project);
        /*acknowledgment.acknowledge();*/
    }
}
