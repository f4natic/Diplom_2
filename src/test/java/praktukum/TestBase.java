package praktukum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import praktukum.model.order.Order;
import praktukum.model.user.User;

public class TestBase {

    private static final String BASE_URL = System.getenv("url") == null ? "https://stellarburgers.nomoreparties.site/" : System.getenv("url");
    private static final String API = System.getenv("api") == null ? "/api" : System.getenv("api");
    private static final Parser PARSER = Parser.fromContentType("application/json;charset=utf-8");
    protected static RequestSpecification specification;
    protected static ObjectMapper mapper;

    protected User user;
    protected String accessToken;

    @Before
    public void init() throws JsonProcessingException {
        user = new User(Constants.EMAIL, Constants.PASSWORD, Constants.NAME);
        createUser(user);
    }

    @After
    public void tearDown() throws JsonProcessingException {
        deleteUser(accessToken);
    }

    @BeforeClass
    @Step("Создание Request Specification и ObjectMapper'а")
    public static void setUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(API)
                .setContentType(PARSER.getContentType())
                .setAccept(ContentType.JSON);
        specification = builder.build();
        mapper = new ObjectMapper();
    }

    @Step("Создание пользователя")
    public Response createUser(User user) throws JsonProcessingException {
        return RestAssured.given(specification)
                .when()
                .body(mapper.writeValueAsString(user))
                .post("/auth/register");
    }

    @Step("Обновление данных пользователя")
    public Response updateUser(User user, String token) throws JsonProcessingException {
        return RestAssured.given(specification)
                .when()
                .header("Authorization", String.format("%s", token))
                .body(mapper.writeValueAsString(user))
                .patch("/auth/user");
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String token) {
        return RestAssured.given(specification)
                .header("Authorization", String.format("%s", token))
                .delete("/auth/user");
    }

    @Step("Авторизация пользователя")
    public Response login(User user) throws JsonProcessingException {
        String usrStr = mapper.writeValueAsString(user);
        return RestAssured.given(specification)
                .when()
                .body(mapper.writeValueAsString(user))
                .post("/auth/login");
    }

    @Step("Создание заказа")
    public Response createOrder(Order order, String token) throws JsonProcessingException {
        return RestAssured.given(specification)
                .when()
                .header("Authorization", String.format("%s", token))
                .body(mapper.writeValueAsString(order))
                .post("/orders");
    }

    @Step("Получение списка заказов пользователя")
    public Response getUserOrders(String token) {
        return RestAssured.given(specification)
                .when()
                .header("Authorization", String.format("%s", token))
                .get("/orders");
    }

    @Step("Получение списка ингредиентов")
    public Response getIngredients() {
        return RestAssured.given(specification)
                .when()
                .get("/ingredients");
    }
}
