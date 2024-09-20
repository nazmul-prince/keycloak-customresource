package com.keycloak.customresource.services;

import com.keycloak.customresource.model.RolesModel;

import java.util.List;

public interface CustomRoleProviderService {
    RolesModel getRolesNamesByUserId(String userId);
    RolesModel getRolesNamesByUserIdCached(String userId);
    public RolesModel findRolesNamesByUserIdWithOutCustomQuery(String userId);
}
