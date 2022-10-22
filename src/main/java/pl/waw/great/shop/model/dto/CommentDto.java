package pl.waw.great.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CommentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int index;
    @NotBlank(message = "{nameNotBlank}")
    private String name;

    @NotBlank(message = "{emailNotBlank}")
    private String email;

    @NotBlank(message = "{textNotBlank}")
    private String text;

    public CommentDto() {
    }

    public CommentDto(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDto)) return false;
        CommentDto that = (CommentDto) o;
        return index == that.index && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, email, text);
    }
}
