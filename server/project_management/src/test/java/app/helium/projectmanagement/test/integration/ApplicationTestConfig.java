package app.helium.projectmanagement.test.integration;

import app.helium.projectmanagement.project.ProjectId;
import app.helium.projectmanagement.project.ProjectMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ApplicationTestConfig {

    @Value("${spring.kafka.producer.client-id}")
    private String clientId;
    @Value("${spring.kafka.producer.acks}")
    private String acks;
    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private String maxInFlightRequests;
    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private String enableIdempotency;
    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServer;
    @Value("${spring.kafka.producer.properties.schema.registry.url}")
    private String schemaRegistryURL;

    @Bean
    public ProducerFactory<ProjectId, ProjectMessage> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        properties.put("schema.registry.url", schemaRegistryURL);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotency);
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequests);
        properties.put(ProducerConfig.ACKS_CONFIG, acks);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<ProjectId, ProjectMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
