<template>
  <div class="sdfaw" :style="handleColor()">hello linge</div>
    <el-dialog v-model="outerVisible">
      <div :style="handleColor()">hello linge</div>
    </el-dialog>
  <el-button
      @click="handleClick"
  >111
  </el-button>
</template>

<script setup>
import { ref } from 'vue'

const outerVisible = ref('false')
const color = ref('red')
const handleClick = () => {
  outerVisible.value = true
  color.value = 'blue'
}
const handleColor = () => {
  return {
    background: 'red',
    color: '#FFFFFF'
  }
}
</script>
<style lang="scss">
.box {
  width: 400px;
  height: 500px;
  color: v-bind(color);
}

.box2 {
  width: 400px;
  height: 500px;
  color: v-bind(color);
}
</style>
