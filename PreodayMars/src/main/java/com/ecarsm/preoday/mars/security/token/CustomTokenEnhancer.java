package com.ecarsm.preoday.mars.security.token;

import com.ecarsm.preoday.mars.entity.LocalUser;
import com.ecarsm.preoday.mars.security.UserLogin;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UserLogin user = (UserLogin) authentication.getPrincipal();
        LocalUser authUser = user.getUser();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("user", new UserDTO(authUser.getName(), authUser.getEmail()));

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }

    @Data
    @AllArgsConstructor
    private class UserDTO {

        private String name;
        private String email;
    }

}
