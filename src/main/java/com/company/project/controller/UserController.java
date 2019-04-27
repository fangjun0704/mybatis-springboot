package com.company.project.controller;

import com.company.project.entity.User;
import com.company.project.annotation.mapper.UserMapper;
import com.company.project.xml.mapper.UserMapperXML;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

  @Resource
  private UserMapper userMapper;

  @Resource
  private UserMapperXML userMapperXML;

  /**
   * 增加一个用户
   */
  @RequestMapping(value = "/insert")
  public User insertUser(User user) {
    /**
     * 这里有一个问题，就是插入成功后，不会返回主键u_id
     * 需要使用 @Options注解
     */
//    userMapper.insertUser(user);
    userMapperXML.insertUser(user);
    return user;
  }

  /**
   * 删除一个用户
   */
  @RequestMapping(value = "/delete/{pathId}")
  public String deleteUser(@PathVariable("pathId") Integer uId) {
//    int i = userMapper.deleteUserById(uId);
    int i = userMapperXML.deleteUserById(uId);
    if (i != 0) {
      return "删除成功";
    } else {
      return "删除失败";
    }
  }

  /**
   * 更新一个用户
   */
  @RequestMapping(value = "/update")
  public User updatetUser(User user) {
//    userMapper.updateUserById(user);
    userMapperXML.updateUserById(user);
    return user;
  }

  /**
   * 查询一个用户
   */
  @GetMapping(value = "/get/{pathId}")
  public User getUser(@PathVariable("pathId") Integer uId) {
//    return userMapper.getUserById(uId);
    return userMapperXML.getUserById(uId);
  }

}
