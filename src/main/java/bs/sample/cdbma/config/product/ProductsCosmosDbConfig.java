package bs.sample.cdbma.config.product;

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
@EnableCosmosRepositories(basePackages = "bs.sample.cdbma.repository.product", cosmosTemplateRef = "productsCosmosTemplate")
public class ProductsCosmosDbConfig extends CosmosConfigurationSupport {

    @Value("${products.cosmos.uri}")
    private String uri;

    @Value("${products.cosmos.key}")
    private String key;

    @Value("${products.cosmos.database}")
    private String database;

    @Value("${products.cosmos.enableQueryMetrics}")
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

    @Bean("productsCosmosConfig")
    public CosmosConfig getPeopleCosmosConfig() {
        return CosmosConfig.builder()
                .enableQueryMetrics(enableQueryMetrics)
                .build();
    }

    @Bean("productsCosmosClientBuilder")
    public CosmosClientBuilder getPeopleCosmosClientBuilder() {
        return new CosmosClientBuilder()
                .key(key)
                .endpoint(uri);
    }

    @Bean("productsCosmosAsyncClient")
    public CosmosAsyncClient getPeopleCosmosAsyncClient(@Qualifier("productsCosmosClientBuilder") CosmosClientBuilder cosmosClientBuilder) {
        return CosmosFactory.createCosmosAsyncClient(cosmosClientBuilder);
    }

    @Bean("productsCosmosFactory")
    public CosmosFactory getPeopleCosmosFactory(@Qualifier("productsCosmosAsyncClient") CosmosAsyncClient cosmosAsyncClient) {
        return new CosmosFactory(cosmosAsyncClient, getDatabaseName());
    }

    @Bean("productsMappingCosmosConverter")
    public MappingCosmosConverter getPeopleMappingCosmosConverter(CosmosMappingContext cosmosMappingContext) {
        return new MappingCosmosConverter(cosmosMappingContext, this.objectMapper);
    }

    @Bean("productsCosmosTemplate")
    public CosmosTemplate getProductsCosmosTemplate(@Qualifier("productsCosmosFactory") CosmosFactory cosmosFactory,
                                               @Qualifier("productsCosmosConfig") CosmosConfig cosmosConfig,
                                               @Qualifier("productsMappingCosmosConverter") MappingCosmosConverter mappingCosmosConverter) {
        return new CosmosTemplate(cosmosFactory, cosmosConfig, mappingCosmosConverter);
    }
}
