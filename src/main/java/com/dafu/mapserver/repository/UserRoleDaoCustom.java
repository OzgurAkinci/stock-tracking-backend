package com.dafu.mapserver.repository;


import com.dafu.mapserver.domain.SPermission;
import com.dafu.mapserver.domain.SRole;

import java.util.List;

public interface UserRoleDaoCustom {
    public List<SRole> findByUser(Integer userId);

    public List<SRole> findByPermission(Integer permissionId);

    public List<SPermission> findPermissionByRole(Integer roleId);
}
