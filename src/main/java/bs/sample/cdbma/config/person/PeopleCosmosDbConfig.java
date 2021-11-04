package bs.sample.cdbma.config.person;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.Constants;
import com.azure.spring.data.cosmos.CosmosFactory;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.config.CosmosConfigurationSupport;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.core.convert.MappingCosmosConverter;
import com.azure.spring.data.cosmos.core.mapping.CosmosMappingContext;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.IsNewAwareAuditingHandler;

@Configuration
@EnableCosmosRepositories(basePackages = "bs.sample.cdbma.repository.person", cosmosTemplateRef = "peopleCosmosTemplate")
public class PeopleCosmosDbConfig extends CosmosConfigurationSupport {

    @Value("${people.cosmos.uri}")
    private String uri;

    @Value("${people.cosmos.key}")
    private String key;

    @Value("${people.cosmos.database}")
    private String database;

    @Value("${people.cosmos.enableQueryMetrics}")
    private boolean enableQueryMetrics;

    @Qualifier(Constants.OBJECT_MAPPER_BEAN_NAME)
    @Autowired(required = false)
    private ObjectMapper objectMapper;

    @Qualifier(Constants.AUDITING_HANDLER_BEAN_NAME)
    @Autowired(required = false)
    private IsNewAwareAuditingHandler cosmosAuditingHandler;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean("peopleCosmosConfig")
    public CosmosConfig getPeopleCosmosConfig() {
        return CosmosConfig.builder()
                .enableQueryMetrics(enableQueryMetrics)
                .build();
    }

    @Bean("peopleCosmosClientBuilder")
    public CosmosClientBuilder getPeopleCosmosClientBuilder() {
        return new CosmosClientBuilder()
                .key(key)
                .endpoint(uri);
    }

    @Bean("peopleCosmosAsyncClient")
    public CosmosAsyncClient getPeopleCosmosAsyncClient(@Qualifier("peopleCosmosClientBuilder") CosmosClientBuilder cosmosClientBuilder) {
        return CosmosFactory.createCosmosAsyncClient(cosmosClientBuilder);
    }

    @Bean("peopleCosmosFactory")
    public CosmosFactory getPeopleCosmosFactory(@Qualifier("peopleCosmosAsyncClient") CosmosAsyncClient cosmosAsyncClient) {
        return new CosmosFactory(cosmosAsyncClient, getDatabaseName());
    }

    @Bean("peopleMappingCosmosConverter")
    public MappingCosmosConverter getPeopleMappingCosmosConverter(CosmosMappingContext cosmosMappingContext) {
        return new MappingCosmosConverter(cosmosMappingContext, this.objectMapper);
    }

    @Bean("peopleCosmosTemplate")
    public CosmosTemplate getPeopleCosmosTemplate(@Qualifier("peopleCosmosFactory") CosmosFactory cosmosFactory,
                                                  @Qualifier("peopleCosmosConfig") CosmosConfig cosmosConfig,
                                                  @Qualifier("peopleMappingCosmosConverter") MappingCosmosConverter mappingCosmosConverter) {
        return new CosmosTemplate(cosmosFactory, cosmosConfig, mappingCosmosConverter);
    }
}
