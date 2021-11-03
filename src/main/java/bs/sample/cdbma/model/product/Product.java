package bs.sample.cdbma.model.product;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
@Container(containerName = "${products.cosmos.container.Product}")
public class Product {

    @Id
    private String id;

    @PartitionKey
    private String name;

    private float price;

    @Version
    private String etag;
}
