package praktukum.model;

public enum IngredientType {
    BUN("bun"),
    MAIN("main"),
    SOUCE("sauce");

    private String type;

    IngredientType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
