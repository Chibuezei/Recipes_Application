package recipes;
//persistence layer

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import recipes.secure.User;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeById(Long id);

    Recipe findRecipeByIdAndAndUser(Long id, User user);


    List<Recipe> findByCategory(String category);

    List<Recipe> findByName(String name);

    List<Recipe> findAllByNameContainingIgnoreCaseOrderByDateDesc(String name);

    List<Recipe> findAllByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByDateDesc(String name, String category);

    List<Recipe> findByUser(String name);

}
