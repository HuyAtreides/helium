package app.helium.projectmanagement.infra.messaging.mapper;

import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.project.Data;
import org.mapstruct.Mapper;

@Mapper
public interface KafkaProjectMessageMapper {
    Data mapToProjectMessageData(Project project);
}
