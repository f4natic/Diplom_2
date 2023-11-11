package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import praktukum.model.ReturnedData;
import praktukum.model.user.User;

@Epic("Авторизация пользователя")
@DisplayName("Авторизация пользователя")
public class UserLoginTest extends TestBase {

    @Test
    @DisplayName("Авторизация под существующим пользователем")
    public void shouldLogin() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        createUser(user);
        Response response = login(user);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);
        deleteUser(returnedData.getAccessToken());
        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getAccessToken());
        Assert.assertNotNull(returnedData.getRefreshToken());
    }

    @Test
    @DisplayName("Авторизация под несуществующим пользователем")
    public void shouldNotLogin() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        Response response = login(user);
        response.then().assertThat().statusCode(401);
        ReturnedData returnedData = response.as(ReturnedData.class);
        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getMessage());
    }
}
