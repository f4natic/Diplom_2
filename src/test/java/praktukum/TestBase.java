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
import org.junit.BeforeClass;
import praktukum.model.user.User;

public class TestBase {

    private static final String BASE_URL = System.getenv("url") == null ? "https://stellarburgers.nomoreparties.site/" : System.getenv("url");
    private static final String API = System.getenv("api") == null ? "/api" : System.getenv("api");
    private static final Parser PARSER = Parser.fromContentType("application/json;charset=utf-8");
    protected static RequestSpecification specification;
    protected static ObjectMapper mapper;

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

    @Step("Авторизация пользователя")
    public Response login(User user) throws JsonProcessingException {
        return RestAssured.given(specification)
                .body(mapper.writeValueAsString(user))
                .post("/auth/login");
    }

    @Step("Выход пользователя из системы")
    public Response logout() {
        return null;
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String token) {
        return RestAssured.given(specification)
                .header("Authorization", String.format("%s", token))
                .delete("/auth/user");
    }
}
