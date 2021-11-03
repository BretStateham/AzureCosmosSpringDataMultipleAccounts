package bs.sample.cdbma.model.person;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
@Container(containerName = "${people.cosmos.container.Person}")
public class Person {

    @Id
    private String id;

    @PartitionKey
    private String name;

    private int age;

    @Version
    private String etag;
}



