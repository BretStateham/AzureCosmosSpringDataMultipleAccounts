package bs.sample.cdbma.repository.product;

import bs.sample.cdbma.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:integration-tests.properties")
class ProductCosmosRepositoryIT {

    static final String PRODUCT_ID_1 ="1";
    static final String PRODUCT_NAME_1 ="Widget";
    static final float PRODUCT_PRICE_1 = 100.00f;

    private Product PRODUCT_1;

    @Autowired
    ProductCosmosRepository productCosmosRepository;

    @BeforeEach
    void setUp() {
        //Initialize our test product
        PRODUCT_1 = new Product();
        PRODUCT_1.setId(PRODUCT_ID_1);
        PRODUCT_1.setName(PRODUCT_NAME_1);
        PRODUCT_1.setPrice(PRODUCT_PRICE_1);

        //Clear the database of data
        productCosmosRepository.deleteAll();
    }

    @Test
    void canSaveProduct(){
        Product savedProduct = productCosmosRepository.save(PRODUCT_1);
        assertNotNull(savedProduct);
        assertNotEquals(PRODUCT_1.getEtag(), savedProduct.getEtag());
    }
}