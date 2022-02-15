<template>
  <div id="home" class="h-full">
    <RecipeSearch class="mt-6 w-full" />
    <RecipeGrid />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import RecipeSearch from "@/components/RecipeSearch.vue";
import RecipeGrid from "@/components/RecipeGrid.vue";
@Component({
  components: {
    RecipeSearch,
    RecipeGrid,
  },
})
export default class Home extends Vue {
  private assets: string = process.env.VUE_APP_ASSETS as string;
}
</script>
