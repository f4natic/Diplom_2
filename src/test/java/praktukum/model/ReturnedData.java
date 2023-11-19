package praktukum.model;

import praktukum.model.order.Ingredient;
import praktukum.model.order.Order;
import praktukum.model.user.User;

import java.util.List;

public class ReturnedData {
    private Boolean success;
    private String accessToken;
    private String refreshToken;
    private User user;
    private String name;
    private Order order;
    private List<Order> orders;
    private String message;
    private List<Ingredient> data;
    private Integer total;
    private Integer totalToday;

    public ReturnedData() {
    }

    public ReturnedData(Boolean success, String accessToken, String refreshToken,
                        User user, String name, Order order,
                        List<Order> orders, String message, List<Ingredient> data,
                        Integer total, Integer totalToday) {
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
        this.name = name;
        this.order = order;
        this.orders = orders;
        this.message = message;
        this.data = data;
        this.total = total;
        this.totalToday = totalToday;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Ingredient> getData() {
        return data;
    }

    public void setData(List<Ingredient> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(Integer totalToday) {
        this.totalToday = totalToday;
    }
}
