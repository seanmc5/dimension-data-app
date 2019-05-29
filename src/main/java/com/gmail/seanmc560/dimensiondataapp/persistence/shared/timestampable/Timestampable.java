package com.gmail.seanmc560.dimensiondataapp.persistence.shared.timestampable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Getter
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestampable {

  @LastModifiedDate
  @Column(name = "LAST_UPDATED_DATE", nullable = false)
  protected ZonedDateTime lastUpdatedDate;

  @CreatedDate
  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  protected ZonedDateTime createdDate;
}
