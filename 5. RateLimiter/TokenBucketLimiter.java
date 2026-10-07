import configs.BucketConfig;
import configs.LimiterType;
import policies.RateLimitPolicy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketLimiter implements RateLimiter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean shouldRun(RateLimitPolicy policy) {
        return LimiterType.BUCKET.equals(policy.getLimiterType());
    }

    @Override
    public boolean allow(RateLimitPolicy policy, Request request) {
        BucketConfig config = (BucketConfig) policy.getLimiterConfig();
        String userId = request.getUserId(policy.getConfigType());

        Bucket bucket = buckets.computeIfAbsent(userId, id -> new Bucket(id, config));
        bucket.refillIfDue(config);
        return bucket.tryConsume();
    }
}
