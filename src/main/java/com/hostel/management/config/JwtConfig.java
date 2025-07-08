package com.hostel.management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT token configuration properties.
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private long expirationMs;
    private String tokenPrefix;
    private String headerString;

    // Getters and setters
    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public long getExpirationMs() { return expirationMs; }
    public void setExpirationMs(long expirationMs) { this.expirationMs = expirationMs; }
    public String getTokenPrefix() { return tokenPrefix; }
    public void setTokenPrefix(String tokenPrefix) { this.tokenPrefix = tokenPrefix; }
    public String getHeaderString() { return headerString; }
    public void setHeaderString(String headerString) { this.headerString = headerString; }
}
