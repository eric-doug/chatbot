import Vue from "vue";
import Router from "vue-router";
import index from "@/view/index"
Vue.use(Router);

/**
 * 解决重复点击菜单会控制台报错bug
 */
 const routerPush = Router.prototype.push
 Router.prototype.push = function push(location) {
   return routerPush.call(this, location).catch(error=> error)
 }

export default new Router({
  mode: "history",
  routes: [
      {
          path: "", // 首页
          name: "index",
          component: index,
      },
  ],
});
