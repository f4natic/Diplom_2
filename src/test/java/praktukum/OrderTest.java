package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import praktukum.model.ReturnedData;
import praktukum.model.order.Ingredient;
import praktukum.model.order.Order;
import praktukum.model.user.User;

import java.util.List;

@Epic("Тесты на работу с заказами")
@DisplayName("Тесты на работу с заказами")
public class OrderTest extends TestBase {
    @Test
    public void shouldCreateOrderOnNonAuthorizedUser() throws JsonProcessingException {
        List<Ingredient> ingredientsList = List.of(new Ingredient("61c0c5a71d1f82001bdaaa76"),
                new Ingredient("61c0c5a71d1f82001bdaaa79"),
                new Ingredient("61c0c5a71d1f82001bdaaa72"),
                new Ingredient("61c0c5a71d1f82001bdaaa6d"));
        Order order = new Order(ingredientsList);
        Response response = createOrder(order, null);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);
        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getOrder());
        Assert.assertNotNull(returnedData.getName());
    }

    @Test
    public void shouldCreateOrderOnAuthorizedUser() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        createUser(user);
        Response loginResponse = login(user);
        ReturnedData returnedLoginData = loginResponse.as(ReturnedData.class);
        List<Ingredient> ingredientsList = List.of(
                new Ingredient("61c0c5a71d1f82001bdaaa76"),
                new Ingredient("61c0c5a71d1f82001bdaaa79"),
                new Ingredient("61c0c5a71d1f82001bdaaa72"),
                new Ingredient("61c0c5a71d1f82001bdaaa6d"));
        Order order = new Order(ingredientsList);
        Response response = createOrder(order, returnedLoginData.getAccessToken());
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);
        deleteUser(returnedLoginData.getAccessToken());
        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getOrder());
        Assert.assertNotNull(returnedData.getName());
    }

    @Test
    public void shouldFailedCreateOrderWithoutIngredients() throws JsonProcessingException {
        Order order = new Order();
        Response response = createOrder(order, null);
        response.then().assertThat().statusCode(400);
        ReturnedData returnedData = response.as(ReturnedData.class);
        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getMessage());
    }

    @Test
    public void shouldFailedCreateOrderWithWrongIngredientsHash() throws JsonProcessingException {
        List<Ingredient> ingredientsList = List.of(new Ingredient());
        Order order = new Order(ingredientsList);
        System.out.println(mapper.writeValueAsString(order));
        Response response = createOrder(order, null);
        response.then().assertThat().statusCode(500);
    }

    @Test
    public void shouldReturnUserOrderWithoutAuthorization() {
        Response response = getUserOrders(null);
        response.then().assertThat().statusCode(401);
        ReturnedData returnedData = response.as(ReturnedData.class);
        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getMessage());
    }

    @Test
    public void shouldReturnUserOrder() throws JsonProcessingException {
        User user = new User("f4natic@example.org", "f4natic", "f4natic");
        createUser(user);
        Response loginResponse = login(user);
        ReturnedData returnedLoginData = loginResponse.as(ReturnedData.class);
        Response response = getUserOrders(returnedLoginData.getAccessToken());
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);
        deleteUser(returnedLoginData.getAccessToken());
        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getOrders());
        Assert.assertNotNull(returnedData.getTotal());
        Assert.assertNotNull(returnedData.getTotalToday());
    }
}
