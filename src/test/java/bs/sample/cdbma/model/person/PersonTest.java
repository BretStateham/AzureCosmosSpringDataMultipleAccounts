package bs.sample.cdbma.model.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonTest {

    static final String PERSON_ID_1 ="1";
    static final String PERSON_NAME_1 ="foo1";
    static final int PERSON_AGE_1 =42;

    private Person PERSON_1;

    @BeforeEach
    void Setup(){
        PERSON_1 = new Person();
        PERSON_1.setId(PERSON_ID_1);
        PERSON_1.setName(PERSON_NAME_1);
        PERSON_1.setAge(PERSON_AGE_1);
    }

    @Test
    void canCreatePerson(){
        assertEquals(PERSON_ID_1, PERSON_1.getId());
        assertEquals(PERSON_NAME_1, PERSON_1.getName());
        assertEquals(PERSON_AGE_1, PERSON_1.getAge());
    }

}
