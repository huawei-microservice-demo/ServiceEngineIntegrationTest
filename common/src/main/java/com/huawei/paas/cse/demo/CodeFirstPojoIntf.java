/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.paas.cse.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huawei.paas.cse.demo.compute.Person;
import com.huawei.paas.cse.demo.server.User;

public interface CodeFirstPojoIntf {
    Map<String, User> testUserMap(Map<String, User> userMap);

    List<User> testUserArray(List<User> users);

    String[] testStrings(String[] input);

    byte[] testBytes(byte[] input);

    int reduce(int a, int b);

    Date addDate(Date date, long second);

    Person sayHello(Person user);

    String saySomething(String prefix, Person user);

    String sayHi(String name);

    String sayHi2(String name);

    boolean isTrue();

    String addString(List<String> s);
}
