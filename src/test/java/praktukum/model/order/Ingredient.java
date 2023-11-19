package praktukum.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Ingredient {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer fat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer carbohydrates;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer calories;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer  proteins;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer __v;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String image_mobile;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String image_large;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String image;

    public Ingredient() {
    }

    public Ingredient(String _id) {
        this._id = _id;
    }

    public Ingredient(String _id, String name, String type, Integer price, Integer fat, Integer carbohydrates,
                      Integer calories, Integer proteins, Integer __v, String image_mobile,
                      String image_large, String image) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.proteins = proteins;
        this.__v = __v;
        this.image_mobile = image_mobile;
        this.image_large = image_large;
        this.image = image;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public String getImage_mobile() {
        return image_mobile;
    }

    public void setImage_mobile(String image_mobile) {
        this.image_mobile = image_mobile;
    }

    public String getImage_large() {
        return image_large;
    }

    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", __v=" + __v +
                ", image_mobile='" + image_mobile + '\'' +
                ", image_large='" + image_large + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
