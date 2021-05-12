package com.todo.demo.security.oauth2;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OauthLoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {
}
