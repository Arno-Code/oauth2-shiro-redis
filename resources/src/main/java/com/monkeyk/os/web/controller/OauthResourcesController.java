package com.monkeyk.os.web.controller;

import com.monkeyk.os.service.dto.UsernameDto;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom resource API
 * Protect by Oauth
 *
 * @author Shengzhao Li
 * @see org.apache.oltu.oauth2.rsfilter.OAuthFilter
 */
@Controller
@RequestMapping("/rs/")
public class OauthResourcesController {


    private static final Logger LOG = LoggerFactory.getLogger(OauthResourcesController.class);


    /**
     * RESTFUL
     * Return username API
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     */
    @RequiresRoles("User")
    @RequestMapping("username")
    @ResponseBody
    public UsernameDto username(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final String clientId = (String) request.getAttribute(OAuth.OAUTH_CLIENT_ID);
        LOG.debug("Current clientId: {}", clientId);

        final String username = request.getUserPrincipal().getName();
        LOG.debug("Current username: {}", username);

        return new UsernameDto(clientId, username);
    }


}