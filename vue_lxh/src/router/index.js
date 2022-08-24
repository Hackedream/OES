import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  //管理员
  {
    path: '/admin',
    name: 'Admin',
    component: Layout,
    redirect: "/admin/userManagement",//重定向自动跳转
    children: [{
      path: 'userManagement',
      name: 'UserManagement',
      component: () => import ("@/views/admin/UserManagement")
    },
    {
      path: 'lessonManagement',
      name: 'LessonManagement',
      component: () => import("@/views/admin/LessonManagement")
    },
    {
      path: 'announceManagement',
      name: 'AnnounceManagement',
      component: () => import("@/views/admin/AnnounceManagement")
    },
    {
      path: 'lessonCheckout',
      name: 'LessonCheckout',
      component: () => import("@/views/admin/LessonCheckout")
    },
    {
      path: 'ldetails',
      name: 'Adetails',
      component: () => import("@/views/lesson/Adetails")
    },
  ]
  },
  {
    path:'/login',
    name: 'Login',
    component: () =>import("@/views/user/Login"),
  },
  //用户
  {
    path: '/user',
    name: 'User',
    redirect:"/home",
    component: () => import("@/components/Header"),
    children: [{
      path: 'topup',
      name: 'Topup',
      component: () =>import("@/views/user/Topup")
    },
    {
      path:'withdraw',
      name:'Withdraw',
      component: ()=>import("@/views/user/Withdraw"),
    },
    {
      path: 'order',
      name: 'Order',
      component: () =>import("@/views/order/Order")
    },
    {
      path: 'myCourse',
      name: 'MyCourse',
      component: () =>import("@/views/user/MyCourse"),
    },
  ]
  },
  {
    path:'/home',
    name:'HomeView',
    component: () =>import('@/views/HomeView')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
