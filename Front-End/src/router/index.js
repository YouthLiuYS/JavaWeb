//在使用路由之前，需要安装vue-router
//路由文件入口

//引入创建方法
import {createRouter,createWebHashHistory} from 'vue-router'

import Layout from '@/layout/index.vue'

//映引入全局状态
// import {store} from '@/store/store.js'
// import storage from '../store/storage'
import useUserStore from '@/store/userStore.js'


//配置映射关系，请求指定地址和组件的映射
export const routes = [
	{
		path: '/',//请求路径
		name: '登录页面',
		component: () => import('@/views/login/index.vue') //页面
	},
	{
		path: '/register',//请求路径
		name: '注册页面',
		component: () => import('@/views/register/index.vue') //页面
	},
	{
		path: '/layout',
		name: 'layout',
		component: Layout,
		children: []
	}
	// {
	// 	path: '/layout', //请求路径
	// 	name: '首页',
	// 	component: Layout, //组件
	// 	children: [  //指子页面
	// 		{
	// 			path: '/main',
	// 			name: '首页',
	// 			component: () => import('@/views/main/index.vue')
	// 		},
	// 		{
	// 			path: '/apply',
	// 			name: '实验室申请',
	// 			component: () => import('@/views/apply/index.vue')
	// 		}
	// 	]
	// },
	// {
	// 	path: '/apply',
	// 	name: '实验室申请',
	// 	component: Layout,
	// 	children: [  //指子页面
	// 		{
	// 			path: '',
	// 			name: '实验室申请',
	// 			component: () => import('@/views/apply/index.vue')
	// 		}
	// 	]
	// },
	// {
	// 	path: '/reviewapply',
	// 	name: '申请审批',
	// 	component: Layout,
	// 	children: [  //指子页面
	// 		{
	// 			path: '',
	// 			name: '申请审批',
	// 			component: () => import('@/views/reviewapply/index.vue')
	// 		}
	// 	]
	// }
	
]


//白名单，路由守卫不去判断登录就可以访问
const whiteList = ['/','/register']
//创建路由实例，导入模式和配置
const router = createRouter({
	history: createWebHashHistory(),//模式
	routes//: routes
	//ES6的规范，如果键和值的名称相同，可以省略为 键
	//routes
})

//路由守卫
router.beforeEach((to,from,next) =>{
	
	const userStore = useUserStore()
	
	//判断是否登录,跳过登录页、注册页的判断
	
	console.log(!userStore.isLogin)
	
	if(!userStore.isLogin && whiteList.indexOf(to.path) == -1){
		next('/')
		return false
	}
	
	const menuList = userStore.menuList
	
	if(menuList){
		//vite不支持 () => import('@/views/reviewapply/index.vue')
		//需要使用下面的方式
		//参考连接：https://cn.vitejs.dev/guide/features.html#glob-import
		const modules = import.meta.glob('../views/**/*')
		menuList.forEach(
			menu => {
				//router.addRoute
				router.addRoute('layout',{
					name: menu.menuName,
					path: menu.menuPath,
					 component: modules[`../views${menu.menuUrl}`]
				})
			}
		)
		console.log(router.getRoutes())
	}
	
	// let flag = 0
	// if(flag ===0 && to.matched.length == 0)
	// 解决动态路由刷新白屏问题
	let flag = 0
	if (flag == 0 && to.matched.length == 0) {
	    flag++
	    router.push(to.path)
	} else if (flag !== 0 && to.matched.length == 0) {
	    next('/')
		return false
	} else {
	    next()
		return true
	}
	next()
})


//导出路由
export default router