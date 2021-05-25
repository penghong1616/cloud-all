package com.ph.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/21/ 11:14
 */
@Entity
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  public Role(long id, String name) {
    this.id=id;
    this.name=name;
  }

  // setter getter

  @Override
  public String getAuthority() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}