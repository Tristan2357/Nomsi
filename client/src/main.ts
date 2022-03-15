import axios from "axios";
import { createApp } from "vue";
import {
  button,
  fab,
  list,
  menu,
  select,
  textfield,
} from "vue-material-adapter";
import App from "./App.vue";

import "./assets/index.scss";
import router from "./router";

axios.defaults.baseURL = import.meta.env.VITE_API;

const app = createApp(App);

app.use(router);

app.use(button);
app.use(fab);
app.use(list);
app.use(menu);
app.use(select);
app.use(textfield);

app.mount("#app");
