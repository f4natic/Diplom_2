package praktukum.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import praktukum.model.user.User;

import java.util.List;

public class Order {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Ingredient> ingredients;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer number;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User owner;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updatedAt;

    public Order() {
    }

    public Order(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Order(String _id, String name, Integer price, List<Ingredient> ingredients, Integer number,
                 User owner, String status, String createdAt, String updatedAt) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.number = number;
        this.owner = owner;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                ", number=" + number +
                ", owner=" + owner +
                ", status='" + status + '\'' +
                ", created='" + createdAt + '\'' +
                ", updateAt='" + updatedAt + '\'' +
                '}';
    }
}