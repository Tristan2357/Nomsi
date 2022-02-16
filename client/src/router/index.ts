import { createRouter, createWebHistory } from "vue-router";
import RecipePreview from "../views/RecipePreview.vue";
import HomeView from "../views/HomeView.vue";
import RecipeCreation from "../views/RecipeCreation.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "HomeView.vue",
      component: HomeView,
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
  ],
});

export default router;
