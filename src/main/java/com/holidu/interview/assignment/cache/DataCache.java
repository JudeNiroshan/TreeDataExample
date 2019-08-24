package com.holidu.interview.assignment.cache;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Simple data cache for Tree data objects. This can be used when
 * there are many data objects being received from the 3rd party
 * data provider. Data can be cached to minimize the response time
 * for future in-coming http requests.
 *
 * Note: data expiration timeout is defined in application.yml
 */
@Service
public class DataCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCache.class);

    private static Instant lastUpdated = Instant.now();

    private static int expirationSeconds;

    private static ObjectNode[] data;

    @Value("${treedata.cache.expireSeconds}")
    public void setExpirationSeconds(int expirationSeconds) {
        DataCache.expirationSeconds = expirationSeconds;
    }

    /**
     * returns the data if is it available and not already expired
     * @return
     */
    public static Optional<ObjectNode[]> getData() {
        Instant plusSeconds = lastUpdated.plusSeconds(expirationSeconds);

        if (data == null || plusSeconds.isBefore(Instant.now())) {
            return Optional.empty();
        }

        return Optional.of(data);
    }

    /**
     * Set the new value in the cache
     * @param dataToSet
     */
    public static void updateCache(ObjectNode[] dataToSet) {
        LOGGER.debug("Updating cache with new data...");
        lastUpdated = Instant.now();
        data = dataToSet;
    }
}
