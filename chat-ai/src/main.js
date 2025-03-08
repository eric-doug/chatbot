import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from '@/router/'

Vue.prototype.$http= axios
new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
console.log("与其蜗牛般学习开发，不如开启自主学习,代码江湖官网地址:http://www.softworld.vip")