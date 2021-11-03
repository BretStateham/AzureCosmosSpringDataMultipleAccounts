package bs.sample.cdbma.repository.person;

import bs.sample.cdbma.model.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:integration-tests.properties")
class PersonCosmosRepositoryIT {

    static final String PERSON_ID_1 ="1";
    static final String PERSON_NAME_1 ="Jan Modaal";
    static final int PERSON_AGE_1 =42;

    private Person PERSON_1;

    @Autowired PersonCosmosRepository personCosmosRepository;

    @BeforeEach
    void setUp() {
        //Initialize our test person
        PERSON_1 = new Person();
        PERSON_1.setId(PERSON_ID_1);
        PERSON_1.setName(PERSON_NAME_1);
        PERSON_1.setAge(PERSON_AGE_1);

        //Clear the database of data
        personCosmosRepository.deleteAll();
    }

    @Test
    void canSavePerson(){
        Person savedPerson = personCosmosRepository.save(PERSON_1);
        assertNotNull(savedPerson);
        assertNotEquals(PERSON_1.getEtag(), savedPerson.getEtag());
    }


}