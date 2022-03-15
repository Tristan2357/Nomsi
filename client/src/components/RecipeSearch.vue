<template>
  <div id="recipeSearch w-full">
    <input
      class="rounded-lg dark:!bg-white !bg-neutral-900 mx-4 border"
      icon="search"
    />
  </div>
</template>

<script lang="ts" setup>
import type { Ref } from "vue";
import { ref } from "vue";

const searchTerm: Ref = ref("");

function lookup(searchTerm: string) {
  // TODO implement search
  return "Not implemented";
}
</script>

<style scoped></style>
