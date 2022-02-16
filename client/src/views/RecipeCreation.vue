<template>
  <div>
    <form id="demo">
      <!-- text -->
      <p>
        <input v-model="msg" type="text" />
        {{ msg }}
      </p>
      <!-- checkbox -->
      <p>
        <input v-model="checked" type="checkbox" />
        {{ checked ? "yes" : "no" }}
      </p>
      <!-- radio buttons -->
      <p>
        <input v-model="picked" name="picked" type="radio" value="one" />
        <input v-model="picked" name="picked" type="radio" value="two" />
        {{ picked }}
      </p>
      <!-- select -->
      <p>
        <select v-model="selected">
          <option>one</option>
          <option>two</option>
        </select>
        {{ selected }}
      </p>
      <!-- multiple select -->
      <p>
        <select v-model="multiSelect" multiple>
          <option>one</option>
          <option>two</option>
          <option>three</option>
        </select>
        {{ multiSelect }}
      </p>
    </form>
  </div>
</template>

<script lang="ts" setup>
import type { Ref } from "vue";
import { ref } from "vue";

const msg: Ref<string> = ref("");
const checked: Ref<boolean> = ref(false);
const picked: Ref<string> = ref("");
const selected: Ref<string> = ref("");
const multiSelect: Ref<string[]> = ref([]);
</script>
