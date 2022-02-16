<template>
  <div id="recipeSearch w-full">
    <input
      class="rounded-lg dark:bg-white bg-trueGray-900 mx-4 w-5/6 border"
      type="search"
      v-bind:value="searchTerm"
      v-on:keydown.enter="lookup(searchTerm)"
    />
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import type { Ref } from "vue";

const searchTerm: Ref = ref("");

function lookup(searchTerm: string) {
  // TODO implement search
  return "Not implemented";
}
</script>

<style scoped></style>
