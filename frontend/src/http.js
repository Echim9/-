import axios from "axios"
import {message} from "ant-design-vue"
import router from "@/router"

axios.interceptors.request.use(config => {
    // config.baseURL = `${host}:8080`
    return config
})

axios.interceptors.response.use(
    response => {return response},
    error => {
        // console.log(error)
        if (error.response.status === 401) {
            message.error("请先登录")
            router.push({name: 'login'})
        }
        else {
            message.error((error.response.data.message != null ? error.response.data.message: error.response.statusText))
        }
        return Promise.reject(error)
    }
)

export default axios
