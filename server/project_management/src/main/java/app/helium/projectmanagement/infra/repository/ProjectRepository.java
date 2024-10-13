package app.helium.projectmanagement.infra.repository;

import app.helium.projectmanagement.core.domain.model.Project;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, UUID> {
}
