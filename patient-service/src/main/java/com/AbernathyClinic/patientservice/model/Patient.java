package com.AbernathyClinic.patientservice.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

  @Id
  @GeneratedValue
  private int id;

  @Column
  private String family;
  @Column
  private String given;
  @Column
  private Date birthdate;
  @Column
  private String sex;
  @Column
  private String address;
  @Column
  private String phone;
}
