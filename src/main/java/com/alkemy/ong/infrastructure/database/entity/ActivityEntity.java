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
@Table(name = "ACTIVITIES")
public class ActivityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ACTIVITY_ID")
  private Long activityId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "CONTENT", nullable = false)
  private String content;

  @Column(name = "IMAGE_URL", nullable = false)
  private String imageUrl;

  @CreationTimestamp
  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETED")
  private boolean softDeleted;

  public ActivityEntity(String name, String content, String imageUrl) {
    this.name = name;
    this.content = content;
    this.imageUrl = imageUrl;
  }
}
