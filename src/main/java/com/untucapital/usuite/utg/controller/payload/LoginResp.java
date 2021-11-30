package com.untucapital.usuite.utg.controller.payload;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

public class LoginResp {
    
    private String accessToken;
    private String tokenType = "Bearer";
    private String subject;
    
    public LoginResp(String accessToken, String subject) {
        this.accessToken = accessToken;
        this.subject = subject;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getTokenType() {
        return tokenType;
    }
    
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
