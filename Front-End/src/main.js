//引入css
// import './assets/main.css'
//引入重置样式
import './assets/reset.scss'

import { createApp } from 'vue'
//引入element-plus
import ElementPlus from 'element-plus'

import 'element-plus/dist/index.css'
//引入根组件
import App from './App.vue'
//创建实例
const app = createApp(App)

//引入路由
import router from './router/index.js'

//引入pinia，状态管理
import { createPinia }  from 'pinia'

//使用router的时机，在创建失利之后，挂载之前
// const pinia = createPinia()
// app.use(pinia)
app.use(createPinia())
app.use(router)
app.use(ElementPlus)

//挂载
app.mount('#app')
//全局挂载
// app.config.globalProperties.$axios = request

// createApp(App).use(router).mount('#app')  //等价于上面三行代码
