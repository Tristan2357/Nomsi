import Vue from 'vue'
import App from './Main.vue'
import router from './router'
import axios from "axios";
import './assets/tailwind.css'

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_API


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
