<template>
  <el-menu
      class="el-menu-vertical-demo"
      default-active="/Main"
      router
  >
    <el-menu-item index="/Main/MainPage">
      <el-icon>
        <icon-menu/>
      </el-icon>
      <span style="font-size:18px;">主页</span>
    </el-menu-item>
    <el-menu-item index="/Main/Inventory">
      <el-icon>
        <icon-menu/>
      </el-icon>
      <span style="font-size:18px;">库存</span>
    </el-menu-item>
    <el-menu-item index="/Main/Purchase">
      <el-icon>
        <icon-menu/>
      </el-icon>
      <span style="font-size:18px;">采购</span>
    </el-menu-item>
    <el-menu-item index="/Main/Order">
      <el-icon>
        <icon-menu/>
      </el-icon>
      <span style="font-size:18px;">销售</span>
    </el-menu-item>
    <el-menu-item index="/Main/Cost">
      <el-icon>
        <setting/>
      </el-icon>
      <span style="font-size:18px;">费用</span>
    </el-menu-item>
    <el-menu-item index="/Main/MyAccount">
      <el-icon>
        <icon-menu/>
      </el-icon>
      <span style="font-size:18px;">财务</span>
    </el-menu-item>
  </el-menu>

</template>

<script setup>

import { onMounted } from 'vue'
import router from '@/router'
onMounted(() => {
  router.push('/Main/MainPage')
})
</script>

<style scoped>

</style>
