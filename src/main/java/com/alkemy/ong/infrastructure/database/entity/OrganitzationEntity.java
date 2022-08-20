package com.alkemy.ong.infrastructure.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ORGANIZATIONS")
public class OrganitzationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORGANIZATION_ID")
  private Long organizationId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE_URL", nullable = false)
  private String imageUrl;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "WELCOME_TEXT", nullable = false)
  private String welcomeText;

  @Column(name = "ABOUT_US", nullable = false)
  private String aboutUsText;

  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  @CreationTimestamp
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETED", updatable = false)
  private boolean softDeleted;



}
