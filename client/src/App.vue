<template>
  <div class="flex flex-col h-screen">
    <header>
      <NavBar/>
    </header>
    <main
      class="bg-neutral-100 dark:bg-neutral-800 text-center inset-0 flex flex-col flex-grow"
    >
      <div class="flex flex-col justify-around">
        <RouterView
          class="max-w-4xl lg:shadow-lg dark:shadow-neutral-900 h-full"
        />
      </div>
    </main>
  </div>
</template>
0
<style></style>

<script lang="ts" setup>
import { RouterView } from "vue-router";
import NavBar from "./components/NavBar.vue";</script>
