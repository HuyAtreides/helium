
package app.helium.projectmanagement.test.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.test.shared.validator.DomainEntityValidator;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CreateProjectTest {

    @Test
    void missing_required_fields_project_should_fail_validator() {
        var exception = assertThrows(ConstraintViolationException.class, () -> {
            DomainEntityValidator.validate(Project.builder()
                    .iconURL("https://media.helium.com/project-icon-url")
                    .build()
            );
        });

        assertEquals(6, exception.getConstraintViolations().size());
    }

    @Test
    void project_with_invalid_icon_url_should_fail_validator() {
        assertThrows(ConstraintViolationException.class, () -> {
            DomainEntityValidator.validate(
                    Project.builder()
                            .id(UUID.randomUUID())
                            .iconURL("https://mediahelium.com/project-icon-url")
                            .key("project-1")
                            .name("New Project")
                            .createdAt(Instant.now())
                            .lastUpdatedAt(Instant.now())
                            .lastUpdatedById(UUID.randomUUID())
                            .projectLeadId(UUID.randomUUID())
                            .build()
            );
        });
    }

    @Test
    void create_project_with_valid_data_should_success() {
        assertDoesNotThrow(() -> {
            DomainEntityValidator.validate(
                    Project.builder()
                            .id(UUID.randomUUID())
                            .iconURL("https://media.helium.com/project-icon-url")
                            .key("project-1")
                            .name("New Project")
                            .createdAt(Instant.now())
                            .lastUpdatedAt(Instant.now())
                            .lastUpdatedById(UUID.randomUUID())
                            .projectLeadId(UUID.randomUUID())
                            .build()
            );
        });
    }
}
