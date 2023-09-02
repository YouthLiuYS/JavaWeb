//放一些全局状态，比如用户的登录状态，用户的基本信息
//所有的组件都能进行访问

import { reactive } from 'vue'

//用户登录状态
export const store = reactive({
	isLogin: false,
	user: {
		account: '',
		name: ''
	}
})

