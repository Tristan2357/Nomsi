<template>
  <div>
    <div
      class="grid p-4 grid-cols-2 sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-6 xl:grid-cols-7 2xl:grid-cols-8 place-content-around gap-1"
    >
      <div
        v-for="recipe in recipes"
        :key="recipe.id"
        class="w-full flex place-content-around self-center my-2"
        v-on:click="navigate(recipe.id)"
      >
        <p class="text-center">{{ recipe.title }}</p>
      </div>
    </div>
    <p v-if="err !== null">{{ err.name }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.message }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.code }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.request }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.response }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.config }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.isAxiosError }}</p>
    <p></p>
    <p v-if="err !== null">{{ err.stack }}</p>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import axios, { AxiosError } from "axios";
import { models } from "shared-types";
import Recipe = models.Recipe;

@Component
export default class RecipeGrid extends Vue {
  @Prop() private msg!: string;
  private recipes: Recipe[] | null = null;
  private err: AxiosError | null = null;

  getRecipes(): void {
    if (!this.recipes) {
      axios
        .get<Recipe[]>("/recipe")
        .then((res) => {
          console.log(res);
          this.recipes = res.data;
        })
        .catch((err: AxiosError) => {
          this.err = err;
          console.log(err);
        });
    }
  }

  navigate(key: string): void {
    this.$router.push(`recipe/preview/${key}`);
  }

  mounted(): void {
    this.getRecipes();
  }
}
</script>

<style scoped></style>
