package bs.sample.cdbma.config.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductsCosmosDbConfigIT {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    void applicationContextExists(){
        assertNotNull(applicationContext);
    }

    @Test
    void cosmosConfigBeanExists(){
        assertNotNull(applicationContext.getBean("productsCosmosConfig"));
    }

}
