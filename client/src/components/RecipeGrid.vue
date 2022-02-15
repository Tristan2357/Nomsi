<template>
  <div>
    <div>
      <button
        aria-label="create"
        class="p-0 w-12 h-12 bg-paradise rounded-full hover:bg-paradise-light focus-within:bg-paradise-light active:shadow-lg mouse shadow transition ease-in duration-200 focus:outline-none fixed right-4 bottom-4"
        v-on:click="$router.push('/recipe/create')"
      >
        <svg class="w-6 h-6 inline-block" viewBox="0 0 20 20">
          <path
            d="M16,10c0,0.553-0.048,1-0.601,1H11v4.399C11,15.951,10.553,16,10,16c-0.553,0-1-0.049-1-0.601V11H4.601
                                    C4.049,11,4,10.553,4,10c0-0.553,0.049-1,0.601-1H9V4.601C9,4.048,9.447,4,10,4c0.553,0,1,0.048,1,0.601V9h4.399
                                    C15.952,9,16,9.447,16,10z"
            fill="#FFFFFF"
          />
        </svg>
      </button>
    </div>
    <div
      class="grid p-4 grid-cols-2 sm:grid-cols-4 md:grid-cols-5 place-content-around gap-1"
    >
      <div
        v-for="recipe in recipes"
        :key="recipe.id"
        class="w-full flex place-content-around self-center my-2 active:shadow-lg mouse transition ease-in duration-200 focus:outline-none"
        v-on:click="navigateToPreview(recipe.id)"
      >
        <p class="text-center">{{ recipe.title }}</p>
      </div>
    </div>
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

  navigateToPreview(key: string): void {
    this.$router.push(`recipe/preview/${key}`);
  }

  mounted(): void {
    this.getRecipes();
  }
}
</script>

<style scoped></style>
