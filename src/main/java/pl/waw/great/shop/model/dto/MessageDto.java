package pl.waw.great.shop.model.dto;

import javax.validation.constraints.NotBlank;

public class MessageDto {

    @NotBlank(message = "{titleNotBlank}")
    private String title;
    @NotBlank(message = "{textNotBlank}")
    private String text;
    @NotBlank(message = "{cityNotBlank}")
    private String city;
    @NotBlank(message = "{emailNotBlank}")
    private String email;

    public MessageDto(String title, String text, String city, String email) {
        this.title = title;
        this.text = text;
        this.city = city;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
