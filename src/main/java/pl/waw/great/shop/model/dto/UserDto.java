package pl.waw.great.shop.model.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "{nameNotBlank}")
    private String name;

    public UserDto() {
    }

    public UserDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
