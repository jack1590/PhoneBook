package com.livevox.phonebook.repository.entity;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id",
    "version"})
@MappedSuperclass
public class BaseEntity {

  @Id
  private String id;

  @Version
  private Integer version;

  private Instant lastUpdatedOn;
  private String lastUpdatedBy;

  @PrePersist
  public void beforeCreate() {
    final UUID uuid = UUID.randomUUID();
    if (null == id) {
      id = uuid.toString();
    }
    version = 1;
    lastUpdatedOn = Instant.now();
    lastUpdatedBy = "Anonymous";
  }

  @PreUpdate
  public void beforeUpdate() {
    lastUpdatedOn = Instant.now();
    lastUpdatedBy = "Anonymous";
  }

}
