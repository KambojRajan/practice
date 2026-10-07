package configs;

import lombok.Data;

@Data
public class WindowConfig implements LimiterConfig {
    private Long requestsPerWindow;
    private Long windowSizeSeconds;
    private Long start;

    @Override
    public LimiterType getLimiterType() {
        return LimiterType.WINDOW;
    }
}
