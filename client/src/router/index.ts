import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/Home.vue";
import RecipePreview from "@/views/RecipePreview.vue";
import RecipeCreation from "@/views/RecipeCreation.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/recipe/preview/:id",
    name: "Recipe",
    component: RecipePreview,
  },
  {
    path: "/recipe/create",
    name: "Create-Recipe",
    component: RecipeCreation,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
