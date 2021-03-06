package com.heli.oa.common.service;

import org.springframework.stereotype.Service;

import java.util.Set;

public interface RolePermissionService {
    Set<String> listRoles(String userName);
    Set<String> listPermissions(String userName);
}
