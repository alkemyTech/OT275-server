package com.alkemy.ong.infrastructure.database.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Time;
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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TESTIMONIALS")
public class TestimonialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TESTIMONIAL_ID")
  private Long testimonialId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE_URL")
  private String imageUrl;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  @CreationTimestamp
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETED")
  private boolean softDeleted;


}
