# FoodLife

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
FoodLife is a cooking app that provides tasty dish recipes that can be made from specific ingredients a user has

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Food/Cooking**
- **Mobile: This app will mainly be for mobile use and Users can search for ingredients from the Spoonacular API, add ingredients to their fridge, remove ingredients from the fridge. The added ingredients are saved in Back4App database.

.Fridge Fragment
The fridge has ingredient search autocomplete which shows the first five ingredients based on the user searches.After searching, ingredients are added to the fridge by tapping on the ingredient, and user can swipe left to remove ingredients from the fridge. Hence, the explore fragment displays recipes based on the ingredients in the user's fridge and previously liked recipes.

.RecipeSearchFragment
User can search for recipes in the RecipeSearchFragment, click on recipes to see the overview, and instructions for recipes, and filter recipes based on diet and meal type.

.RecipeExploreFragment
RecipeExploreFragment display recipes based on ingredients stored in fridge(Back4App database) and previously liked recipes(room database)

.RecipeFavoriteFragment
RecipeFavoriteFragment display favorite recipes which was stored in room database.

.ProfileFragment
ProfileFragment shows users profile, which contains users imported profile image from facebook, and logout .**
- **Story: This answers the question of "what can I cook with the ingredients I have?" Users will be able to know the dish to cook with their available ingredients.The app takes in user's ingredients (what they have in their fridge) and provides recipe suggestions from them.**
- **Market: A lot of people have something at home in their fridge, but don't know what to cook**
- **Habit:This app will be addictive because a lot of people want to know what food they can cook with the ingredients they have.**
- **Scope: The app most basic feature is that it allows user to know what to cook and the app recommends recipes based on user ingredients, allow user to view details about recipe and save their favorite recipes**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* [x] User can see custom splash screen
* [x] User can sign up for a new account
* [x] User can log into their account
* [x] User can log out of their account
* [x] User can search, add and remove ingredients from their Fridge/Ingredient List
* [x] User available ingredients in the fridge can be used to get suggested recipes
* [x] User can search for recipes based on ingredients
* [x] User can filter recipes based on meal type and diet type
* [x] RecipeDetails displays overview, instructions and ingredients for recipe
* [x] User can double tap to like a recipe in the Overview Fragment of the detail activity
* [x] User can see dish recipe that can be made
* [x] User can see steps on how to prepare the Recipe
* [x] User can see Recommended Recipes for five categories. The first category is based on ingredients in their fridge, while the remaining categories are cuisines based on previously liked recipes.
* [x] Ingredients search autocompletes
* [x] The app integrates with spoonacular API
* [x] The app uses gesture double tap to like a recipe
* [x] The app uses shimmer animation in the explore fragment and slide-in animation in the Search fragment

**Optional Nice-to-have Stories(Stretch)**
* [x] User can save favorite recipes
* [x] Add Facebook SDK for login
* [x] Import user facebook profile image to user's profile 
* [x] Added shimmer effect loading state for the explore page
* [x] API call information is cached and can be seen offline
* [] RecipeFragment has infinite scroll



Complex Features:

1.I created a fridge, where users can search for ingredients from the API with ingredient search autocomplete. This is the API endpoint I used for the ingredients autocomplete "https://api.spoonacular.com/food/ingredients/autocomplete?apiKey=%s" and "https://api.spoonacular.com/recipes/findByIngredients/{id}ingredientWidget.json?apiKey=%s" for the ingredient search from the API. Then, the user can click on any of the suggestions, which is added to the Fridge Fragment, user can also swipe to delete ingredients. The ingredients are then saved to BAck4App database and used it to display recommended recipes from the API with the **fetchRecipesByIngredients()** method in the RecipeExploreFragment.

2. I used Nested recyclerview, so I had a recyclerview with horizontal scrolling in the main recyclerview that supports vertical scrolling because I displayed recommendations for five categories. Then, I saved users previously liked recipes to room database and I created a method called **querySimilarCuisinesRecipe()**, which is the method that gets the favorite recipes saved in the databse and calls similar recipes from the API. Also,  I filtered the recipes based on type,cuisine and to include certain ingredients, then I displayed three different cuisines which are Italian, American, and Chinese cuisines, and the breakfast category from the meal type.
3. Added a category called recipe of the day : This displays meals that meet the target calories of 2000. I implemented this by getting the Calorievalue for each recipe ID, then from each food saved by the user in the favorite, I got the calorie for each ID, then I checked for recipes that meet the target calories of 2000, and that was displayed as the recipe of the day.


