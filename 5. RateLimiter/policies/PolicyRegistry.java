package policies;

import configs.ConfigType;

import java.util.Map;


public class PolicyRegistry {
    private final Map<ConfigType, RateLimitPolicy> policies;

    public PolicyRegistry(Map<ConfigType, RateLimitPolicy> policies) {
        this.policies = policies;
    }

    public RateLimitPolicy get(ConfigType configType) {
        return policies.get(configType);
    }
}
