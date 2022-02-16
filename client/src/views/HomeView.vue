<template>
  <div id="home" class="h-full">
    <RecipeSearch class="mt-6 w-full" />
    <RecipeGrid />
  </div>
</template>

<script lang="ts" setup>
import RecipeGrid from "../components/RecipeGrid.vue";
import RecipeSearch from "../components/RecipeSearch.vue";
</script>
