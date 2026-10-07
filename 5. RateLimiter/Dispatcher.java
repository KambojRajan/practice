import lombok.AllArgsConstructor;
import policies.RateLimitPolicy;

import java.util.List;

@AllArgsConstructor
public class Dispatcher {
    List<RateLimiter> rateLimiters;

    boolean allow(RateLimitPolicy rateLimitPolicy, Request request) {
        for (RateLimiter rateLimiter : rateLimiters) {
            if (rateLimiter.shouldRun(rateLimitPolicy)) {
                return rateLimiter.allow(rateLimitPolicy, request);
            }
        }
        return false;
    }
}
