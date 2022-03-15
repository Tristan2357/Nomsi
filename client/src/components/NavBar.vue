<template>
  <div
    class="h-16 p-1 border-b-2 border-neutral-800 dark:border-neutral-400 bg-neutral-200 dark:bg-neutral-700 sticky flex-row flex justify-between items-center"
  >
    <div>
      <SideMenu/>
    </div>
    <div v-on:click="$router.push('/').catch(() => {})">
      <h1 class="text-2xl text-black dark:text-white">Nomsi</h1>
    </div>
    <div/>
  </div>
</template>

<script lang="ts" setup>
import SideMenu from "./SideMenu.vue";</script>
<style scoped></style>
