package com.alkemy.ong.infrastructure.database.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "ROLES")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ROLE_ID")
  private Long roleId;

  @Column(name = "NAME", nullable = false, length = 5)
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  @CreationTimestamp
  private Timestamp createTimestamp;

}
