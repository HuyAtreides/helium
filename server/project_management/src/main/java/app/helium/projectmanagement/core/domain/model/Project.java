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
import org.hibernate.validator.constraints.URL;

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
    @URL(message = "URL is malformed", protocol = "https", host = "media.helium.com")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Getter(AccessLevel.PUBLIC)
    private String iconURL;

    @Column(name = "name")
    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Getter(AccessLevel.PUBLIC)
    private String name;

    @Column(name = "description")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Getter(AccessLevel.PUBLIC)
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
    @Getter(AccessLevel.PUBLIC)
    private Instant createdAt;

    @Column(name = "last_updated_at")
    @JdbcTypeCode(SqlTypes.TIMESTAMP_UTC)
    @NotNull
    @Getter(AccessLevel.PUBLIC)
    private Instant lastUpdatedAt;

    @Column(name = "last_updated_by_id")
    @JdbcTypeCode(SqlTypes.UUID)
    @NotNull
    @Getter(AccessLevel.PUBLIC)
    private UUID lastUpdatedById;
}
