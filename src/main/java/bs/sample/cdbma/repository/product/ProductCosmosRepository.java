package bs.sample.cdbma.repository.product;

import bs.sample.cdbma.model.product.Product;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCosmosRepository extends CosmosRepository<Product, String> {
}
