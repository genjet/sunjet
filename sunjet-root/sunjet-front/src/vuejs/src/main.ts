import Vue from "vue";
import EventBus from "vue-bus-ts";

//import Cookies from "js-cookie";
// import axios from "axios";
// import vueAxios from "vue-axios";

import "reset-css";
import "element-ui/lib/theme-chalk/index.css";

import lang from "./lang";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import initialize from '@/inits';
import "@/styles/index.scss"; // global css
import "@/icons";
import "@/permission"; // permission control

Vue.config.productionTip = false;
Vue.use(EventBus);
// Vue.prototype.$I18N =

initialize();

new Vue({
  router,
  store,
  i18n: lang.i18n,
  render: h => h(App)
}).$mount("#app");
