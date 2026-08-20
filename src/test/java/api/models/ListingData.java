package api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListingData {
    private String name;
    private String category;
    private String condition;
    private String city;
    private String description;
    private String price;

    public ListingData(String name, String category, String condition, String city, String description, String price) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.city = city;
        this.description = description;
        this.price = price;
    }
}