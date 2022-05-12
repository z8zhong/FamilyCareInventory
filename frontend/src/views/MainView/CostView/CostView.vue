<template>
  <div class="page-title">
    <h1>添加费用</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>
  <div class="list-form">
    <el-form :model="form" label-width="100px" :rules="rules">
      <el-form-item label="费用名称" prop="name" >
        <el-input class="item-input" size="large" v-model="form.name" @keyup.enter="focusOnNextItem('amt')"/>
      </el-form-item>
      <el-form-item label="费用金额" prop="amt" >
        <el-input class="item-input" size="large" v-model="form.amt" id="amt" @keyup.enter="focusOnNextItem('ct')"/>
      </el-form-item>
      <el-form-item label="日期" prop="cost_time" >
        <el-date-picker
            type="date" placeholder="选择日期" v-model="form.cost_time" id="ct" @keyup.enter="focusOnNextItem('note')">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input class="item-input" size="large" v-model="form.note" type="textarea" id="note" @keyup.enter="handleAdd"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleAdd" class="item-button">提交</el-button>
        <el-button @click="resetForm" class="item-button">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  <br/>
  <br/>
  <br/>
  <div class="page-title">
    <h1>费用明细</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>

  <div class="items-container">
    <el-table :data="costsData" style="width: 100%" table-layout: auto>
      <el-table-column prop="name" label="费用名称"/>
      <el-table-column prop="amt" label="费用金额"/>
      <el-table-column prop="cost_time" label="日期"/>
      <el-table-column prop="note" label="备注"/>
      <el-table-column fixed="right" label="操作">
        <template #default="{ row }">
          <el-select v-model="costsData[row.productId]" class="m-2" placeholder="Select" size="small"
                     @change="handleDeleteCost(row)">
            <el-option label="删除" value=0></el-option>
          </el-select>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useStore } from 'vuex'
import { myCosts } from '@/api/myCost'
import { deleteCost } from "@/api/deleteCost";
import { addCost } from "@/api/addCost";
// eslint-disable-next-line
import { ElMessage, ElMessageBox } from 'element-plus'
const rules = ref({
  name: [
    {
      required: true,
      message: '请输入费用名称',
      trigger: 'blur'
    }
  ],
  amt: [
    {
      required: true,
      message: '请输入费用金额',
      trigger: 'blur'
    }
  ],
  cost_time: [
    {
      required: true,
      message: '请输入日期',
      trigger: 'blur'
    }
  ]
})

const form = ref({
  name: '',
  amt: '',
  cost_time: '',
  note: ''
})
const initCostData = async () => {
  costsData.value = []
  costsData.value = await myCosts(localStorage.getItem('token'))
}

// listItem
// eslint-disable-next-line
const store = useStore()
const costsData = ref([])
onMounted(async () => {
  const y = await myCosts(localStorage.getItem('token'))
  costsData.value = y
})
const handleAdd = async () => {
  // console.log(form)
  // store.dispatch('app/listItem', form.value)
  addCost(form.value, localStorage.getItem('token')).then(() => {
    // console.log('get returned' + res)
    ElMessageBox.alert('提交成功！', {
      confirmButtonText: 'OK',
      callback: () => {
        setTimeout(initCostData, 1)
        form.value = {
          name: '',
          amt: '',
          cost_time: '',
          note: ''
        }
        ElMessage({
          type: 'info',
          message: '已更新列表'
        })
      }
    })
  }).catch((err) => {
    alert(err)
  })
}

const resetForm = async() => {
  form.value = {
    name: '',
    amt: '',
    cost_time: '',
    note: ''
  }
}
const handleDeleteCost = async (row) => {
  console.log(row)
  deleteCost({
    token: localStorage.getItem('token'),
    costId: row.costId,
  })
  ElMessage({
    type: 'info',
    message: '已删除'
  })
  setTimeout(initCostData, 1)
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
