package uk.steers.blog.security;

public class SecurityConstants {
    public static final String SECRET = System.getenv("SIGNING_SECRET");
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
