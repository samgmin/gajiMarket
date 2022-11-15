import Vue from 'vue'
import VueRouter from 'vue-router'
import BoardList from "@/views/BoardList";
import BoardCreate from "@/views/BoardCreate";
import BoardRead from "@/views/BoardRead";
import BoardUpdate from "@/views/BoardUpdate";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'list',
    component: BoardList,
  },
  {
    path: '/create',
    name: 'create',
    component: BoardCreate,
  },
  {
    path: '/read',
    name: 'read',
    component: BoardRead,
  },
  {
    path: '/update/:bno',
    name: 'update',
    component: BoardUpdate,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
