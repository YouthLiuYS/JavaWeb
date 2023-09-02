<template>
        <!-- 实验室申请 -->

        <el-form-item>
                <el-button type="primary" @click="dialogFormVisible = true">新增</el-button>
        </el-form-item>
        <!-- 列表 -->

        <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="applyId" label="申请id" width="180" />
                <el-table-column prop="userEntity.name" label="申请人" width="180" />
                <el-table-column prop="applyNum" label="申请编号" width="180" />
                <el-table-column prop="applyDay" label="申请天数" width="180" />
                <el-table-column prop="applyReason" label="申请原因" width="180" />
                <el-table-column :formatter="formatter" prop="applyStatus" label="申请状态" width="180" />
                <el-table-column prop="applyDatetime" label="申请时间" width="180" />
                <el-table-column prop="reviewComments" label="审批意见" width="180" />
                <el-table-column prop="reviewDatetime" label="审批时间" />
                <el-table-column label="操作">
                        <template #default="scope">
                                <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        </template>
                </el-table-column>
        </el-table>
        <!-- 列表 -->
        <!-- 弹出框 表单 -->
        <el-dialog v-model="dialogFormVisible" title="新增">
                <el-form ref="dialogForm" :rules="rules" :model="form">
                        <el-form-item label="申请编号" prop="applyNum" :label-width="formLabelWidth">
                                <el-input v-model="form.applyNum" autocomplete="off" />
                        </el-form-item>
                        <el-form-item label="申请天数" prop="applyDay" :label-width="formLabelWidth">
                                <el-input v-model="form.applyDay" autocomplete="off" />
                        </el-form-item>
                        <el-form-item label="申请原因" prop="applyReason" :label-width="formLabelWidth">
                                <el-input v-model="form.applyReason" autocomplete="off" />
                        </el-form-item>
                        <el-form-item label="申请时间" prop="applyDatetime" :label-width="formLabelWidth">
                                <el-date-picker v-model="form.applyDatetime" format="YYYY-MM-DD HH:mm:ss"
                                        value-format="YYYY-MM-DD HH:mm:ss" type="datetime" laceholder="选择时间" />
                        </el-form-item>
                </el-form>
                <template #footer>
                        <span class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取消</el-button>
                                <el-button type="primary" @click="add"> 提交 </el-button>
                        </span>
                </template>
        </el-dialog>
        <!-- 弹出框 表单 -->
</template>
    
<script setup >
import { ref, reactive } from "vue"
import requst from "@/utils/request.js"
import { ElMessage } from "element-plus"
// import storage from '@/store/storage.js'

// const userId = storage.get('userId')
// console.log('userId:' + userId)
import useUserStore from '@/store/userstore.js'
// 要使用，第一步类似初始化
const userStore = useUserStore()
const userId = userStore.user.userId

// 定义表格数据对象
const tableData = ref([]);
//  定义初始化数据方法
const getData = async () => {
        const path = "/apply/listApplysByUserId?userId=" + userId
        await requst.get(path).then(
                res => {
                        console.log(res)
                        //res.data是res的data对象
                        tableData.value = res.data.data
                }
        )
}
// 执行数据初始化方法
getData();

// 格式化列
const formatter = (row, column, cellValue, index) => {
        if (cellValue === 0) {
                return "待审批";
        } else if (cellValue === 1) {
                return "审批通过";
        } else if (cellValue === 2) {
                return "审批不通过";
        }
}

// 弹出框可见性.默认为隐藏
const dialogFormVisible = ref(false);

// 表单数据对象
const form = reactive({
        applyNum: '',
        applyDay: '',
        applyReason: '',
        applyDatetime: ''
})
// 表单对象,用于数据校验
const dialogForm = ref(null)
// 表单校验规则
const rules = {
        applyNum: [{ required: true, message: "请输入申请编号", trigger: "blur" }],
        applyDay: [{ required: true, message: "请输入申请天数", trigger: "blur" }],
        applyReason: [{ required: true, message: "请输入申请原因", trigger: "blur" }],
        applyDatetime: [{ required: true, message: "请选择申请时间", trigger: "blur" }]
}

// 新增申请
const add = () => {
        console.log(2222);
        dialogForm.value.validate(
                (valid) => {
                        if (valid) {
                                // 请求后台,新增数据
                                const path = "/apply/insertApply"
                                form.userId = userId
                                // 默认为待审批
                                form.applyStatus = 0
                                requst.post(path, form).then(
                                        res => {
                                                if (res.data.code === 200) {
                                                        dialogFormVisible.value = false
                                                        ElMessage({
                                                                message: res.data.message,
                                                                type: "success",
                                                        })
                                                        // 刷新页面
                                                        getData()
                                                } else {
                                                        ElMessage({
                                                                message: res.data.message,
                                                                type: "error",
                                                        })
                                                }
                                        }
                                )
                        } else {
                                console.log("校验不通过")
                        }
                }
        )
};

// 删除
const handleDelete = (index, row) => {
        console.log(row.applyId);
        const path = "/apply/deleteApplyById";
        requst.get(path, { params: { applyId: row.applyId } }).then((res) => {
                if (res.data.code === 200) {
                        dialogFormVisible.value = false;
                        ElMessage({
                                message: res.data.message,
                                type: "success",
                        });
                        // 刷新页面
                        getData();
                } else {
                        ElMessage({
                                message: res.data.message,
                                type: "error",
                        });
                }
        });
};
</script>
    
<style></style>