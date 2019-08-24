package com.holidu.interview.assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Every successful http request will be cached by a key made out of
 * request parameters. This cache is valid for 30 seconds. If the
 * user make the same request within 30 seconds, cached response will
 * be served.
 */
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static final String SAME_REQUEST_CACHE = "TreeSummaryStatistics";

    private final int sameRequestCacheTimeoutSeconds = 30;

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(SAME_REQUEST_CACHE);

        return cacheManager;
    }

    /**
     * Scheduled cache evict for every 30 seconds
     */
    @CacheEvict(allEntries = true, value = {SAME_REQUEST_CACHE})
    @Scheduled(fixedDelay = sameRequestCacheTimeoutSeconds * 1000, initialDelay = 500)
    public void reportCacheEvict() {
        log.debug("Flush " + SAME_REQUEST_CACHE + " Cache ");
    }
}