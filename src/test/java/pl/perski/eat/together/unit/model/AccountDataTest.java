package pl.perski.eat.together.unit.model;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.perski.eat.together.database.model.AccountData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountDataTest {

    private static AccountData objectOne;
    private static AccountData objectTwo;
    private static AccountData objectThree;

    @BeforeClass
    public static void setup() {
        Integer theSameId = 25;
        Integer differentId = 30;

        objectOne = buildAccountDataWithConstantEmailAndPass(theSameId);
        objectTwo = buildAccountDataWithConstantEmailAndPass(theSameId);
        objectThree = buildAccountDataWithConstantEmailAndPass(differentId);
    }

    private static AccountData buildAccountDataWithConstantEmailAndPass(Integer id) {
        String email = "test@test.com";
        String password = "q2D$#$s";

        return AccountData.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Test
    public void testEquals() {
        assertEquals(objectOne, objectTwo);
        assertNotEquals(objectOne, objectThree);
    }

    @Test
    public void testHashCode() {
        int hashCodeOne = objectOne.hashCode();
        int hashCodeTwo = objectTwo.hashCode();
        int hashCodeThree = objectThree.hashCode();

        assertEquals(hashCodeOne, hashCodeTwo);
        assertNotEquals(hashCodeOne, hashCodeThree);
    }
}
