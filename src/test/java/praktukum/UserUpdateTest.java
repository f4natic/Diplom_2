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

@Epic("Тесты на изменение данных пользователя")
@DisplayName("Тесты на изменение данных пользователя")
public class UserUpdateTest extends TestBase {

    @Test
    @DisplayName("Обновление данных авторизованного пользователя")
    public void shouldUpdateAuthorizedUser() throws JsonProcessingException {
        User updatedUser = new User("updated@example.org", "updated", "updated");
        accessToken = login(user).as(ReturnedData.class).getAccessToken();
        Response response = updateUser(updatedUser, accessToken);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getUser().getEmail());
        Assert.assertNotNull(returnedData.getUser().getName());
    }

    @Test
    @DisplayName("Обновление данных неавторизованного пользователя")
    public void shouldUpdateNonAuthorizedUser() throws JsonProcessingException {
        Response response = updateUser(user, null);
        response.then().assertThat().statusCode(401);
        ReturnedData returnedData = response.as(ReturnedData.class);
        accessToken = login(user).as(ReturnedData.class).getAccessToken();

        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertEquals(returnedData.getMessage(), Constants.SHOULD_BE_AUTHORIZED);
    }
}