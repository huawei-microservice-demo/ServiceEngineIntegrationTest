package com.huawei.paas.cse.test.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.huawei.cse.wkapp.loadtest.PerformanceClient;
import com.huawei.paas.cse.demo.TestMgr;
import com.huawei.paas.cse.demo.springmvc.client.SpringmvcClient;
import com.huawei.paas.cse.demo.springmvc.generics.GenericsTestServiceClient;

public class IntegrationIT {
    @Before
    public void setUp() throws Exception {
        PerformanceClient.main(null);
    }

    @Test
    public void testSpringMVC() throws Exception {
        SpringmvcClient.run();
        GenericsTestServiceClient.run();


        TestMgr.summary();
        Assert.assertTrue(TestMgr.isSuccess());
    }
}
