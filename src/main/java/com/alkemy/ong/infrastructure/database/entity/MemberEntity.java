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
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "MEMBERS")
public class MemberEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long memberId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "FACEBOOK_URL")
  private String facebookUrl;

  @Column(name = "INSTAGRAM_URL")
  private String instagramUrl;

  @Column(name = "LINKED_IN_URL")
  private String linkedInUrl;

  @Column(name = "IMAGE_URL", nullable = false)
  private String imageUrl;

  @Column(name = "DESCRIPTION")
  private String description;

  @CreationTimestamp
  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETE")
  private boolean softDeleted;

}