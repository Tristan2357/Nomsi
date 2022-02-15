<template>
  <div
    class="bg-trueGray-100 dark:bg-trueGray-800 text-center h-screen w-screen absolute inset-0 flex flex-col"
  >
    <NavBar />
    <div class="flex w-screen justify-around h-full">
      <router-view class="max-w-4xl lg:shadow-lg dark:shadow-trueGray-900" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import NavBar from "@/components/NavBar.vue";

@Component({
  components: {
    NavBar,
  },
})
export default class Home extends Vue {}
</script>
