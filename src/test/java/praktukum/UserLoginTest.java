package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktukum.model.ReturnedData;
import praktukum.model.user.User;

@Epic("Тесты на авторизацию пользователя")
@DisplayName("Тесты на авторизацию пользователя")
public class UserLoginTest extends TestBase {

    @Test
    @DisplayName("Авторизация под существующим пользователем")
    public void shouldLogin() throws JsonProcessingException {
        Response response = login(user);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);
        accessToken = returnedData.getAccessToken();

        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull( returnedData.getUser());
        Assert.assertNotNull( returnedData.getAccessToken());
        Assert.assertNotNull( returnedData.getRefreshToken());
    }

    @Test
    @DisplayName("Авторизация под несуществующим пользователем")
    public void shouldNotLogin() throws JsonProcessingException {
        Response response = login(new User());
        response.then().assertThat().statusCode(401);
        ReturnedData returnedData = response.as(ReturnedData.class);
        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertEquals(returnedData.getMessage(), Constants.DATA_INCORRECT);
    }
}
