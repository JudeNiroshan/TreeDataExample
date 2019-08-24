package com.holidu.interview.assignment.provider;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.cache.DataCache;
import com.holidu.interview.assignment.exception.DataProviderCommunicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * This class manages the communication between NewYork City
 * Tree data. URL endpoint can be defined in application.yml
 */
@Component
public class NycTreeDataProvider implements TreeDataProvider {

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
        ObjectNode[] fetchedData;
        try {
            fetchedData = restTemplate.getForObject(URI.create(endpoint), ObjectNode[].class);
        } catch (RestClientException e) {
            throw new DataProviderCommunicationException(e.getMessage(), e, "cityofnewyork", endpoint);
        }
        DataCache.updateCache(fetchedData);

        return fetchedData;
    }
}
