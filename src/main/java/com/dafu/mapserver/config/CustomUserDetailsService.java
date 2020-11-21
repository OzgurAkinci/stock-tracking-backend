package com.dafu.mapserver.config;


import com.dafu.mapserver.domain.SUser;
import com.dafu.mapserver.repository.SUserDao;
import com.dafu.mapserver.repository.SUserRoleDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Log LOG = LogFactory.getLog(CustomUserDetailsService.class);

    @Autowired
    SUserDao userDao;

    @Autowired
    SUserRoleDao userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LOG.info("loadUsesByUserName: " + userName);
        SUser SUser = userDao.findByUserName(userName);
        if (SUser == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return new CustomUserDetails(SUser, userRoleDao.findByUser(SUser.getId()));
    }
}
