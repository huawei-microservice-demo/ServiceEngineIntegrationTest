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

package com.huawei.paas.cse.demo.springmvc.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.huawei.paas.cse.demo.CodeFirstRestTemplate;
import com.huawei.paas.cse.demo.TestMgr;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.springmvc.reference.CseHttpEntity;
import org.apache.servicecomb.swagger.invocation.Response;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author   
 * @version  [版本号, 2017年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class CodeFirstRestTemplateSpringmvc extends CodeFirstRestTemplate {

    @RpcReference(microserviceName = "customer-service", schemaId = "codeFirst")
    private CodeFirstSprigmvcIntf intf;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void testExtend(RestTemplate template, String cseUrlPrefix) {
        super.testExtend(template, cseUrlPrefix);

        testResponseEntity("springmvc", template, cseUrlPrefix);
        testIntf();
    }

    private void testIntf() {
        Date date = new Date();

        ResponseEntity<Date> responseEntity = intf.responseEntity(date);
        TestMgr.check(date, responseEntity.getBody());
        TestMgr.check(true, ((String)responseEntity.getHeaders().getFirst("h1")).contains("x-cse-src-microservice=loadtestclient"));
        TestMgr.check(true, ((String)responseEntity.getHeaders().getFirst("h2")).contains("x-cse-src-microservice=loadtestclient"));

        checkStatusCode("springmvc", 202, responseEntity.getStatusCode());

        Response cseResponse = intf.cseResponse();
        TestMgr.check("User [name=nameA, age=100, index=0]", cseResponse.getResult());
        TestMgr.check(true, ((String)cseResponse.getHeaders().getFirst("h1")).contains("x-cse-src-microservice=loadtestclient"));
        TestMgr.check(true, ((String)cseResponse.getHeaders().getFirst("h2")).contains("x-cse-src-microservice=loadtestclient"));
    }

    private void testResponseEntity(String microserviceName, RestTemplate template, String cseUrlPrefix) {
        Map<String, Object> body = new HashMap<>();
        Date date = new Date();
        body.put("date", date);

        CseHttpEntity<Map<String, Object>> httpEntity = new CseHttpEntity<>(body);
        httpEntity.addContext("contextKey", "contextValue");

        ResponseEntity<Date> responseEntity =
            template.exchange(cseUrlPrefix + "responseEntity", HttpMethod.POST, httpEntity, Date.class);
        TestMgr.check(date, responseEntity.getBody());
        TestMgr.check(true, ((String)responseEntity.getHeaders().getFirst("h1")).contains("x-cse-src-microservice=loadtestclient"));
        TestMgr.check(true, ((String)responseEntity.getHeaders().getFirst("h2")).contains("x-cse-src-microservice=loadtestclient"));
        checkStatusCode(microserviceName, 202, responseEntity.getStatusCode());
    }
}
