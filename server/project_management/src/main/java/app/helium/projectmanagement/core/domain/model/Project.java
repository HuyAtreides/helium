package app.helium.projectmanagement.core.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Table(name = "project", schema = "project_management")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Project {

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @EqualsAndHashCode.Include
    @NotNull
    @Getter(AccessLevel.PUBLIC)
    private UUID id;

    @Column(name = "icon_url")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String iconURL;

    @Column(name = "name")
    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Column(name = "description")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String description;

    @Column(name = "project_lead_id")
    @JdbcTypeCode(SqlTypes.UUID)
    @Getter(AccessLevel.PUBLIC)
    private UUID projectLeadId;

    @Column(name = "key", nullable = false, length = 7)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotNull
    @Getter(AccessLevel.PUBLIC)
    private String key;

    @Column(name = "default_assignee_id")
    @JdbcTypeCode(SqlTypes.UUID)
    @Getter(AccessLevel.PUBLIC)
    private UUID defaultAssigneeId;

    @Column(name = "created_at")
    @JdbcTypeCode(SqlTypes.TIMESTAMP_UTC)
    @NotNull
    private Instant createdAt;

    @Column(name = "last_updated_at")
    @JdbcTypeCode(SqlTypes.TIMESTAMP_UTC)
    @NotNull
    private Instant lastUpdatedAt;

    @Column(name = "last_updated_by_id")
    @JdbcTypeCode(SqlTypes.UUID)
    @NotNull
    private UUID lastUpdatedById;
}
