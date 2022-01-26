<template>
  <div id="recipe">
    <h2 class="mt-4">{{ recipe.title }}</h2>
    <ul>
      <li
        v-for="ingredient in ingredients"
        :key="ingredient.name"
        class="text-left pl-16"
      >
        <p>
          - {{ ingredient.name }} <b>{{ ingredient.amount }}</b>
        </p>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import axios from "axios";
import { models } from "shared-types";
import Recipe = models.Recipe;
import Ingredient = models.Ingredient;

@Component({
  components: {},
})
export default class RecipePreview extends Vue {
  private recipe: Recipe = new Recipe("", [], [], "");
  private ingredients: Ingredient[] | null = null;

  mounted(): void {
    axios
      .get<Recipe>(`/recipe/${this.$route.params.id}`)
      .then((res) => {
        console.log(res);
        this.recipe = res.data;
        this.ingredients = this.recipe?.ingredients;
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
</script>
