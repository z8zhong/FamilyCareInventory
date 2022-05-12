<template>
  <div class="page-title">
    <h1>添加采购</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>
  <div class="list-form">
    <el-form :model="purchaseForm" ref="form" label-width="100px" :rules="rules">
      <el-form-item prop="name" label="采购抬头" @keyup.enter="focusOnNextItem('deposit')">
        <el-input v-model="purchaseForm.name" class="item-input"></el-input>
      </el-form-item>
      <el-form-item prop="deposit" label="采购订金">
        <el-input v-model="purchaseForm.deposit" class="item-input" @keyup.enter="focusOnNextItem('bt')" id="deposit"></el-input>
      </el-form-item>
      <el-form-item prop="buy_time" label="采购日期">
      <el-date-picker
          type="date" placeholder="选择日期" v-model="purchaseForm.buy_time" id="bt" @keyup.enter="focusOnNextItem('note')">
      </el-date-picker>
      </el-form-item>
      <el-form-item label="备注">
        <el-input class="item-input" size="large" v-model="purchaseForm.note" type="textarea" id="note" @keyup.enter="focusOnNextItem('pn')"/>
      </el-form-item>
      <el-form-item v-for="(product, index) in purchaseForm.products"
                    :label="'商品' + index"
                    :key="product.key"
                    :prop="'商品.' + index + '.product_name'">
        <el-col :span="3">
        <el-input v-model="product.product_name" placeholder="品名" style="width: 100%;" id="pn"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
          <el-col :span="3">
          <el-input v-model="product.product_version" placeholder="型号" style="width: 100%;"></el-input>
          </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-col :span="3">
          <el-input v-model="product.quantity" placeholder="数量" style="width: 100%;"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-col :span="3">
          <el-input v-model="product.price" placeholder="单价" style="width: 100%;"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-button @click.prevent="removeProduct(product)">删除</el-button>
        </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addbuy" class="item-button">提交</el-button>
        <el-button @click="addProduct" class="item-button">新增商品</el-button>
        <el-button @click="resetPurchaseForm" class="item-button">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  <br/>
  <br/>
  <br/>
  <div class="page-title">
    <h1>采购列表</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>

  <div class="items-container">
    <el-table :data="buysData" style="width: 100%" table-layout: auto>
      <el-table-column prop="name" label="采购抬头"/>
      <el-table-column prop="deposit" label="采购订金"/>
      <el-table-column prop="buy_time" label="采购日期"/>
      <el-table-column prop="note" label="备注"/>
      <el-table-column prop="ship-time" label="发运时间">
        <template #default="scope">
        <el-date-picker v-model="shiptimeValue[scope.row.buyId]" @change="handleUpdateShipTime(scope.row.buyId)"
            type="date" placeholder="选择日期" v-if="!scope.row.ship_time">
        </el-date-picker>
          <p v-if="scope.row.ship_time">{{ scope.row.ship_time }}</p>
        </template>
      </el-table-column>
      <el-table-column label="商品列表">
        <template #default="{ row }">
          <el-button type="text" size="small" @click="handleDetail(row)"
          >Detail
          </el-button
          >
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template #default="{ row }">
      <el-select v-model="buysData[row.buyId]" class="m-2" placeholder="Select" size="small"
                 @change="handleDelete(row)">
        <el-option label="删除" value=0></el-option>
      </el-select>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
        v-model="showDialog"
        title="商品列表"
    >
      <el-table :data="dialogData" style="width: 100%" table-layout: auto>
        <el-table-column prop="product_name" label="品名"/>
        <el-table-column prop="product_version" label="型号"/>
        <el-table-column prop="quantity" label="数量"/>
        <el-table-column prop="price" label="单价"/>
      </el-table>
    </el-dialog>
  </div>
</template>

<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import {onMounted, ref} from "vue";
import { addBuy } from '@/api/addBuy'
import {myBuys} from "@/api/myBuy"
import {buyDetail} from "@/api/buyDetail";
import {deleteBuy} from "@/api/deleteBuy"
import {updateShipTime} from "@/api/updateShipTime"

const rules = ref({
  name: [
    {
      required: true,
      message: '请输入采购抬头',
      trigger: 'blur'
    }
  ],
  deposit: [
    {
      required: true,
      message: '请输入采购订金',
      trigger: 'blur'
    }
  ],
  buy_time: [
    {
      required: true,
      message: '请输入采购日期',
      trigger: 'blur'
    }
  ]
})

onMounted(async () => {
  const y = await myBuys(localStorage.getItem('token'))
  buysData.value = y
})

const purchaseForm = ref({
  products: [{
    product_name: '',
    product_version: '',
    quantity: '',
    price: ''
  }],
  name: '',
  deposit: '',
  buy_time: '',
  note: ''
})

const addbuy = async () => {
  addBuy(purchaseForm.value, localStorage.getItem('token')).then(() => {
    ElMessageBox.alert('提交成功！', {
      confirmButtonText: 'OK',
      callback: () => {
        setTimeout(initBuyData, 1)
        setTimeout(resetPurchaseForm, 1)
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

const addProduct = async() =>{
  purchaseForm.value.products.push({
    product_name: '',
    product_version: '',
    quantity: '',
    price: '',
    key: Date.now()
  })}

const removeProduct = (product) => {
  var index = purchaseForm.value.products.indexOf(product)
  if (index !== -1) {
    purchaseForm.value.products.splice(index, 1)
  }
}

const resetPurchaseForm = async() =>{
  purchaseForm.value = {
    products: [{
      product_name: '',
      product_version: '',
      quantity: '',
      price: ''
    }],
    name: '',
    deposit: '',
    buy_time: '',
    note: ''
  }
}

const buysData = ref([])
const showDialog = ref(false)
const dialogData = ref([])
const handleDetail = async (row) => {
  // eslint-disable-next-line
  const x = await buyDetail(row.buyId, localStorage.getItem('token'))
  showDialog.value = true
  dialogData.value = x
}
const initBuyData = async () => {
  buysData.value = []
  buysData.value = await myBuys(localStorage.getItem('token'))
}
const handleDelete = async (row) => {
  deleteBuy({
    token: localStorage.getItem('token'),
    buyId: row.buyId,
  })
  ElMessage({
    type: 'info',
    message: '已删除'
  })
  setTimeout(initBuyData, 1)
}
const shiptimeValue = ref([])
const handleUpdateShipTime = async (row) => {
  const body = {
    token: localStorage.getItem('token'),
    ship_time: shiptimeValue.value[row],
    buyId: row
  }
  updateShipTime(body)
  ElMessage({
    type: 'info',
    message: '已更新发运时间'
  })
  setTimeout(initBuyData, 1)
  shiptimeValue.value = []
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
