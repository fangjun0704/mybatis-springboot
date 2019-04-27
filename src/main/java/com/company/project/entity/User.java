package com.company.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  /**
   * 数据库自增主键
   */
  private Integer uId;
  private String name;
  private Integer age;
  private String testField;
}
