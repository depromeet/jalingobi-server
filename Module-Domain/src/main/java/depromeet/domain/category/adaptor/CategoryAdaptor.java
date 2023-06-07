package depromeet.domain.category.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.common.exception.CustomException;
import depromeet.common.exception.CustomExceptionStatus;
import depromeet.domain.category.domain.Category;
import depromeet.domain.category.repository.CategoryRepository;
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.CATEGORY_NOT_FOUND));
    }

    @Transactional
    public Category createCategory(depromeet.domain.challenge.domain.Category category) {
        Category categoryEntity = Category.builder().name(category.getName()).build();
        return categoryRepository.save(categoryEntity);
    }
}
