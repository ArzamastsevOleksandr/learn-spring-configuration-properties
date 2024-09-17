package learn.springconfigurationproperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class LogConfigurations implements CommandLineRunner {

    final Configurations configurations;
    final ComplexEnvVariable complexEnvVariable;

    @Value("${custom.env-variable}")
    String customEnvVariable;

    @Override
    public void run(String... args) {
        log.info("{}", configurations);

        log.info("{}", configurations.configPerCurrency.get("USD").getClass());

        log.info("{}", configurations.configPerCurrency());

        log.info("{}", customEnvVariable);

        log.info("{}", complexEnvVariable);
    }
}
