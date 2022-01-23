<template>
  <div id="recipeSearch w-full">
    <input type="search" class="rounded-lg bg-transparent mx-4 w-5/6" v-on:keyup.enter="populate">
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import axios from "axios";

@Component
export default class RecipeSearch extends Vue {
  populate() {
      axios.get("/recipe/generate").then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
  }
}
</script>

<style scoped>
</style>
