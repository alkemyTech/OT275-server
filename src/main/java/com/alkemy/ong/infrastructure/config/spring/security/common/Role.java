package com.alkemy.ong.infrastructure.config.spring.security.common;

public enum Role {

  ADMIN, USER;

  private static final String ROLE_PREFIX = "ROLE_";

  public String getFullRoleName() {
    return ROLE_PREFIX + this.name();
  }

}
