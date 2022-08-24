import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

import ViewUIPlus from 'view-ui-plus'

import '@/assets/css/global.css'
import 'view-ui-plus/dist/styles/viewuiplus.css'

import { createVuestic } from 'vuestic-ui'
import 'vuestic-ui/css'


var app=createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.use(store).use(router).use(ElementPlus,{locale: zhCn,}).use(ViewUIPlus).use(createVuestic()).mount('#app')
