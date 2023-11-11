package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import praktukum.model.ReturnedData;
import praktukum.model.user.User;

@Epic("Изменение данных пользователя")
@DisplayName("Изменение данных пользователя")
public class UserUpdateTest extends TestBase {

    @Test
    @DisplayName("Обновление данных авторизованного пользователя")
    public void shouldUpdateAuthorizedUser() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        User updatedUser = new User("upd@example.org", "updated", "updated");
        createUser(user);
        ReturnedData returnedData = login(user).as(ReturnedData.class);
        Response response = updateUser(updatedUser, returnedData.getAccessToken());
        response.then().assertThat().statusCode(200);
        ReturnedData updateUserData = response.as(ReturnedData.class);
        deleteUser(returnedData.getAccessToken());
        Assert.assertTrue(updateUserData.getSuccess());
        Assert.assertNotNull(updateUserData.getUser().getEmail());
        Assert.assertNotNull(updateUserData.getUser().getName());
    }

    @Test
    @DisplayName("Обновление данных неавторизованного пользователя")
    public void shouldUpdateNonAuthorizedUser() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        User updatedUser = new User("upd@example.org", "updated", "updated");
        createUser(user);
        Response response = updateUser(updatedUser, null);
        response.then().assertThat().statusCode(401);
        ReturnedData updateUserData = response.as(ReturnedData.class);
        ReturnedData returnedData = login(user).as(ReturnedData.class);
        deleteUser(returnedData.getAccessToken());
        Assert.assertFalse(updateUserData.getSuccess());
        Assert.assertNotNull(updateUserData.getMessage());
    }
}