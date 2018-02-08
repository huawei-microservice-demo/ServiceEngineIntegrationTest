package com.huawei.paas.cse.demo.springmvc.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.servicecomb.core.CseContext;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.huawei.paas.cse.demo.TestMgr;

public class GenericsTestServiceClient {
    private static RestTemplate cseRestTemplate = RestTemplateBuilder.create();

    //    private static RestTemplate normalRestTemplate = new RestTemplate();
    private static String server = "cse://customer-service";

    public static void run() {
        CseContext.getInstance().getConsumerProviderManager().setTransport("customer-service", "rest");

        testHolder();
        testListLong();
        testtemplateListLong();
        testtemplateListPerson();
        testgetHolderHolderHolder();
        testtemplateLong();
        testtemplateWrapped();
    }

    private static void testHolder() {
        Holder<Person> holder = new Holder<Person>();
        Person person = new Person();
        person.setAge(3000L);
        person.setBirth(new Date(633333333333L));
        person.setName("test");
        holder.setData(person);
        holder.setStatus(3000);

        @SuppressWarnings("unchecked")
        Holder<Person> result =
            cseRestTemplate.postForObject(server + "/generics/template", holder, holder.getClass());
        TestMgr.check(result.getData().getClass(), Person.class);
        TestMgr.check(result.getData().getName(), "test");
        TestMgr.check(result.getData().getAge(), 3000L);
        TestMgr.check(result.getData().getBirth().getTime(), new Date(633333333333L).getTime());
    }

    private static void testListLong() {
        List<Long> data = new ArrayList<>();
        data.add(34L);
        @SuppressWarnings("unchecked")
        List<Long> result = cseRestTemplate.postForObject(server + "/generics/listLong", data, List.class);
        TestMgr.check(result.get(0).getClass(), Long.class);
        TestMgr.check(result.get(0), Long.valueOf(34L));
    }

    private static void testtemplateListLong() {
        Holder<List<Long>> holder = new Holder<List<Long>>();
        List<Long> data = new ArrayList<>();
        data.add(34L);
        holder.setData(data);
        holder.setStatus(300);

        @SuppressWarnings("unchecked")
        Holder<List<Long>> result =
            cseRestTemplate.postForObject(server + "/generics/templateListLong", holder, Holder.class);
        TestMgr.check(result.getData().get(0).getClass(), Long.class);
        TestMgr.check(result.getData().get(0), Long.valueOf(34L));
    }

    private static void testtemplateListPerson() {
        Holder<List<Person>> holder = new Holder<List<Person>>();
        List<Person> data = new ArrayList<>();
        Person person = new Person();
        person.setName("test");
        data.add(person);
        holder.setData(data);
        holder.setStatus(300);

        @SuppressWarnings("unchecked")
        Holder<List<Person>> result =
            cseRestTemplate.postForObject(server + "/generics/templateListPerson", holder, Holder.class);
        TestMgr.check(result.getData().get(0).getClass(), Person.class);
        TestMgr.check(result.getData().get(0).getName(), "test");
    }

    private static void testgetHolderHolderHolder() {
        Holder<Holder<Person>> hodlerW = new Holder<>();
        Holder<Person> holder = new Holder<Person>();
        Person person = new Person();
        person.setName("test");
        holder.setData(person);
        holder.setStatus(3000);
        hodlerW.setData(holder);
        hodlerW.setStatus(3001);

        @SuppressWarnings("unchecked")
        Holder<Person> result =
            cseRestTemplate.postForObject(server + "/generics/templateHolderHolder", hodlerW, Holder.class);
        TestMgr.check(result.getStatus(), 3000);
        Person p = result.getData();
        TestMgr.check(p.getName(), "test");
    }

    private static void testtemplateLong() {
        Holder<Long> hodlerW = new Holder<>();
        hodlerW.setData(3300L);
        hodlerW.setStatus(3001);

        @SuppressWarnings("unchecked")
        Holder<Long> result =
            cseRestTemplate.postForObject(server + "/generics/templateLong", hodlerW, Holder.class);
        TestMgr.check(result.getStatus(), 0);
        Long p = result.getData();
        TestMgr.check(p, 3300);
    }

    private static void testtemplateWrapped() {
        WapperHolder hodlerW = new WapperHolder();
        Person person = new Person();
        person.setName("test");
        hodlerW.setData(person);
        hodlerW.setStatus(3001);

        WapperHolder result =
            cseRestTemplate.postForObject(server + "/generics/templateWrapped", hodlerW, WapperHolder.class);
        TestMgr.check(result.getStatus(), 3001);
        Person p = result.getData();
        TestMgr.check(p.getName(), "test");
    }
}
