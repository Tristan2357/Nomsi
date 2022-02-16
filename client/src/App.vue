<template>
  <NavBar />
  <div
    class="bg-trueGray-100 dark:bg-trueGray-800 text-center h-screen w-screen absolute inset-0 flex flex-col"
  >
    <div class="flex w-screen justify-around h-full">
      <RouterView class="max-w-4xl lg:shadow-lg dark:shadow-trueGray-900" />
    </div>
  </div>
</template>

<style></style>

<script lang="ts" setup>
import { RouterView } from "vue-router";
import NavBar from "./components/NavBar.vue";
</script>
