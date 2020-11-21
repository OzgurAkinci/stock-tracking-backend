package com.dafu.mapserver.config;


import com.dafu.mapserver.domain.SRole;
import com.dafu.mapserver.domain.SUser;
import com.dafu.mapserver.repository.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.*;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    protected AppDao appDao;


    private List<TokenEnhancer> delegates = Collections.emptyList();

    public void setTokenEnhancers(List<TokenEnhancer> delegates) {
        this.delegates = delegates;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        return enhanceNew(appDao, accessToken, authentication);
    }

    public OAuth2AccessToken enhanceNew(AppDao providedAppDao, OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getUserAuthentication().getPrincipal();
        SUser daoSUser = providedAppDao.getUserDao().findByUserName(userDetails.getUsername());
        final Map<String, Object> additionalInformation = new HashMap<String, Object>();
        additionalInformation.put("id", daoSUser.getId());
        additionalInformation.put("username", daoSUser.getUsername());
        additionalInformation.put("email", daoSUser.getEmail());
        additionalInformation.put("permissions", providedAppDao.getUserDao().findPermissionsByUser(daoSUser.getId()));
        List<String> roles = new ArrayList<String>();
        for (SRole role : providedAppDao.getUserRoleDao().findByUser(daoSUser.getId())) {
            roles.add(role.getRoleName());
        }
        additionalInformation.put("roles", roles.toArray());
        tempResult.setAdditionalInformation(additionalInformation);

        OAuth2AccessToken result = tempResult;
        for (TokenEnhancer enhancer : delegates) {
            result = enhancer.enhance(result, authentication);
        }
        return result;
    }
}
