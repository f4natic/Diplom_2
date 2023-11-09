package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import praktukum.model.user.User;
import praktukum.model.user.ReturnedData;

@Epic("Тесты на создание пользователя")
public class UserCreationTest extends TestBase {
    @Test
    @DisplayName("Создание уникального пользователя")
    public void shouldSuccessCreateUniqUserTest() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        ReturnedData createUser = createUser(user).as(ReturnedData.class);
        deleteUser(login(user).as(ReturnedData.class).getAccessToken()).as(ReturnedData.class);
        Assert.assertTrue("Не удалось создать пользователя", createUser.getSuccess());
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрировался")
    public void shouldNoSuccessCreateUser() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        createUser(user);
        ReturnedData createUser = createUser(user).as(ReturnedData.class);
        deleteUser(login(user).as(ReturnedData.class).getAccessToken()).as(ReturnedData.class);
        Assert.assertFalse("Что-то пошло не так и пользователь создался", createUser.getSuccess());
    }

    @Test
    @DisplayName("Создание пользователя, без обязательных полей")
    public void shouldNoSuccessCreateUserIfRequiredFieldIsNull() throws JsonProcessingException {
        User user = new User();
        ReturnedData createUser = createUser(user).as(ReturnedData.class);
        Assert.assertFalse("Что-то пошло не так и пользователь создался", createUser.getSuccess());
    }
}
