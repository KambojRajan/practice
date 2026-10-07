package policies;

import configs.ConfigType;
import configs.LimiterConfig;
import configs.LimiterType;
import lombok.Data;

@Data
public class RateLimitPolicy {
    private ConfigType configType;
    private LimiterConfig limiterConfig;

    public LimiterType getLimiterType() {
        return limiterConfig.getLimiterType();
    }
}
