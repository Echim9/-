<template>
  <div class="container">
    <a-form
        :model="loginForm"
        autocomplete="off"
        class="form"
        name="basic"
        @finish="login"
    >
      <a-typography-title style="text-align: center;width: 100%" :level="2">登录</a-typography-title>
      <a-form-item
          :rules="[{ required: true, message: '请输入您的用户名！' }, {validator: rules.username}]"
          class="form-item"
          name="username"
          style="width: 100%"
      >
        <a-input v-model:value="loginForm.username" class="form-input" placeholder="用户名"/>
      </a-form-item>

      <a-form-item
          :rules="[{ required: true, message: '请输入您的密码！' }, {validator: rules.password}]"
          class="form-item"
          name="password"
      >
        <a-input-password v-model:value="loginForm.password" class="form-input" placeholder="密码"/>
      </a-form-item>

      <a-form-item
          class="form-item"
      >
        <a-button html-type="submit" type="primary">登录</a-button>
        <a-button @click="this.$router.push({name: 'register'})">注册</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import {UndoOutlined} from '@ant-design/icons-vue'
import {login} from "@/api/users"
import store from "@/store"
import {message} from "ant-design-vue";

const _ = require('lodash')

export default {
  name: "Login",
  components: {
    UndoOutlined
  },
  data() {
    return {
      codeExpireTime: 100,
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: (rule, value, callback) => {
          if (value && value.length < 4 || value.length > 20) {
            return Promise.reject('用户名长度应为4到20个字符')
          } else if (value && !/^[A-Za-z0-9_]{4,20}$/g.test(value)) {
            return Promise.reject('用户名仅能包含数字、大小写字母和下划线')
          } else {
            return Promise.resolve()
          }
        },
        password: (rule, value, callback) => {
          if (value && value.length < 6 || value.length > 20) {
            return Promise.reject('密码长度应为6到20个字符')
          } else if (value && !/^[A-Za-z0-9_]{6,20}$/g.test(value)) {
            return Promise.reject('密码仅能包含数字、大小写字母和下划线')
          } else {
            return Promise.resolve()
          }
        }
      }
    }
  },
  methods: {
    login: _.throttle(function () {
      login(this.loginForm).then(response => {
        if (response.data.code !== 0) {
          message.error(response.data.message)
        } else {
          store.dispatch('loginState/setToken', response.data.data.token)
          store.dispatch('loginState/setUsername', response.data.data.username)
          message.success('登录成功')
          this.$router.push({name: 'home'})
        }
      })
    }, 5000),
  },
}
</script>

<style scoped>
.container {
  height: 100vh;
  display: flex;
  align-items: center;
}

.form {
  flex: 1;
  text-align: center;
  display: flex;
  align-items: center;
  flex-wrap: wrap
}

:deep(.ant-spin-nested-loading) {
  width: 100%;
  margin-bottom: 15px;
}

:deep(.ant-spin-container) {
  display: flex;
  align-items: center;
  width: fit-content;
  margin: auto;
}

.form-item {
  width: 100%;
}

.form-input {
  width: 300px;
}

</style>
