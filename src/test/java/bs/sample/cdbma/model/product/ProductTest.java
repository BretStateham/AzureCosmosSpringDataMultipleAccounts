package bs.sample.cdbma.model.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    static final String PRODUCT_ID_1 ="1";
    static final String PRODUCT_NAME_1 ="bar1";
    static final float PRODUCT_PRICE_1 = 100.00f;

    private Product PRODUCT_1;

    @BeforeEach
    void setUp() {
        PRODUCT_1 = new Product();
        PRODUCT_1.setId(PRODUCT_ID_1);
        PRODUCT_1.setName(PRODUCT_NAME_1);
        PRODUCT_1.setPrice(PRODUCT_PRICE_1);
    }

    @Test
    void canCreateProduct(){
        assertEquals(PRODUCT_ID_1, PRODUCT_1.getId());
        assertEquals(PRODUCT_NAME_1, PRODUCT_1.getName());
        assertEquals(PRODUCT_PRICE_1, PRODUCT_1.getPrice());
    }
}