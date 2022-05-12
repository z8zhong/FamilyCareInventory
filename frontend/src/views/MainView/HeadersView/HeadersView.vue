<template>
<div class="navbar">
  <el-button type="primary" @click="logout" style="margin-top:20px;">退出登录</el-button>
</div>
</template>

<script setup>
// import { ref } from 'vue'
import { useStore } from 'vuex'
const store = useStore()
const logout = () => {
  store.dispatch('app/logout')
}
</script>

<style scoped>

</style>
