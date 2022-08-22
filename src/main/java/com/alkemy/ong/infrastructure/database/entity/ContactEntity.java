package com.alkemy.ong.infrastructure.database.entity;

import java.time.LocalDate;
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
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "CONTACTS")
public class ContactEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CONTACT_ID")
  private Long contactId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "MESSAGE", nullable = false)
  private String message;

  @Column(name = "DELETED_AT")
  private LocalDate deletedAt;

}