package learn.springconfigurationproperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Setter
@ToString
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(value = "configurations", ignoreInvalidFields = true)
class Configurations {

    final ObjectMapper objectMapper;

    Server server = new Server();
    Client client = new Client();
    @Value("#{${configurations.keyValues}}")
    Map<String, String> keyValues = new HashMap<>();
    @Value("#{${configurations.configPerCurrency}}")
    Map<String, Map<String, String>> configPerCurrency = new HashMap<>();

    public Map<String, CurrencyConfig> configPerCurrency() {
        return configPerCurrency.entrySet().stream()
                .map(e -> {
                    CurrencyConfig currencyConfig = objectMapper.convertValue(e.getValue(), CurrencyConfig.class);
                    return Map.entry(e.getKey(), currencyConfig);
                })
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Setter
    @ToString
    public static class Server {
        String host;
        Integer port;
        Parameters parameters = new Parameters();

        @Setter
        @ToString
        public static class Parameters {
            String name;
            String fallbackName;
        }
    }

    @Setter
    @ToString
    public static class Client {
        String name;
        Integer timeoutSeconds;
    }

    @Setter
    @ToString
    public static class CurrencyConfig {
        String name;
        Integer rate;
    }

}
