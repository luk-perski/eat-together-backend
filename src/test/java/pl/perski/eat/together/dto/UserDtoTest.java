package pl.perski.eat.together.dto;

import org.junit.Test;
import pl.perski.eat.together.api.v2.dto.user.UserDtoGet;
import pl.perski.eat.together.api.v2.dto.user.UserDtoPost;
import pl.perski.eat.together.api.v2.dto.user.UserMapper;
import pl.perski.eat.together.api.v2.dto.user.UserMapperImpl;
import pl.perski.eat.together.database.model.UserData;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

public class UserDtoTest {

    private UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Test
    public void whenConvertUserDataToUserDtoGet_thenCorrect() {
        UserData userData = UserData.builder()
                .id(1)
                .description(randomAlphabetic(256))
                .companyName(randomAlphabetic(8))
                .firstName(randomAlphabetic(8))
                .lastName(randomAlphabetic(8))
                .build();

        UserDtoGet userDtoGet = userMapper().toUserDtoGet(userData);
        assertEquals(userData.getId(), userDtoGet.getId());
        assertEquals(userData.getFirstName(), userDtoGet.getFirstName());
        assertEquals(userData.getLastName(), userDtoGet.getLastName());
        assertEquals(userData.getCompanyName(), userDtoGet.getCompanyName());
        assertEquals(userData.getDescription(), userDtoGet.getDescription());
    }

    @Test
    public void whenConvertUserDtoPostToUserData_thenCorrect() {
        UserDtoPost userDtoPost = UserDtoPost.builder()
                .firstName(randomAlphabetic(8))
                .lastName(randomAlphabetic(8))
                .companyName(randomAlphabetic(8))
                .description(randomAlphabetic(256))
                .userLocationAddress(randomAlphabetic(8))
                .userLocationLatitude(52.409528)
                .userLocationLongitude(16.912463)
                .build();

        UserData userData = userMapper().toUserData(userDtoPost);
        assertEquals(userDtoPost.getFirstName(), userData.getFirstName());
        assertEquals(userDtoPost.getLastName(), userData.getLastName());
        assertEquals(userDtoPost.getCompanyName(), userData.getCompanyName());
        assertEquals(userDtoPost.getDescription(), userData.getDescription());
        assertEquals(userDtoPost.getUserLocationAddress(), userData.getUserLocationAddress());
        assertEquals(userDtoPost.getUserLocationLatitude(), new Double(userData.getUserLocationLatitude()));
        assertEquals(userDtoPost.getUserLocationLongitude(), new Double(userData.getUserLocationLongitude()));
    }
}

