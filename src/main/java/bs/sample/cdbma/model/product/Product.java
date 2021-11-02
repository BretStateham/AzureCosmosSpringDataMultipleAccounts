package bs.sample.cdbma.model.product;

import lombok.Data;

@Data
public class Product {

    private String id;

    private String name;

    private float price;

    private String etag;
}
