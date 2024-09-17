package learn.springconfigurationproperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ToString
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(value = "complex-env-var", ignoreInvalidFields = true)
class ComplexEnvVariable {

    @ToString.Exclude
    final ObjectMapper objectMapper;

    Internal internal;

    public void setInternal(String internalString) throws JsonProcessingException {
        internal = objectMapper.readValue(internalString, Internal.class);
    }

    @ToString
    @Setter
    public static class Internal {
        String name;
        Map<String, CurrencyData> currencyDataPerCode;

        @ToString
        @Setter
        public static class CurrencyData {
            String name;
            Map<String, Integer> holders;
        }
    }

}
