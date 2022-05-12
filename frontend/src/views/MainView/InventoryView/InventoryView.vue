<template>
  <div class="page-title">
    <h1>添加库存</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>
  <div class="list-form">
    <el-form :model="inventoryForm" ref="form" label-width="100px" :rules="rules">
      <el-form-item label="品名" prop="product_name">
        <el-input class="item-input" v-model="inventoryForm.product_name" @keyup.enter="focusOnNextItem('pv')"/>
      </el-form-item>
      <el-form-item label="型号" prop="product_version">
        <el-input class="item-input" v-model="inventoryForm.product_version" @keyup.enter="focusOnNextItem('inv')" id="pv"/>
      </el-form-item>
      <el-form-item label="库存" prop="inventory">
        <el-input class="item-input" v-model="inventoryForm.inventory" @keyup.enter="handleList" id="inv"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleList" class="item-button">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
  <br/>
  <br/>
  <br/>
  <div class="page-title">
    <h1>现有库存</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>
  <div class="items-container">
    <el-table :data="itemsData" style="width: 100%" table-layout: auto>
      <el-table-column prop="product_name" label="品名"/>
      <el-table-column prop="product_version" label="型号"/>
      <el-table-column prop="inventory" label="库存量"/>
      <el-table-column prop="created_time" label="创建时间"/>
      <el-table-column fixed="right" label="操作">
        <template #default="{ row }">
          <el-select v-model="itemsData[row.productId]" class="m-2" placeholder="Select" size="small"
                     @change="handleDelete(row)">
            <el-option label="删除" value=0></el-option>
          </el-select>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { myInventory } from '@/api/myInventory'
import { addInventory } from '@/api/addInventory'
import {deleteInventory} from "@/api/deleteInventory";
import { ElMessage, ElMessageBox } from 'element-plus'

const rules = ref({
  product_name: [
    {
      required: true,
      message: '请输入品名',
      trigger: 'blur'
    }
  ],
  product_version: [
    {
      required: true,
      message: '请输入型号',
      trigger: 'blur'
    }
  ],
  inventory: [
    {
      required: true,
      message: '请输入库存',
      trigger: 'blur'
    }
  ]
})

const inventoryForm = ref({
  product_name: '',
  product_version: '',
  inventory: ''
})

const itemsData = ref([])
onMounted(async () => {
  const y = await myInventory(localStorage.getItem('token'))
  itemsData.value = y
})

const initProductData = async () => {
  itemsData.value = []
  itemsData.value = await myInventory(localStorage.getItem('token'))
}

const handleList = async () => {
  addInventory(inventoryForm.value, localStorage.getItem('token')).then(() => {
    ElMessageBox.alert('提交成功！', {
      confirmButtonText: 'OK',
      callback: () => {
        setTimeout(initProductData, 1)
        setTimeout(resetInventoryForm, 1)
        ElMessage({
          type: 'info',
          message: '已更新列表'
        })
      }
    })
  }).catch((err) => {
    alert(err)
  })
  setTimeout(initProductData, 1)
}

const handleDelete = async (row) => {
  deleteInventory({
    token: localStorage.getItem('token'),
    productId: row.productId,
  })
  ElMessage({
    type: 'info',
    message: '已删除'
  })
  setTimeout(initProductData, 1)
}

const resetInventoryForm = async() => {
  inventoryForm.value = {
    product_name: '',
    product_version: '',
    inventory: ''
  }
}

const focusOnNextItem = (nextItemId) => {
  var nextItem = document.getElementById(nextItemId)
  nextItem.focus()
}
</script>

<style scoped lang="scss">

.list-form {
  margin-left: 350px;
}

.page-title {
  font-size: 20px;
  text-align: left;
  margin-left: 300px;
  margin-bottom: 20px;
}

.item-input {
  width: 400px;
}

.item-button {
  margin-left: 10px;
  margin-top: 20px;
}

.items-container {
  margin-left: 300px;
  width: 1000px;
}

.page-title {
  font-size: 20px;
  text-align: left;
  margin-left: 300px;
  margin-bottom: 20px;
}
</style>
