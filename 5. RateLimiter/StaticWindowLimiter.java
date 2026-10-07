import configs.LimiterType;
import configs.WindowConfig;
import policies.RateLimitPolicy;

import java.util.Map;

public class StaticWindowLimiter implements RateLimiter {
    private Map<String, Window> windows;

    @Override
    public boolean shouldRun(RateLimitPolicy policy) {
        return LimiterType.WINDOW.equals(policy.getLimiterType());
    }

    @Override
    public boolean allow(RateLimitPolicy policy, Request request) {
        WindowConfig config = (WindowConfig) policy.getLimiterConfig();
        String userId = request.getUserId(policy.getConfigType());

        Window window = windows.computeIfAbsent(userId, id -> new Window(id, config));
        window.reset(config);
        return window.tryConsume();
    }
}
