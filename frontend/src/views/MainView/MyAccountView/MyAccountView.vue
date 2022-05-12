<template>
<div class="page-title">
  <h1>财务总结</h1>
  <hr style="width:50%;text-align:left;margin-left:0">
</div>
<div class="items-container">
  <el-table :data="transsumData" fit height="120px">
    <el-table-column prop="order_amt" label="成交总额"/>
    <el-table-column prop="buy_amt" label="采购费用"/>
    <el-table-column prop="cost_amt" label="其他费用"/>
    <el-table-column prop="cur_acc_amt" label="账户余额"/>
    <el-table-column prop="cur_cash_amt" label="现金余额"/>
  </el-table>
</div>

<div class="page-title">
  <h1>财务明细</h1>
  <hr style="width:50%;text-align:left;margin-left:0">
</div>
<div class="items-container">
  <el-table :data="transData" fit>
    <el-table-column prop="trans_name" label="收支类型"/>
    <el-table-column prop="trans_amt" label="数目"/>
    <el-table-column prop="trans_date" label="日期"/>
    <el-table-column prop="cur_acc_amt" label="账户余额"/>
    <el-table-column prop="cur_cash_amt" label="现金余额"/>
    <el-table-column prop="note" label="备注"/>
  </el-table>
</div>
</template>

<script setup>
import { myTrans } from "@/api/myTrans";
import { myTransSum } from "@/api/myTransSum";
import { onMounted, ref } from "vue";

const transsumData = ref([])
const transData = ref([])
const initTransSumData = async() => {
  transsumData.value = []
  transsumData.value = await myTransSum(localStorage.getItem('token'))
}
const initTransData = async() => {
  transData.value = []
  transData.value = await myTrans(localStorage.getItem('token'))
}
onMounted(async () => {
  await initTransSumData()
  await initTransData()
})
</script>

<style scoped lang="scss">
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
