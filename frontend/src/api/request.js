import axios from 'axios'
import { ElMessage } from 'element-plus'
const service = axios.create({
  baseURL: '/api',
  timeout: 5000,
  headers: { 'Content-Type': 'application/json;charset=UTF-8' }
})

service.interceptors.response.use(
  (response) => {
    const { data, meta } = response.data
    if (meta.status === 200 || meta.status === 201) {
      return data
    } else {
      ElMessage.error(meta.msg)
      // alert(meta.msg)
      return Promise.reject(new Error(meta.msg))
    }
  },
  (error) => {
    console.log(error.response)
    error.response && ElMessage.error(error.response.data)
    return Promise.reject(new Error(error.response.data))
  }
)

export default service
