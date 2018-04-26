package bookstore.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookDto {
    public int id;

    @Size(min = 1, message = "Title cannot be empty")
    public String title;

    @Size(min = 1, message = "Author cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Author name can contain only letters")
    public String authorName;

    @Range(min = 1)
    public int genreId;

    @Range(min = 1, max = 1000, message = "Price must be between 1 and 1000")
    public double price;

    @Range(min = 0, max = 1000, message = "Quantity must be between 0 and 1000")
    public int quantity;

    public BookDto(){}

    public BookDto(String title, String authorName, int genreId, double price, int quantity) {
        this.title = title;
        this.authorName = authorName;
        this.genreId = genreId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
