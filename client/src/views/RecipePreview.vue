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
    <ol>
      <li
        v-for="(step, index) in steps"
        :key="step.description"
        class="text-left pl-16"
      >
        <p>
          -
          {{ step.number !== -1 ? step.number : index + 1 }}
          <b>{{ step.description }}</b>
        </p>
      </li>
    </ol>
  </div>
</template>

<script lang="ts" setup>
import axios from "axios";
import type { Ingredient } from "shared-types";
import { Recipe, Step } from "shared-types";
import type { Ref } from "vue";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();

const recipe: Ref<Recipe> = ref(new Recipe("", [], [], ""));
const steps: Ref<Step[]> = ref([]);
const ingredients: Ref<Ingredient[]> = ref([]);

onMounted(() => {
  axios
    .get<Recipe>(`/recipe/${route.params.id}`)
    .then((res) => {
      recipe.value = res.data;
      ingredients.value = recipe.value?.ingredients;
      steps.value = recipe.value?.steps;
    })
    .catch((err) => {
      console.log(err);
    });
});
</script>
