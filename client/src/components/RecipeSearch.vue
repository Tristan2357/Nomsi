<template>
  <div id="recipeSearch w-full">
    <input
      type="search"
      class="rounded-lg dark:bg-white bg-trueGray-900 mx-4 w-5/6 border"
      v-on:keyup.enter="populate"
    />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import axios from "axios";

@Component
export default class RecipeSearch extends Vue {
  populate(): void {
    axios
      .get("/recipe/generate")
      .then((res) => {
        console.log(res);
        // TODO
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
</script>

<style scoped></style>
