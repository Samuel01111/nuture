package br.com.nuture.config;

import br.com.nuture.model.*;
import br.com.nuture.repository.DietRepository;
import br.com.nuture.repository.IngredientRepository;
import br.com.nuture.repository.RecipeRepository;
import br.com.nuture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    DietRepository dietRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public void run(String... args) throws Exception {

        // Saving users with their phones
        // userRepository.saveAll(List.of(
        //         User.builder().name("José Silva").cpf(84757483471L).birthday(LocalDate.of(1990, 12, 30)).email("jose@email.com").weight("70kg").height("170cm")
        //                 .foodFrequency(FoodFrequency.THREE_MEALS).password(encoder.encode("senha123")).sex("male").phone(new Phone(null, 55, 11, 950312033, null)).build(),
        //         User.builder().name("Maria Silva").cpf(23465458909L).birthday(LocalDate.of(1960, 9, 12)).email("maria.silva@email.com").weight("120kg").height("163cm")
        //                 .foodFrequency(FoodFrequency.THREE_MEALS).password(encoder.encode("mariasilva")).sex("female").phone(new Phone(null, 55, 76, 908845355, null)).build(),
        //         User.builder().name("Marcela Inês").cpf(12345678908L).birthday(LocalDate.of(2002, 4, 28)).email("mines@email.com").weight("55kg").height("168cm")
        //                 .foodFrequency(FoodFrequency.FOUR_MEALS).password(encoder.encode("marcela28042002")).sex("female").phone(new Phone(null, 55, 33, 992341101, null)).build(),
        //         User.builder().name("Alexandre Oliveira").cpf(76534565455L).birthday(LocalDate.of(2010, 7, 13)).email("aoliveira@email.com").weight("100kg").height("175cm")
        //                 .foodFrequency(FoodFrequency.FOUR_MEALS).password(encoder.encode("tommy2020")).sex("male").phone(new Phone(null, 55, 21, 989238812, null)).build(),
        //         User.builder().name("Grace Williams").cpf(94836423742L).birthday(LocalDate.of(2000, 5, 6)).email("graceeee@email.com").weight("92kg").height("162cm")
        //                 .foodFrequency(FoodFrequency.THREE_MEALS).password(encoder.encode("graceeeeeeeee")).sex("female").phone(new Phone(null, 11, 22, 960618564, null)).build()
        // ));

        // Ingredients for each recipe (when a recipe is added, so are its ingredients)
        Ingredient pasta1 = new Ingredient(null, "Pasta", "500g", "Não perecível", Recipe.builder().id(1L).build());
        Ingredient pasta2 = new Ingredient(null, "Pasta", "500g", "Não perecível", Recipe.builder().id(5L).build());
        Ingredient tomato1 = new Ingredient(null, "Tomato", "5", "Fruit", Recipe.builder().id(1L).build());
        Ingredient tomato2 = new Ingredient(null, "Tomato", "5", "Fruit", Recipe.builder().id(4L).build());
        Ingredient egg1 = new Ingredient(null, "Egg", "2", "Protein", Recipe.builder().id(2L).build());
        Ingredient egg2 = new Ingredient(null, "Egg", "2", "Protein", Recipe.builder().id(3L).build());
        Ingredient bread1 = new Ingredient(null, "Bread", "1", "Bread", Recipe.builder().id(2L).build());
        Ingredient bread2 = new Ingredient(null, "Bread", "1", "Bread", Recipe.builder().id(4L).build());
        Ingredient cheese1 = new Ingredient(null, "Cheese", "2 fatias", "Cheese", Recipe.builder().id(3L).build());
        Ingredient cheese2 = new Ingredient(null, "Cheese", "2 fatias", "Cheese", Recipe.builder().id(5L).build());

        // Saving users with their phones
        userRepository.saveAll(List.of(
                User.builder().name("José Silva").cpf(84757483471L).birthday(LocalDate.of(1990, 12, 30))
                        .email("samuel@gmail.com").weight("70kg").height("170cm")
                        .foodFrequency(FoodFrequency.THREE_MEALS)
                        .password(encoder.encode("123456789")).sex("male")
                        .phone(new Phone(null, 55, 11, 950312033, null)).build(),
                User.builder().name("Maria Silva").cpf(23465458909L).birthday(LocalDate.of(1960, 9, 12))
                        .email("maria.silva@email.com").weight("120kg").height("163cm")
                        .foodFrequency(FoodFrequency.THREE_MEALS)
                        .password(encoder.encode("mariasilva")).sex("female")
                        .phone(new Phone(null, 55, 76, 908845355, null)).build(),
                User.builder().name("Marcela Inês").cpf(12345678908L)
                        .birthday(LocalDate.of(2002, 4, 28)).email("mines@email.com")
                        .weight("55kg").height("168cm")
                        .foodFrequency(FoodFrequency.FOUR_MEALS)
                        .password(encoder.encode("marcela28042002")).sex("female")
                        .phone(new Phone(null, 55, 33, 992341101, null)).build(),
                User.builder().name("Alexandre Oliveira").cpf(76534565455L)
                        .birthday(LocalDate.of(2010, 7, 13)).email("aoliveira@email.com")
                        .weight("100kg").height("175cm")
                        .foodFrequency(FoodFrequency.FOUR_MEALS)
                        .password(encoder.encode("tommy2020")).sex("male")
                        .phone(new Phone(null, 55, 21, 989238812, null)).build(),
                User.builder().name("Grace Williams").cpf(94836423742L)
                        .birthday(LocalDate.of(2000, 5, 6)).email("graceeee@email.com")
                        .weight("92kg").height("162cm")
                        .foodFrequency(FoodFrequency.THREE_MEALS)
                        .password(encoder.encode("graceeeeeeeee")).sex("female")
                        .phone(new Phone(null, 11, 22, 960618564, null)).build()));

        // Saving recipes with their own ingredients for each user
        recipeRepository.saveAll(List.of(
                Recipe.builder().name("Macarrão").description(
                                "Pegue o macarrão coloca na panela com água e faz molho de tomate")
                        .ingredients(List.of(pasta1, tomato1))
                        .user(User.builder().id(1L).build()).build(),
                Recipe.builder().name("Pão com ovo").description("Frita o ovo e coloca no pao")
                        .ingredients(List.of(bread1, egg1)).user(User.builder().id(5L).build())
                        .build(),
                Recipe.builder().name("Ovo com queijo").description("Mexa o ovo com o queijo e frite")
                        .ingredients(List.of(egg2, cheese1)).user(User.builder().id(4L).build())
                        .build(),
                Recipe.builder().name("Pão com tomate").description("Corta o tomate e coloca no pão")
                        .ingredients(List.of(bread2, tomato2))
                        .user(User.builder().id(2L).build()).build(),
                Recipe.builder().name("Macarrão com queijo").description(
                                "Pegue o macarrão coloca na panela com água e coloca o queijo")
                        .ingredients(List.of(pasta2, cheese2))
                        .user(User.builder().id(3L).build()).build()));

        // Saving diets for each user
        dietRepository.saveAll(List.of(
                Diet.builder().description(
                                "Essa dieta foi planejada para uma pessoa com altura de 1,62m e peso de 92kg, visando suprir suas necessidades nutricionais diárias.")
                        .breakfast("2 ovos mexidos, 50g de pão integral e uma xícara de chá ou café sem açúcar.")
                        .lunch("1 concha de feijão (100g), 2 porções de arroz (100g cada) e 100g de ovos cozidos.")
                        .dinner("1 concha e meia de feijão (150g), 2 porções de arroz (100g cada) e uma salada de vegetais variados.")
                        .user(User.builder().id(5L).build()).build(),
                Diet.builder().description(
                                "Essa dieta foi planejada para uma pessoa com altura de 1,68m e peso de 55kg, visando suprir suas necessidades nutricionais diárias.")
                        .breakfast("3 ovos mexidos, 50g de pão francês e uma xícara de chá ou café, açúcar opcional.")
                        .lunch("1 concha de feijão (100g), 2 porções de arroz (100g cada), 100g de ovos cozidos e 200g de filé de frango.")
                        .afternoonCoffee(
                                "2 ovos mexidos, 50g de pão integral e uma fruta de sua escolha.")
                        .dinner("1 concha e meia de feijão (150g), 2 porções de arroz (100g cada), uma salada de vegetais variados e uma sobremesa de sua preferência.")
                        .user(User.builder().id(3L).build()).build(),
                Diet.builder().description(
                                "Essa dieta foi planejada para uma pessoa com altura de 1,70m e peso de 70kg, visando suprir suas necessidades nutricionais diárias.")
                        .breakfast("2 ovos mexidos, 50g de pão integral e uma xícara de chá ou café sem açúcar.")
                        .lunch("1 concha de feijão (100g), 2 porções de arroz (100g cada) e 100g de ovos cozidos.")
                        .dinner("1 concha e meia de feijão (150g), 2 porções de arroz (100g cada) e uma salada de vegetais variados.")
                        .user(User.builder().id(1L).build()).build(),
                Diet.builder().description(
                                "Essa dieta foi planejada para uma pessoa com altura de 1,63m e peso de 120kg, visando suprir suas necessidades nutricionais diárias.")
                        .breakfast("2 ovos mexidos, 50g de pão integral e uma xícara de chá ou café sem açúcar.")
                        .lunch("1 concha de feijão (100g), 2 porções de arroz (100g cada) e 100g de ovos cozidos.")
                        .dinner("1 concha e meia de feijão (150g), 2 porções de arroz (100g cada) e uma salada de vegetais variados.")
                        .user(User.builder().id(2L).build()).build(),
                Diet.builder().description(
                                "Essa dieta foi planejada para uma pessoa com altura de 1,75m e peso de 100kg, visando suprir suas necessidades nutricionais diárias.")
                        .breakfast("2 ovos mexidos, 50g de pão integral e uma xícara de chá ou café sem açúcar.")
                        .lunch("1 concha de feijão (100g), 2 porções de arroz (100g cada) e 100g de ovos cozidos.")
                        .afternoonCoffee(
                                "2 ovos mexidos, 50g de pão integral e uma fruta de sua escolha.")
                        .dinner("1 concha e meia de feijão (150g), 2 porções de arroz (100g cada) e uma salada de vegetais variados.")
                        .user(User.builder().id(4L).build()).build()));
    }

}
