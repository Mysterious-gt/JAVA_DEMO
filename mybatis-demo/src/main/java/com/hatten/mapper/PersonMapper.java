package com.hatten.mapper;

import com.hatten.pojo.Person;

import java.util.List;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 9:54 上午
 * @Desc: test_person表mapper类
 */
public interface PersonMapper {
    Person getPersonById(Integer id);
    int insertPerson(Person p);
    List<Person> getAllPersons();
}
