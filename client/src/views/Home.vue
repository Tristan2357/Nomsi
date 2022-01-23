<template>
  <div id="home">
    <RecipeSearch class="mt-6 w-full"/>
    <RecipeGrid/>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import RecipeSearch from '@/components/RecipeSearch.vue'; // @ is an alias to /src
import RecipeGrid from '@/components/RecipeGrid.vue';
@Component({
  components: {
    RecipeSearch,
    RecipeGrid
  },
})
export default class Home extends Vue {
   private assets: string = process.env.VUE_APP_ASSETS as string;
}
</script>
