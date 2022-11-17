import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "read",
        name: "boardread",
        component: () => import("@/components/board/BoardRead"),
      },
      {
        path: "create",
        name: "boardcreate",
        component: () => import("@/components/board/BoardCreate"),
      },
      {
        path: "update",
        name: "boardupdate",
        component: () => import("@/components/board/BoardUpdate"),
      },
    ],
  },
  {
    path: "/apt",
    name: "apt",
    component: () => import("@/views/AppApt"),
    redirect: "/apt/search",
    children: [
      {
        path: "search",
        name: "aptsearch",
        component: () => import("@/components/apt/AptSearch"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/AppUser"),
    children: [
      {
        path: "login",
        name: "userlogin",
        component: () => import("@/components/user/UserLogin"),
      },
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
