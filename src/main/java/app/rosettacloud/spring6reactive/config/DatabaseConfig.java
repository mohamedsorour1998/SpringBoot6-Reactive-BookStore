package app.rosettacloud.spring6reactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

// setting up the database schema using R2DBC,
//  which is the reactive counterpart to JDBC for databases that support non-blocking 
// database access.
@Configuration // Marks the class as a source of bean definitions.
@EnableR2dbcAuditing
public class DatabaseConfig {
    @Value("classpath:/schema.sql")
    Resource resource;

    // more specialized or less commonly used components
    // (like initializing a database with a specific script using R2DBC)
    // often require explicit configuration.
    @Bean
    // Each method annotated with @Bean acts as a bean factory for that specific
    // bean. It’s essential for defining infrastructure components, configuring
    // components not covered by auto-configuration, or when specific initialization
    // steps are needed (like in your example).
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(resource));
        return initializer;
    }
}
