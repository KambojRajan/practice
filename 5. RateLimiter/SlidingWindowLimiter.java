import configs.LimiterType;
import configs.SlidingWindowConfig;
import policies.RateLimitPolicy;

import java.util.Map;

public class SlidingWindowLimiter implements RateLimiter {
    private Map<String, SlidingWindow> windows;

    @Override
    public boolean shouldRun(RateLimitPolicy policy) {
        return LimiterType.SLIDING_WINDOW.equals(policy.getLimiterType());
    }

    @Override
    public boolean allow(RateLimitPolicy policy, Request request) {
        SlidingWindowConfig config = (SlidingWindowConfig) policy.getLimiterConfig();
        String userId = request.getUserId(policy.getConfigType());

        SlidingWindow window = windows.computeIfAbsent(userId, id -> new SlidingWindow(id, config));
        return window.tryConsume(config);
    }
}
