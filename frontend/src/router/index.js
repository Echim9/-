import { createRouter, createWebHashHistory } from 'vue-router'
import store from "@/store";

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/Home')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  let token = store.getters["loginState/getToken"]
  if ((token === 'null' || token === null) && (to.name === 'login' || to.name === 'register')) next()
  else if (token === 'null' || token === null) next({name: 'login'})
  else if (to.name === 'login') next({name: 'home'})
  else next()
})

export default router
