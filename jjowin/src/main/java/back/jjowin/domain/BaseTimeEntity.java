package back.jjowin.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdDate;

    @LastModifiedDate
   @ColumnDefault("CURRENT_TIMESTAMP()")

    private LocalDateTime lastModifiedDate;
}
