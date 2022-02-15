<template>
  <div
    class="h-16 p-1 border-b-2 border-trueGray-800 dark:border-trueGray-400 bg-trueGray-200 dark:bg-trueGray-700 sticky flex-row flex justify-between items-center"
  >
    <div>
      <Menu />
    </div>
    <div v-on:click="$router.push('/').catch(() => {})">
      <h1 class="text-2xl">Nomsi</h1>
    </div>
    <div />
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import Menu from "./Menu";

@Component({
  components: { Menu },
})
export default class NavBar extends Vue {}
</script>

<style scoped></style>
