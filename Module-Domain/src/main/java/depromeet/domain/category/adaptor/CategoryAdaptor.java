package depromeet.domain.category.adaptor;

import static java.util.stream.Collectors.toList;

import depromeet.common.annotation.Adaptor;
import depromeet.domain.category.domain.Category;
import depromeet.domain.category.exception.CategoryNotFoundException;
import depromeet.domain.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adaptor
@RequiredArgsConstructor
public class CategoryAdaptor {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Transactional(readOnly = true)
    public Category findByName(String name) {
        return categoryRepository
                .findByName(name)
                .map(Category::toDomain)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    public List<Category> findOrExceptionCategories(List<String> categoryNames) {
        return categoryNames.stream().map(this::findByName).collect(toList());
    }

    @Transactional
    public Category createCategory(depromeet.domain.challenge.domain.Category category) {
        Category categoryEntity = Category.builder().name(category.getName()).build();
        return categoryRepository.save(categoryEntity);
    }
}
