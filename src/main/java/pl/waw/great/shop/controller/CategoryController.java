package pl.waw.great.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.waw.great.shop.model.dto.CategoryDto;
import pl.waw.great.shop.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<CategoryDto> getAll() {
        return this.categoryService.getAll();
    }
}
