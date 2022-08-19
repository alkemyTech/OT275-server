package com.alkemy.ong.infrastructure.database.entity;
import static javax.persistence.GenerationType.IDENTITY;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name= "organizations")
public class OrganitzationEntity {
 
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ORGANIZATION_ID")
  private Long organizationId;
  
  @Column (name = "NAME" , nullable = false)
  private String name;
  
  @Column (name = "IMAGE_URL" , nullable = false)
  private String imageUrl;
  
  @Column (name = "ADDRESS")
  private String address;
  
  @Column (name = "PHONE")
  private String phone;
  
  @Column (name = "EMAIL", nullable = false)
  private String email;
  
  @Column (name = "WELCOME_TEXT", nullable = false)
  private String welcomeText;
  
  @Column (name = "ABOUT_US", nullable = false)
  private String aboutUsText;
  
  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  @CreationTimestamp
  private Timestamp createTimestamp;
  
  @Column(name = "SOFT_DELETED", updatable = false)
  private boolean softDelete;
  
  
  
  
  

  
}
