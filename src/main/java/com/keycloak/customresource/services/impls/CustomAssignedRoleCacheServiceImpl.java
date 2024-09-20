package com.keycloak.customresource.services.impls;

import com.keycloak.customresource.repositories.impls.CustomRoleRepositoryImpl;
import com.keycloak.customresource.services.CustomAssignedRoleCacheService;
import org.infinispan.Cache;
import org.keycloak.connections.infinispan.InfinispanConnectionProvider;
import org.keycloak.models.KeycloakSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomAssignedRoleCacheServiceImpl implements CustomAssignedRoleCacheService {

    private final Logger log = LoggerFactory.getLogger(CustomAssignedRoleCacheServiceImpl.class);
    private final String CACHE_PREFIX = "assigned-role-";
    private Cache<String, Object> cache;

    public CustomAssignedRoleCacheServiceImpl(KeycloakSession session) {
        initilizeCacheManager(session);
    }

    private void initilizeCacheManager(KeycloakSession session) {
        log.info("initializing cache manager");
        InfinispanConnectionProvider cacheProvider = session.getProvider(InfinispanConnectionProvider.class);
        if (cacheProvider == null) {
            throw new IllegalStateException("InfinispanConnectionProvider not found. Ensure that Keycloak is configured correctly.");
        }
        this.cache = cacheProvider.getCache("assigned-role-cache");
        this.cache = cacheProvider.getCache("users");

        this.cache.forEach((key, value) -> log.info(key));

        if (this.cache == null) {
            throw new IllegalStateException("Cache 'custom-cache' not found. Ensure that it is configured in Keycloak.");
        }

    }
    /**
     * Caches the provided data with the specified key.
     *
     * @param key   the key under which the value will be cached
     * @param value the value to be cached
     */
    @Override
    public void cacheData(String key, Object value) {
        if (cache != null) {
            cache.put(CACHE_PREFIX + key, value);
        } else {
            throw new IllegalStateException("Cache is not initialized. Please call initializeCache() first.");
        }
    }

    /**
     * Retrieves the cached data associated with the specified key.
     *
     * @param key the key of the cached data
     * @return the cached data, or null if no data is associated with the key
     */
    @Override
    public Object getCachedData(String key) {
        if (cache != null) {
            return cache.get(CACHE_PREFIX + key);
        } else {
            throw new IllegalStateException("Cache is not initialized. Please call initializeCache() first.");
        }
    }
}
