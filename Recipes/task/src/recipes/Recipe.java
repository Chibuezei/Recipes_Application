package recipes;

//businessLayer

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.secure.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
@NamedQuery(name = "Recipe.findByCategory", query = "select u from Recipe u where lower(u.category) = ?1 order by u.date desc")
@NamedQuery(name = "Recipe.findByName", query = "select u from Recipe u where lower(u.name) LIKE '%' || ?1 || '%' order by u.date desc")
@NamedQuery(name = "Recipe.findByUser", query = "select u from Recipe u where lower(u.user) LIKE '%' || ?1 || '%' order by u.date desc")


public class Recipe {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String category;
    @Column
    private LocalDateTime date;
    @Column
    @NotBlank
    private String description;
    @Column
    @NotNull
    @Size(min = 1)
    private String[] ingredients;
    @Column
    @NotNull
    @Size(min = 1)
    private String[] directions;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;


}