**Mobile (Android)
Go beyond CodePath:**

Difficult/ Ambiguous Technical Problems: 
Your app provides multiple opportunities for you to overcome difficult/ambiguous technical problems (more below)
SDK & Database Integration
* [x] Your app interacts with a database (e.g. Parse) - I used Back4App & Room database
* [x] Your app integrates with at least one SDK (e.g. Google Maps SDK, Facebook SDK) or API (that you didn’t learn about in CodePath) – free SDKs and APIs only -  I used Spoonacular API & Facebook SDK

User Authentication
* [x] You can log in/log out of your app as a user
* [x] You can sign up with a new user profile

Visuals & Interactions
Your app has multiple views
[mobile only] 
* [x] Your app uses at least one gesture (e.g. double tap to like, e.g. pinch to scale) -  I used double tap to like in the detail activity, and swipe to delete ingredients in the Fridge fragment.
* [x] Your app incorporates at least one external library to add visual polish -  I added FloatingActionButton and Facebook shimmer effect as a loading state.
* [x] Your app uses at least one animation (e.g. fade in/out, e.g. animating a view growing and shrinking)-  I added slide-in animation in the Recipe/Search Fragment


**File Architecture:**
  . I arranged my files into different packages, which are activities, adapters, clients, favourite, fragments and models. 
  
  activities:
    DetailActivity
    LoginActivity
    MainActivity
    SignupActivity
    SplashscreenActivity. 
    
    
  fragments:
  
    FridgeFragment
    RecipeSearchFragment
    RecipeExploreFragment
    FavoriteRecipeFragment
    ProfileFragment
    detail activity  have RecipeOverviewFragment, RecipeInstructionsFragment, IngredientsFragment. 
    
    
  adapters:
  
    RecipeAdapter: which is the main adapter for the recipes
    RecipeExploreAdapter: this is the adapter for the explore page
    SuggestionsAdapter:  this is the adapter for the ingredients autocomplete in the FridgeFragment,       SelectedIngredientsAdapter: is the adapter for the selected ingredients in the Fridge Fragment,
    FavouriteRecipeAdapter: is the adapter for the FavouriteFragment
    PagerAdapter: is for the ViewPager, and IngredientsAdapter is for the ingredientsFragment in the       detail activity.
    
    
  clients: 
  
    The clients file are FoodClient, where all my API calls were made, NetworkCallback, an interface       for NetworkCallback,  ParseApplication for Back4App database. 
    
  favourite :
  
    The favourite package contains Database class, Data entities, and Data access objects for the           favorite recipes which was saved with room database.
    
  models:
  
    Recipe :The Recipe model class is for the title, image, and id of Recipe
    RecipeExtended : the RecipeExtended model contains more information about Recipes, hence it is for     the detail activity of the Recipes
    Ingredient : The Ingredient model class was used to save ingredients  to the Back4App database.
    IngredientDetails 
    IngredientSearchSuggestion: IngredientSearchSuggestion is the model class for the Ingredient Search     in the FridgeFragment.
    Search: Search model class was used to save search query to the Back4App database.
    User:  The User the model class for the User.

### 2. Screen Functionality

* Fridge Fragment
   * I created a Fridge to allow User to search for ingredients from api. I queried Ingredients for the specific user, then I setup the recyclerview to allow user to search for ingredients from api, then I used an ItemTouchHelper to attach the selected ingredients to the Recyclerview. I created a method named onSuggestionClicked() to attach the ingredients clicked on and attach it to the recyclerview. And, I used the saveIngredient() to saved ingredients to the database and I called it in the onSuggestionClicked() function. Also, I created the deleteIngredient() to delete ingredients from the database and I called the method in the onSwiped() function.
   * User can search for, add ingredients to the pantry fragment
   * User can remove ingredients from the pantry fragment
* Recipe Fragment
   * I created the getRecipes() method that allow users to search for Recipes from the API, and a floatingActionButton to allow users to filter recipes based on diet and meal type, and I saved user searchquery to the database.
   * User can filter recipe based on diet and meal type
   * User can click on Recipe to see details about recipe
