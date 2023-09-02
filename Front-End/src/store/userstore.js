import { defineStore } from 'pinia'

const useUserStore = defineStore('user',{
	state:() => ({
		isLogin: localStorage.getItem('isLogin')?JSON.parse(localStorage.getItem('isLogin')):null,
		user: localStorage.getItem('user')?JSON.parse(localStorage.getItem('user')):null,
		menuList: localStorage.getItem('menuList')?JSON.parse(localStorage.getItem('menuList')):null,
	}),
	actions: {
		login(user){
			this.isLogin = true
			this.user = user
			localStorage.setItem('isLogin',this.isLogin)
			localStorage.setItem('user',JSON.stringify(user))
		},
		logout(){
			this.isLogin = false
			this.user = null
			localStorage.removeItem('isLogin')
			localStorage.removeItem('user')
		},
		setMenu(menuList){
			this.menuList = menuList
			localStorage.setItem('menuList',JSON.stringify(menuList))
		}
	}
})

export default useUserStore