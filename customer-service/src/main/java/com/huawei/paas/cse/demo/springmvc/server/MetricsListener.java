package com.huawei.paas.cse.demo.springmvc.server;

import org.apache.servicecomb.foundation.common.event.EventManager;

import com.google.common.eventbus.Subscribe;
import com.huawei.paas.monitor.ConsoleMonitorDataEvent;

public class MetricsListener {
    String latestMetrics;

    public MetricsListener() {
        EventManager.register(this);
    }

    @Subscribe
    public void onMonitorDataEvent(ConsoleMonitorDataEvent event) {
        this.latestMetrics = event.getJsonData();
    }
    
    public String getLatestMetrics() {
        return latestMetrics;
    }
}
