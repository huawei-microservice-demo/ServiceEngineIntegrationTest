package com.huawei.paas.cse.demo.springmvc.generics;

import java.io.IOException;
import java.util.List;

import org.apache.servicecomb.foundation.common.utils.JsonUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestSchema(schemaId = "generics")
@RequestMapping(path = "/generics")
public class GenericsTestService {
    @GetMapping(path = "/context")
    public String getContext() {
        return ContextUtils.getInvocationContext().getContext("session_id");
    }

    @PostMapping(path = "/listLong")
    public List<Long> getListLong(@RequestBody List<Long> listData) {
        if (!(listData.get(0) instanceof Long)) {
            throw new InvocationException(400, "", "");
        }
        String p = ContextUtils.getInvocationContext().getContext("x_test_person");
        if (p != null) {
            try {
                Person person = JsonUtils.readValue(p.getBytes(), Person.class);
                if (!person.getName().equals("not allow chinese in http header")) {
                    throw new InvocationException(400, "", "");
                }
            } catch (JsonParseException e) {
                throw new InvocationException(400, "", "");
            } catch (JsonMappingException e) {
                throw new InvocationException(400, "", "");
            } catch (IOException e) {
                throw new InvocationException(400, "", "");
            }
        }
        return listData;
    }

    @PostMapping(path = "/templateListLong")
    public Holder<List<Long>> templateListLong(@RequestBody Holder<List<Long>> personHolder) {
        List<Long> listData = personHolder.getData();
        if (!(listData.get(0) instanceof Long)) {
            throw new InvocationException(400, "", "");
        }
        return personHolder;
    }

    @PostMapping(path = "/templateListPerson")
    public Holder<List<Person>> templateListPerson(@RequestBody Holder<List<Person>> personHolder) {
        List<Person> listData = personHolder.getData();
        if (!(listData.get(0) instanceof Person)) {
            throw new InvocationException(400, "", "");
        }
        return personHolder;
    }

    @PostMapping(path = "/template")
    public Holder<Person> getHolder(@RequestBody Holder<Person> personHolder) {
        Person p = personHolder.getData();
        System.out.println(p.getName());
        return personHolder;
    }

    @PostMapping(path = "/templateHolderHolder")
    public Holder<Person> getHolderHolderHolder(@RequestBody Holder<Holder<Person>> personHolder) {
        Person p = personHolder.getData().getData();
        System.out.println(p.getName());
        return personHolder.getData();
    }

    @PostMapping(path = "/templateLong")
    public Holder<Long> getHolderLong(@RequestBody Holder<Long> personHolder) {
        Long a = new Long(personHolder.getData().longValue());
        return new Holder<Long>(a);
    }

    @PostMapping(path = "/templateWrapped")
    public WapperHolder getHolderWrapped(@RequestBody WapperHolder personHolder) {
        Holder<Person> tt = personHolder;
        Person p = tt.getData();
        System.out.println(p.getName());
        return personHolder;
    }
}