* Explore Fragment
   * I displayed recommended recipes based on ingredients available in the users fridge. I saved users previously liked recipes to room database. Then, I created a method called **querySimilarCuisinesRecipe()**, then I got the previouly saved favorite recipes, then I filtered the recipes based on type and three different cuisines which are Italian, American, and Chinese cuisines. For the breakfast category, I did the same, but I created the **queryPreviouslyLikedRecipe()** for it and I displayed recommended breakfast recipes.
   * User can see recommended recipes based on ingredients in their fridge
   * User can see recommended recipes by cuisine based on their preference
* Favorite Fragment
   * I saved user favorite recipes to room database and displayed it in this fragment
   * User can see saved recipes 
* Profile
   * User can see their profile

### 2. Screen Archetypes

* Login/register page
   * User can sign up for a new account
   * User can log into their account
* Fridge Fragment
   * User can search for ingredients from api
   * User can add ingredients to the pantry fragment
   * User can remove ingredients from the pantry fragment
* Recipe Fragment
   * User can search for recipes by ingredient and recipe name
   * User can filter recipe based on diet and meal type
   * User can click on Recipe to see details about recipe
* Explore Fragment
   * User can see recommended recipes based on ingredients in their fridge
   * User can see recommended recipes by cuisine based on their preference
* Favorite Fragment
   * User can see saved recipes 
* Profile
   * User can logout of their profile, I used Facebook Graph API to fetch the users profile image. i got the currentAccessToken when the user login and made a graphrequest if the accesstoken is not null, and I set the profile image to the profile fragment based on the user id.
   * User can see their profile
### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Fridge Screen 
* Recipe Screen
* Explore Screen
* Favorite Screen
* Profile Screen

**Flow Navigation** (Screen to Screen)

* Login  Screen -> Main Screen
*Fridge Screen
* Search Screen 
* Explore Screen
* Favorite Screen    
* Profile Screen

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="Sketch.jpg" width=600>

### [] Tools that will be used:
* Back4App
* Spoonacular Api


### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models

### User
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user|
   | username      | STRING   | unique username for the user |
   | password      | String   | password for user login |
   | email         | String   | email for user login |
   
   
### Ingredient
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | user          |Pointer to User   |user account|
   | ingredientName| STRING  | name for pre-defined ingredients |
    
### getRecipeFromAPI
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectid      | STRING   | unique identifier for the recipe|
   | recipeid      | Integer  | get id in spoonacular database  |
   | title         | STRING   | title of recipe|
   | imageUrl      | String   | url to recipe database |
   | Summary       | STRING   | description of recipe|
   | createdAt     | DateTime | date recipe was created |
   | updatedAt     | DateTime | date when recipe was last updated|
   
### Networking
#### List of network requests by screen
 - Fridge Screen
  - (Read/GET) Query ingredients to save in display in Fridge
    ParseQuery<Ingredient> query = ParseQuery.getQuery(Ingredient.class);
      query.include(Ingredient.USER_KEY);
      query.setLimit(INGREDIENTS_NUMBER);
      query.addDescendingOrder("createdAt");
      query.findInBackground(new FindCallback<Ingredient>());
      - (Create/POST) Create a new user
   - Create Post Screen
      - (Create/POST) Save new ingredients to the database
          Ingredient ingredient = new Ingredient();
          ingredient.setName(ingredientName);
          ingredient.setUser(ParseUser.getCurrentUser());
          ingredient.saveInBackground(new SaveCallback());
   - Recipe Screen
      - (Read/GET) Query logged in user object
           FoodClient client = new FoodClient();
           client.getIngredients(selectedDiet, selectedMeal, searchValue, new NetworkCallback<List<Food>>()
    
#### [OPTIONAL:] Existing API Endpoints
##### Spoonacular
- Base URL - (https://spoonacular.com/recipe-api/docs)
   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | recipes/findByIngredients/{id}| gets recipes by ingredients
    `GET`    | /recipes/{id}/information | gets recipe details
    `GET`    |recipes/complexSearch| gets all recipes by searching
    `GET`    | food/ingredients/autocomplete? | gets autocomplete for ingredients
    
