package impl.payments;

import impl.Ticket;
import impl.TicketStatus;
import impl.payments.config.DefaultPricingConfig;
import impl.payments.config.PricingConfig;
import impl.payments.config.StrategyType;

import java.util.Objects;

public class DefaultPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean shouldRun(PricingConfig config) {
        return !Objects.isNull(config) && StrategyType.DEFAULT.equals(config.getType());
    }

    @Override
    public Double giveTotalPayableAmount(PricingConfig config, Ticket ticket) {
        double totalPayableAmount = 0.0;
        if (config instanceof DefaultPricingConfig defaultPricingConfig) {
            Double basePrice = defaultPricingConfig.getBasePrice();
            Double perHourPrice = defaultPricingConfig.getPerHourPrice();
            long time = ticket.getExitTime().getTime() - ticket.getEntryTime().getTime();
            long timeInHours = time / (1000 * 60 * 60);
            totalPayableAmount = basePrice + (timeInHours * perHourPrice);
        }
        return totalPayableAmount;
    }

    @Override
    public boolean pay(PricingConfig config, Ticket ticket) {
        var totalPayableAmount = giveTotalPayableAmount(config, ticket);
        ticket.setTotalPayableAmount(totalPayableAmount);

        // this will call some payment gateway and will prompt the user some qr or something like a message on the app and will
        // return the status of the payment this part is not getting handled by us.


        ticket.setStatus(TicketStatus.PAID); // defaulting to paid but it will be the status that the gate will return us

        return true; // for now lets default to true
    }
}
