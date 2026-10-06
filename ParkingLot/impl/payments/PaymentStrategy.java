package impl.payments;

import impl.Ticket;
import impl.payments.config.PricingConfig;

public interface PaymentStrategy {
    boolean shouldRun(PricingConfig config);

    Double giveTotalPayableAmount(PricingConfig config, Ticket ticket);

    boolean pay(PricingConfig config, Ticket ticket);
}
