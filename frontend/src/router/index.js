import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView/LoginView.vue')
  },
  {
    path: '/Main',
    name: 'main',
    component: () => import(/* webpackChunkName: "about" */ '../views/MainView/MainView'),
    children: [
      {
        path: 'MainPage',
        name: 'MainPage',
        component: () => import('@/views/MainView/MainPageView/MainPageView.vue')
      },
      {
        path: 'Inventory',
        name: 'Inventory',
        component: () => import('@/views/MainView/InventoryView/InventoryView.vue')
      },
      {
        path: 'Purchase',
        name: 'Purchase',
        component: () => import('@/views/MainView/PurchaseView/PurchaseView.vue')
      },
      {
        path: 'Order',
        name: 'Order',
        component: () => import('@/views/MainView/OrderView/OrderView.vue')
      },
      {
        path: 'MyAccount',
        name: 'MyAccount',
        component: () => import('@/views/MainView/MyAccountView/MyAccountView.vue')
      },
      {
        path: 'Cost',
        name: 'Cost',
        component: () => import('@/views/MainView/CostView/CostView.vue')
      }
    ]
  }, {
    path: '/play',
    name: 'play',
    component: () => import(/* webpackChunkName: "about" */ '../views/PlayGround.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
