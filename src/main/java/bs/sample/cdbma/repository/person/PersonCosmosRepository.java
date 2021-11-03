package bs.sample.cdbma.repository.person;

import bs.sample.cdbma.model.person.Person;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCosmosRepository extends CosmosRepository<Person, String> {
}
