package pl.waw.great.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.waw.great.shop.model.dto.CategoryDto;
import pl.waw.great.shop.model.mapper.CategoryMapper;
import pl.waw.great.shop.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDto> getAll() {
        return this.categoryRepository.findAll().stream()
                .map(category -> categoryMapper.commentToDto(category))
                .collect(Collectors.toList());
    }
}
