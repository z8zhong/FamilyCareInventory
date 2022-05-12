import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/styles/index.scss'
import 'element-plus/dist/index.css'

// eslint-disable-next-line
const app = createApp(App).use(store).use(router).mount('#app')
