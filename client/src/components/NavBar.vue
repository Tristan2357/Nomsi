<template>
  <div
    class="p-1 border-b-2 border-trueGray-800 dark:border-trueGray-400 bg-trueGray-100 dark:bg-trueGray-700"
  >
    <div class="left-4 align-center mx-auto">
      <Menu />
    </div>
    <div>
      <h1 class="text-2xl">Nomsi</h1>
    </div>
  </div>
</template>

<script>
import { Vue } from "vue-property-decorator";
export default class NavBar extends Vue {}
</script>

<style scoped></style>
