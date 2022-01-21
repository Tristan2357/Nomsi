<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p v-if="this.user !== null">
      This example was created by <b>{{ this.user.first }} {{ this.user.last }}</b> by the age of
      <b>{{ this.user.age }}</b>.
    </p>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';
import axios from "axios";
import {models} from "shared-types";
import User = models.User;

@Component
export default class HelloWorld extends Vue {
  @Prop() private msg!: string;
  private user: User | null = null;

  getUser() {
    if (!this.user) {
      axios.get<User>("/user").then(res => {
        this.user = res.data
      }).catch(err => {
        console.log(err)
      })
    }
  }

  mounted() {
    this.getUser()
  }
}
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
