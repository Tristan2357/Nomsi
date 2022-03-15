<template>
  <div>
    <div>
      <mcw-fab
        class="fixed right-4 bottom-4"
        icon="add"
        v-on:click="$router.push('/recipe/create')"
      >
      </mcw-fab>
    </div>
    <div>
      <div
        v-for="recipe in recipes"
        :key="recipe.id"
        class="w-full flex place-content-around self-center my-2 active:shadow-lg mouse transition ease-in duration-200 focus:outline-none"
        @click="navigateToPreview(recipe.id)"
      >
        <p class="text-center">{{ recipe.title }}</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import axios from "axios";
import type { Ref } from "vue";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import type { Recipe } from "../models/recipe";

let recipes: Ref<Recipe[]> = ref([]);

const router = useRouter();

function getRecipes(): void {
  if (recipes.value.length === 0) {
    axios.get<Recipe[]>("/recipe").then((res) => {
      recipes.value = res.data;
    });
  }
}

function navigateToPreview(key: string): void {
  router.push(`/recipe/preview/${key}`);
}

onMounted(() => {
  getRecipes();
});
</script>

<style scoped></style>
