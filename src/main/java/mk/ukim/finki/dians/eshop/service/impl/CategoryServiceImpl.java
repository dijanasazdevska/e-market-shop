package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.repository.CategoryRepository;
import mk.ukim.finki.dians.eshop.repository.ProductRepository;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> findProductsByCategory(String name) {
        return productRepository.findProductByCategory(categoryRepository.findCategoryByName(name));

    }

    @Override
    public List<Product> findProductsByCategoryEN(String name) {
        return productRepository.findProductByCategory(categoryRepository.findCategoryByNameEN(name));
    }


}
