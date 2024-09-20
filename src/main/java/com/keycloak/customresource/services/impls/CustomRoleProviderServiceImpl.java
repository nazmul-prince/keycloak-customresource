package com.keycloak.customresource.services.impls;

import com.keycloak.customresource.model.RolesModel;
import com.keycloak.customresource.services.CustomAssignedRoleCacheService;
import com.keycloak.customresource.services.CustomRoleProviderService;
import com.keycloak.customresource.repositories.CustomRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.models.KeycloakSession;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomRoleProviderServiceImpl implements CustomRoleProviderService {

	private final CustomRoleRepository roleRepository;
	private final CustomAssignedRoleCacheService customAssignedRoleCacheService;

	@Override
	public RolesModel getRolesNamesByUserId(String userId) {
		final var rolesNamesByUserId = roleRepository.findRolesNamesByUserId(userId);
		RolesModel rolesModel = new RolesModel(rolesNamesByUserId);
		return rolesModel;
	}

	@Override
	public RolesModel getRolesNamesByUserIdCached(String userId) {
		RolesModel rolesModel;
		rolesModel = (RolesModel) customAssignedRoleCacheService.getCachedData(userId);

		if(rolesModel == null) {
			log.info("no cached data for user id: " + userId);
			final var rolesNamesByUserId = roleRepository.findRolesNamesByUserId(userId);
			rolesModel = new RolesModel(rolesNamesByUserId);
		}
		return rolesModel;
	}

	@Override
	public RolesModel findRolesNamesByUserIdWithOutCustomQuery(String userId) {
		final var rolesNamesByUserId = roleRepository.findRolesNamesByUserIdWithOutCustomQuery(userId);

		RolesModel rolesModel = new RolesModel(rolesNamesByUserId);
		return rolesModel;
	}

}
