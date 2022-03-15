<template>
  <div class="mt-16">
    <!--  Header Image  -->
    <div class="w-full">
      <!--   TODO   -->
    </div>
    <!--  Title  -->
    <div class="flex flex-row justify-between p-4">
      <mcw-textfield v-model="newRecipe.title" :value="newRecipe.title"
                     label="Title" @input="event => newRecipe.title = event.target.value"/>
      <mcw-button @click="createRecipe">Save</mcw-button>
    </div>
    <!--  Ingredients  -->
    <div>
      <div>
        <div v-for="ingredient of newRecipe.ingredients" class="w-full">
          <div class="flex flex-row w-full justify-evenly gap-4">
            <mcw-textfield v-model="ingredient.name" label="Name of Ingredient"></mcw-textfield>
            <mcw-textfield v-model="ingredient.amount" label="Amount of Ingredient" type="number"></mcw-textfield>
            <mcw-select v-model="ingredient.unit" label="Unit of Amount of Ingredient">
              <mcw-list-item v-for="unit of amountUnitAsArray()" :key="unit" role="option">{{ unit }}</mcw-list-item>
            </mcw-select>
          </div>
        </div>
        <div>
          <mcw-button class="!text-white" icon="add" @click="addEmptyIngredient">
            Add Ingredient
          </mcw-button>
        </div>
      </div>
    </div>
    <!--  Steps  -->
  </div>
</template>

<script lang="ts" setup>
import type { Ref } from "vue";
import { ref } from "vue";
import { AmountUnit, amountUnitAsArray, Ingredient } from "../models/ingredient";
import { Recipe } from "../models/recipe";

const newRecipe: Ref<Recipe> = ref(new Recipe("", [], [], ""));

function addEmptyIngredient() {
  newRecipe.value.ingredients.push(new Ingredient("", 0, AmountUnit.COUNT));
}

function createRecipe() {
  console.log(newRecipe.value);
}

</script>
