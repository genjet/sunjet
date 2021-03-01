// import Vue from 'vue'
import store from "@/store";

export default () => {
  // Vue.prototype.$I18N = function (...text: any){
  //   let message = "";
  //   text.forEach((item: any) => {
  //     message += this.$t(item);
  //   });
  //   return message;
  // }

 // store.dispatch("management/getDeps");
  store.dispatch("app/getAllOptions");
}
