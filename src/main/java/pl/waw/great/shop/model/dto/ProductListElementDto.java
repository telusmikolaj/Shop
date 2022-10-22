package pl.waw.great.shop.model.dto;

public class ProductListElementDto {
    private String img;
    private String title;

    public ProductListElementDto() {
    }

    public ProductListElementDto(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
