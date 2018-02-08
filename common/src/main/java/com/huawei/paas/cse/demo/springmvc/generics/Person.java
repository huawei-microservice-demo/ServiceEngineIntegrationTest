package com.huawei.paas.cse.demo.springmvc.generics;

import java.util.Date;

public class Person {
    String name;
    
    Long age;
    
    Date birth;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    
}
