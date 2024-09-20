package com.keycloak.customresource.services;

import org.keycloak.models.KeycloakSession;

public interface CustomAssignedRoleCacheService {
    /**
     * Caches the provided data with the specified key.
     * 
     * @param key   the key under which the value will be cached
     * @param value the value to be cached
     */
    void cacheData(String key, Object value);

    /**
     * Retrieves the cached data associated with the specified key.
     * 
     * @param key the key of the cached data
     * @return the cached data, or null if no data is associated with the key
     */
    Object getCachedData(String key);
}
