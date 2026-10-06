package impl.payments.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PricingConfig {
    private StrategyType type;
}
