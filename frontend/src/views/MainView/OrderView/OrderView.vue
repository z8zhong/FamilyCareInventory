<template>
  <div class="page-title">
    <h1>添加成交</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>
  <div class="list-form">
    <el-form :model="orderForm" ref="form" label-width="100px" :rules="rules">
      <el-form-item prop="name" label="成交抬头">
        <el-input v-model="orderForm.name" class="item-input" @keyup.enter="focusOnNextItem('cn')"></el-input>
      </el-form-item>
      <el-form-item prop="customer_name" label="客户名称">
        <el-input v-model="orderForm.customer_name" class="item-input" @keyup.enter="focusOnNextItem('total')" id="cn"></el-input>
      </el-form-item>
      <el-form-item prop="total" label="成交额">
        <el-input v-model="orderForm.total" class="item-input" @keyup.enter="focusOnNextItem('tt')" id="total"></el-input>
      </el-form-item>
      <el-form-item label="成交日期" prop="trans_time">
        <el-date-picker
            type="date" placeholder="选择日期" v-model="orderForm.trans_time" id="tt">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="成交方式" prop="flag">
        <el-radio-group v-model="orderForm.flag">
          <el-radio label="offline">线下</el-radio>
          <el-radio label="online">线上</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="支付方式" prop="method">
        <el-radio-group v-model="orderForm.method">
          <el-radio label="cash">现金</el-radio>
          <el-radio label="etransfer">电子支付</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注">
        <el-input class="item-input" size="large" v-model="orderForm.note" type="textarea" id="note" @keyup.enter="focusOnNextItem('pn')"/>
      </el-form-item>
      <el-form-item v-for="(product, index) in orderForm.products"
                    :label="'商品' + index"
                    :key="product.key"
                    :prop="'商品.' + index + '.product_name'">
        <el-col :span="5">
          <el-input v-model="product.product_name" placeholder="品名" style="width: 100%;"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-col :span="5">
          <el-input v-model="product.product_version" placeholder="型号" style="width: 100%;"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-col :span="5">
          <el-input v-model="product.quantity" placeholder="数量" style="width: 100%;"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-col :span="5">
          <el-input v-model="product.price" placeholder="单价" style="width: 100%;"></el-input>
        </el-col>
        &nbsp;&nbsp;&nbsp;
        <el-button @click.prevent="removeOrderProduct(product)">删除</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addorder" class="item-button">提交</el-button>
        <el-button @click="addOrderProduct" class="item-button">新增商品</el-button>
        <el-button @click="resetOrderForm" class="item-button">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  <br/>
  <br/>
  <br/>
  <div class="page-title">
    <h1>成交列表</h1>
    <hr style="width:50%;text-align:left;margin-left:0">
  </div>

  <div class="items-container">
    <el-table :data="ordersData" style="width: 100%" table-layout: auto>
      <el-table-column prop="note" label="成交抬头"/>
      <el-table-column prop="customer_name" label="客户名称"/>
      <el-table-column prop="total" label="成交额"/>
      <el-table-column prop="trans_time" label="成交日期"/>
      <el-table-column prop="flag" label="成交方式"/>
      <el-table-column prop="method" label="支付方式"/>
      <el-table-column prop="note" label="备注"/>
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
          <el-select v-model="ordersData[row.orderId]" class="m-2" placeholder="Select" size="small"
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
import {myOrders} from "@/api/myOrder"
import {orderDetail} from "@/api/orderDetail";
import {deleteOrder} from "@/api/deleteOrder"
import { addOrder } from '@/api/addOrder'

const rules = ref({
  name: [
    {
      required: true,
      message: '请输入成交抬头',
      trigger: 'blur'
    }
  ],
  customer_name: [
    {
      required: true,
      message: '请输入客户名称',
      trigger: 'blur'
    }
  ],
  total: [
    {
      required: true,
      message: '请输入成交金额',
      trigger: 'blur'
    }
  ],
  trans_time: [
    {
      required: true,
      message: '请输入成交日期',
      trigger: 'blur'
    }
  ],
  flag: [
    {
      required: true,
      message: '请输入成交方式',
      trigger: 'blur'
    }
  ],
  method: [
    {
      required: true,
      message: '请输入支付方式',
      trigger: 'blur'
    }
  ],
})

onMounted(async () => {
  const y = await myOrders(localStorage.getItem('token'))
  ordersData.value = y
})

const orderForm = ref({
  products: [{
    product_name: '',
    product_version: '',
    quantity: '',
    price: ''
    }],
  note: '',
  trans_time: '',
  flag: '',
  total: '',
  customer_name: '',
  method: ''
})

const addorder = async () => {
  var legacy_total = 0
  for (let product of orderForm.value.products) {
    legacy_total += product.price * product.quantity
  }
  if (eval(legacy_total) !== eval(orderForm.value.total)) {
    ElMessage({
      type: 'error',
      message: '总成交额与所有商品销售额总和不符，请重新检查'
    })
    return
  }
  addOrder(orderForm.value, localStorage.getItem('token')).then(() => {
    ElMessageBox.alert('提交成功！', {
      confirmButtonText: 'OK',
      callback: () => {
        setTimeout(initOrderData, 1)
        setTimeout(resetOrderForm, 1)
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

const addOrderProduct = async() =>{
  orderForm.value.products.push({
    product_name: '',
    product_version: '',
    quantity: '',
    price: '',
    key: Date.now()
  })}

const removeOrderProduct = (product) => {
  var index = orderForm.value.products.indexOf(product)
  if (index !== -1) {
    orderForm.value.products.splice(index, 1)
  }
}

const resetOrderForm = async() => {
  orderForm.value = {
    products: [{
      product_name: '',
      product_version: '',
      quantity: '',
      price: ''
    }],
    note: '',
    trans_time: '',
    flag: '',
    total: '',
    customer_name: '',
    method: ''
  }
}
const ordersData = ref([])
const showDialog = ref(false)
const dialogData = ref([])

const handleDetail = async (row) => {
  // eslint-disable-next-line
  const x = await orderDetail(row.orderId, localStorage.getItem('token'))
  showDialog.value = true
  dialogData.value = x
}

const initOrderData = async () => {
  ordersData.value = []
  ordersData.value = await myOrders(localStorage.getItem('token'))
}

const handleDelete = async (row) => {
  deleteOrder({
    token: localStorage.getItem('token'),
    orderId: row.orderId,
  })
  ElMessage({
    type: 'info',
    message: '已删除'
  })
  setTimeout(initOrderData, 1)
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
