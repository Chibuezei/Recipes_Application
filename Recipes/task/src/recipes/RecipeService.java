package recipes;
//businessLayer

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import recipes.secure.User;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findByCategory(String category) {
        return recipeRepository.findByCategory(category);
    }

    public List<Recipe> findByName(String name) {
        return recipeRepository.findByName(name);
    }

    public Recipe findRecipeByIdAndAndUser(Long id, User user) {
        return recipeRepository.findRecipeByIdAndAndUser(id, user);
    }

    public List<Recipe> findByNameAndCategory(String name, String category) {
        return recipeRepository.findAllByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByDateDesc(name, category);
    }

    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    public void delete(long id) {
        recipeRepository.delete(findRecipeById(id));
    }
}
