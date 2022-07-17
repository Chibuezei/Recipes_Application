package recipes;
//presentation layer

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import recipes.secure.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;


    @PostMapping("/api/recipe/new")
    public ResponseEntity<String> addRecipe(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody Recipe recipe) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        recipe.setUser(user);
        recipe.setDate(LocalDateTime.now());
        Recipe recipe1 = recipeService.save(recipe);
        return new ResponseEntity<>("{\"id\": " + recipe1.getId() + "}", HttpStatus.OK);

    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<String> putRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable long id, @Valid @RequestBody Recipe recipe) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        Recipe recipeByUser = recipeService.findRecipeByIdAndAndUser(id, user);
        Recipe recipe1 = recipeService.findRecipeById(id);
        if (recipeService.findRecipeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (recipeByUser == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            recipe1.setName(recipe.getName());
            recipe1.setCategory(recipe.getCategory());
            recipe1.setDate(LocalDateTime.now());
            recipe1.setDescription(recipe.getDescription());
            recipe1.setIngredients(recipe.getIngredients());
            recipe1.setDirections(recipe.getDirections());
            recipeService.save(recipe1);
            return new ResponseEntity<>("{\"id\": " + recipe1.getId() + "}", HttpStatus.NO_CONTENT);
        }


    }


    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        if (recipeService.findRecipeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return recipeService.findRecipeById(id);
        }
    }


    @GetMapping("/api/recipe/search/")
    public List<Recipe> getByFilter(@RequestParam(value = "category", required = false) String category, @RequestParam(value = "name", required = false) String name) {
        if (category == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (name == null) {
            return recipeService.findByCategory(category.toLowerCase());
        } else if (category == null) {
            return recipeService.findByName(name.toLowerCase());
        } else {
            return recipeService.findByNameAndCategory(name, category);
        }
    }


    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        Optional<Recipe> delRecipe = Optional.ofNullable(recipeService.findRecipeById(id));
        Recipe recipeByUser = recipeService.findRecipeByIdAndAndUser(id, user);
        if (!delRecipe.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (recipeByUser == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            recipeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}


