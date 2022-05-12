import { login as loginApi } from '@/api/login'
import router from '@/router'
// eslint-disable-next-line
// import { setTokenTime } from '@/utils/auth'
export default {
  namespaced: true,
  state: () => ({
    token: localStorage.getItem('token') || ''
  }),
  mutations: {
    setToken (state, token) {
      console.log('token is' + token)
      state.token = token
      localStorage.setItem('token', token)
    }
  },
  actions: {
    login ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        console.log(commit)
        console.log(userInfo)
        loginApi(userInfo)
          .then((res) => {
            commit('setToken', res)
            // setTokenTime()
            router.replace('/Main')
            resolve()
          })
          .catch((err) => {
            reject(err)
          })
      })
    },
    logout ({ commit }) {
      console.log('log out!')
      commit('setToken', '')
      localStorage.clear()
      router.replace('/')
    }
  }
}
