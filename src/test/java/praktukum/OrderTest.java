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
import praktukum.model.order.Ingredient;
import praktukum.model.order.Order;
import praktukum.model.user.User;

import java.util.List;

@Epic("Тесты на работу с заказами")
@DisplayName("Тесты на работу с заказами")
public class OrderTest extends TestBase {

    @Test
    @DisplayName("Создание заказа неавторизованным пользователем")
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
        Assert.assertNotNull(returnedData.getOrder().getNumber());
        Assert.assertNotNull(returnedData.getName());
    }

    @Test
    @DisplayName("Создание заказа авторизованным пользователем")
    public void shouldCreateOrderOnAuthorizedUser() throws JsonProcessingException {
        accessToken = login(user).as(ReturnedData.class).getAccessToken();
        List<Ingredient> ingredientsList = List.of(
                new Ingredient("61c0c5a71d1f82001bdaaa76"),
                new Ingredient("61c0c5a71d1f82001bdaaa79"),
                new Ingredient("61c0c5a71d1f82001bdaaa72"),
                new Ingredient("61c0c5a71d1f82001bdaaa6d"));
        Order order = new Order(ingredientsList);
        Response response = createOrder(order, accessToken);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getOrder());
        Assert.assertNotNull(returnedData.getName());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void shouldFailedCreateOrderWithoutIngredients() throws JsonProcessingException {
        Order order = new Order();
        Response response = createOrder(order, null);
        response.then().assertThat().statusCode(400);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertEquals(returnedData.getMessage(), Constants.INGREDIENTS_MUST_BE);
    }

    @Test
    @DisplayName("Создание заказа с невалидным хешем ингредиента")
    public void shouldFailedCreateOrderWithWrongIngredientsHash() throws JsonProcessingException {
        List<Ingredient> ingredientsList = List.of(new Ingredient());
        Order order = new Order(ingredientsList);
        Response response = createOrder(order, null);
        response.then().assertThat().statusCode(500);
    }

    @Test
    @DisplayName("Получение списка заказов пользователя без авторизации")
    public void shouldReturnUserOrderWithoutAuthorization() {
        Response response = getUserOrders(null);
        response.then().assertThat().statusCode(401);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertFalse(returnedData.getSuccess());
        Assert.assertEquals(returnedData.getMessage(), Constants.SHOULD_BE_AUTHORIZED);
    }

    @Test
    @DisplayName("Получение списка заказов пользователя с авторизацией")
    public void shouldReturnUserOrder() throws JsonProcessingException {
        accessToken = login(user).as(ReturnedData.class).getAccessToken();
        Response response = getUserOrders(accessToken);
        response.then().assertThat().statusCode(200);
        ReturnedData returnedData = response.as(ReturnedData.class);

        Assert.assertTrue(returnedData.getSuccess());
        Assert.assertNotNull(returnedData.getOrders());
        Assert.assertNotNull(returnedData.getTotal());
        Assert.assertNotNull(returnedData.getTotalToday());
    }
}
