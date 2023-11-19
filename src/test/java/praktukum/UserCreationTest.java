package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktukum.model.user.User;
import praktukum.model.ReturnedData;

@Epic("Тесты на создание пользователя")
@DisplayName("Тесты на создание пользователя")
public class UserCreationTest extends TestBase {

    @Before
    public void init() throws JsonProcessingException {
        user = new User(Constants.EMAIL, Constants.PASSWORD, Constants.NAME);
    }
    @After
    public void tearDown() throws JsonProcessingException {
        deleteUser(login(user).as(ReturnedData.class).getAccessToken());
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    public void shouldSuccessCreateUniqUserTest() throws JsonProcessingException {
        Response response = createUser(user);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull( returnedData.getUser());
        Assert.assertNotNull( returnedData.getAccessToken());
        Assert.assertNotNull( returnedData.getRefreshToken());
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрировался")
    public void shouldNoSuccessCreateUser() throws JsonProcessingException {
        createUser(user);
        Response response = createUser(user);
        response.then().assertThat().statusCode(403);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertFalse("Что-то пошло не так и пользователь создался", returnedData.getSuccess());
        Assert.assertEquals("Что-то пошло не так и пользователь создался", returnedData.getMessage(), Constants.USER_ALREDY_EXISTS);
    }

    @Test
    @DisplayName("Создание пользователя, без обязательных полей")
    public void shouldNoSuccessCreateUserIfRequiredFieldIsNull() throws JsonProcessingException {
        User user = new User();
        Response response = createUser(user);
        response.then().assertThat().statusCode(403);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertFalse("Что-то пошло не так и пользователь создался", returnedData.getSuccess());
        Assert.assertEquals("Что-то пошло не так и пользователь создался", returnedData.getMessage(), Constants.EMPTY_REQUIRED_FIELD);
    }
}
