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

package com.huawei.paas.cse.demo.framework;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.huawei.paas.cse.demo.TestMgr;

public class FrameworkVersionClient {
  private static final Logger LOG = LoggerFactory.getLogger(FrameworkVersionClient.class);
  public static void run() {
    String cseUrlPrefix = "cse://" + "customer-service" + "/framework" + "/version";
    RestTemplate invoker = RestTemplateBuilder.create();
    String version = invoker.getForObject(cseUrlPrefix, String.class);
    LOG.info("version:" + version);
    String sample = "CSE:[a-zA-Z0-9\\.]+;ServiceComb:[a-zA-Z0-9\\.]+";
    String sample2 = "ServiceComb:[a-zA-Z0-9\\.]+;CSE:[a-zA-Z0-9\\.]+";
    TestMgr.check(true, version.matches(sample) || version.matches(sample2) );
  }
}
