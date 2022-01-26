<template>
  <div
    class="bg-trueGray-200 dark:bg-trueGray-800 text-center h-screen w-screen absolute inset-0"
  >
    <NavBar />
    <router-view />
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
