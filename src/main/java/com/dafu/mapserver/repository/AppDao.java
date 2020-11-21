package com.dafu.mapserver.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AppDao {

    @Autowired
    SParameterDao parameterDao;

    @Autowired
    SUserDao userDao;

    @Autowired
    SRoleDao roleDao;

    @Autowired
    SUserRoleDao userRoleDao;

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    OauthTokenDao oauthTokenDao;


}
