# Recipes_Application
A multi-user web service with Spring Boot that stores all recipes in one place and allows retrieving, updating, and deleting recipes.
This project was completed as part of requirement for the JetBrains Backend Developer certification.

## Installation

Gradle is used as the build tool.


## Endpoints

```java
@PostMapping("/api/recipe/new")

@PutMapping("/api/recipe/{id}")

@GetMapping("/api/recipe/{id}")

@GetMapping("/api/recipe/search/")

@DeleteMapping("/api/recipe/{id}")

```

## Contributing
Pull requests are welcome. 

## License
None