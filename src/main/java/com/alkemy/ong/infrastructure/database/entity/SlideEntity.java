package com.alkemy.ong.infrastructure.database.entity;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SLIDES")
public class SlideEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SLIDE_ID")
  private Long slideId;

  @Column(name = "IMAGE_URL", nullable = false)
  private String imageUrl;

  @Column(name = "TEXT")
  private String text;

  @Column(name = "[ORDER]", nullable = false)
  private Integer order;
}
