package com.holidu.interview.assignment.provider;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.cache.DataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class NycTreeDataProvider implements AbstractTreeDataProvider {

    private static final Logger log = LoggerFactory.getLogger(NycTreeDataProvider.class);

    @Value("${treedata.cityofnewyork.url}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ObjectNode[] getData() {

        return DataCache.getData().orElseGet(this::fetchRemoteData);
    }

    private ObjectNode[] fetchRemoteData() {
        log.debug("Calling remote endpoint=" + endpoint);
        ObjectNode[] fetchedData = restTemplate.getForObject(URI.create(endpoint), ObjectNode[].class);
        DataCache.updateCache(fetchedData);

        return fetchedData;
    }
}
