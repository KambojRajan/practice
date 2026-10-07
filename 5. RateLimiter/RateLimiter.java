import policies.RateLimitPolicy;

public interface RateLimiter {
    boolean shouldRun(RateLimitPolicy policy);

    boolean allow(RateLimitPolicy policy, Request request);
}
