package bs.sample.cdbma.config.person;

import com.azure.spring.data.cosmos.config.CosmosConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestPropertySource("classpath:integration-tests.properties")
class PeopleCosmosDbConfigIT {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    void applicationContextExists(){
        assertNotNull(applicationContext);
    }

    @Test
    void cosmosConfigBeanExists(){
        assertNotNull(applicationContext.getBean("peopleCosmosConfig"));    }
}