import Vue from "vue";
import Router, { RouteConfig } from "vue-router";
import Layout from "@/layout/index.vue";

Vue.use(Router);

const constantRoutes: Array<RouteConfig> = [
  {
    path: "/login",
    name:"login",
    component: () => import(/* webpackChunkName: "login" */ "@/views/login/index.vue"),
    meta: { title: "login",  hidden: true }
  },
  {
    path: "/a",
    name: "Home",
    component: () => import(/* webpackChunkName: "home" */ "@/views/Home.vue")
  },
  {
    path: "/todo",
    component: Layout,
    name: "Todo",
    children: [
      {
        path: "todo",
        name: "todo",
        component: () => import(/* webpackChunkName: "todo" */ "@/views/todo/index.vue"),
        meta: { title: "__router.todo", icon: "eye" }
      }
    ]
  },
  {
    path: "/chat",
    component: Layout,
    name: "chat",
    children: [
      {
        path: "chat",
        name: "chat",
        component: () => import(/* webpackChunkName: "chat" */ "@/views/chat/chat.vue"),
        meta: {
          title: "__router.chat",
          icon: "my-sysmenu"
          // noCache: true,
          // resources: "chat",
        }
      }
    ]
  },
  {
    path: "/404",
    component: () => import(/* webpackChunkName: "404" */ "@/views/error-page/404.vue"),
    meta: { hidden: true }
  },
  // {
  //   path: '/401',
  //   component: () => import(/* webpackChunkName: "401" */ '@/views/error-page/401.vue'),
  //   meta: { hidden: true }
  // },
  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        component: () => import(/* webpackChunkName: "dashboard" */ "@/views/dashboard/index.vue"),
        meta: { title: "__router.dashboard", icon: "dashboard" }
      }
    ]
  },

  // {
  //   path: "/example",
  //   component: Layout,
  //   redirect: "/example/table",
  //   name: "Example",
  //   meta: { title: "Example", icon: "example" },
  //   children: [
  //     {
  //       path: "table",
  //       name: "Table",
  //       component: () => import("@/views/table/index.vue"),
  //       meta: { title: "Table", icon: "table" },
  //     },
  //     {
  //       path: "tree",
  //       name: "Tree",
  //       component: () => import("@/views/tree/index.vue"),
  //       meta: { title: "Tree", icon: "tree" },
  //     },
  //   ],
  // },
  {
    path: "/nested",
    component: Layout,
    redirect: "/nested/menu1",
    name: "Nested",
    meta: {
      title: "__router.Nested",
      icon: "nested"
    },
    children: [
      {
        path: "menu1",
        name: "Menu1",
        component: () => import(/* webpackChunkName: "menu1" */ "@/views/nested/menu1/index.vue"), // Parent router-view
        meta: { title: "__router.Menu1" },
        children: [
          {
            path: "user",
            name: "user",
            component: () => import(/* webpackChunkName: "user" */ "@/views/nested/menu1/user/index.vue"),
            meta: { title: "__router.user" }
          },
          {
            path: "role",
            name: "role",
            component: () => import(/* webpackChunkName: "role" */ "@/views/nested/menu1/role/index.vue"),
            meta: { title: "__router.role" }
          },
          {
            path: "menu1-2",
            name: "Menu1-2",
            component: () => import(/* webpackChunkName: "menu1-2" */ "@/views/nested/menu1/menu1-2/index.vue"),
            meta: { title: "__router.Menu12" },
            children: [
              {
                path: "menu1-2-1",
                name: "Menu1-2-1",
                component: () => import(/* webpackChunkName: "menu2-1" */ "@/views/nested/menu1/menu1-2/menu1-2-1/index.vue"),
                meta: { title: "__router.Menu121" }
              },
              {
                path: "menu1-2-2",
                name: "Menu1-2-2",
                component: () => import(/* webpackChunkName: "menu2-2" */ "@/views/nested/menu1/menu1-2/menu1-2-2/index.vue"),
                meta: { title: "__router.Menu122" }
              }
            ]
          },
          {
            path: "menu1-3",
            name: "Menu1-3",
            component: () => import(/* webpackChunkName: "menu1-3" */ "@/views/nested/menu1/menu1-3/index.vue"),
            meta: { title: "__router.Menu13" }
          },
          {
            path: "leave",
            name: "leave",
            component: () => import(/* webpackChunkName: "leave" */ "@/views/nested/menu1/leave/index.vue"),
            meta: { title: "__router.leave" }
          }
        ]
      },
      {
        path: "menu2",
        component: () => import(/* webpackChunkName: "menu2" */ "@/views/nested/menu2/index.vue"),
        meta: { title: "__router.Menu2" }
      }
    ]
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "*",
    redirect: "/404"
    // meta: { hidden: true },
  }
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    //mode: "hash",
    scrollBehavior: (to, from, savedPosition) => {
      if (savedPosition) {
        return savedPosition;
      }
      return { x: 0, y: 0 };
    },
    routes: constantRoutes
  });

let router = createRouter();

export function resetRouter() {
  const newRouter = createRouter();
  router = newRouter; // reset router
  // (router as any).matcher = (newRouter as any).matcher
}

export default router;
