package impl.payments.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultPricingConfig extends PricingConfig {
    private StrategyType type;
    private Double basePrice;
    private Double perHourPrice;
}
