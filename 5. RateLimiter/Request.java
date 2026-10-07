import configs.ConfigType;

public class Request {
    String ip;
    String userId;
    String port;
    public String getUserId(ConfigType configType) {
        if (ConfigType.AUTHENTICATED.equals(configType)) {
            return this.userId;
        }

        if (ConfigType.UNAUTHENTICATED.equals(configType)) {
            return this.ip + ":" + this.port;
        }

        // pro and standard fold into auth/unauth for identifier purposes - they only
        // differ in which RateLimitPolicy applies, not in how the user is identified
        throw new IllegalArgumentException("unhandled configType: " + configType);
    }
}
