<template>
  <div>
    <!-- 患者管理页面内容 -->
    <div>
      <!-- 添加患者按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddPatient"
                     v-hasPermi="['patient:patient:add']">新增患者
          </el-button>
        </el-col>
      </el-row>

      <!-- 患者列表 -->
      <el-table :data="patientList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="患者ID" prop="id" align="center"></el-table-column>
        <el-table-column label="病历号" prop="medicalRecordNo" align="center"></el-table-column>
        <el-table-column label="年龄" prop="age" align="center"></el-table-column>
        <el-table-column label="性别" prop="gender" align="center">
          <template slot-scope="scope">
            {{ scope.row.gender === 'M' ? '男' : scope.row.gender === 'F' ? '女' : '未知' }}
          </template>
        </el-table-column>
        <el-table-column label="关联患者" prop="userId" align="center">
          <template slot-scope="scope">
            {{ getPatientName(scope.row.userId) }}
          </template>
        </el-table-column>
        <el-table-column label="负责医生" prop="doctorId" align="center">
          <template slot-scope="scope">
            {{ getDoctorName(scope.row.doctorId) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="210px">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(scope.row)"
                       v-hasPermi="['patient:patient:view']">查看
            </el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['patient:patient:edit']">编辑
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['patient:patient:delete']">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <pagination
      v-show="totalPatients > 0"
      :total="totalPatients"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchPatients"
    />

    <!-- 添加/编辑患者对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="40%" @close="handleCloseDialog">
      <!-- 对话框内容 -->
      <div>
        <el-form :model="patientForm" label-width="120px" :rules="formRules" ref="patientForm">
          <el-form-item label="病历号" prop="medicalRecordNo">
            <el-input v-model="patientForm.medicalRecordNo" :disabled="isReadOnly" placeholder="请输入病历号"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="patientForm.age" type="number" :min="0" :max="150"
                      :disabled="isReadOnly" placeholder="请输入年龄"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-select v-model="patientForm.gender" :disabled="isReadOnly" placeholder="请选择性别">
              <el-option label="男" value="M"></el-option>
              <el-option label="女" value="F"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="关联患者" prop="userId">
            <el-select v-model="patientForm.userId" :disabled="isReadOnly" placeholder="请选择关联患者" clearable>
              <el-option
                v-for="patient in patientOptions"
                :key="patient.userId"
                :label="patient.nickName"
                :value="patient.userId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="负责医生" prop="doctorId">
            <el-select v-model="patientForm.doctorId" :disabled="isReadOnly" placeholder="请选择负责医生" clearable>
              <el-option
                v-for="doctor in doctorOptions"
                :key="doctor.userId"
                :label="doctor.nickName"
                :value="doctor.userId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="过敏史/病史" prop="history">
            <el-input v-model="patientForm.history" :disabled="isReadOnly" type="textarea" 
                      autosize placeholder="请输入过敏史或病史信息"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 对话框按钮 -->
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit" v-if="!isReadOnly">{{ dialogButtonText }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listAllPatients, addPatient, updatePatient, deletePatient, getPatientById} from '@/api/patient/patient'
import {doctorList, patientList} from '@/api/system/user'

export default {
  data() {
    return {
      loading: false,
      patientList: [],
      doctorOptions: [], // 医生下拉选项
      patientOptions: [], // 患者下拉选项
      dialogVisible: false,
      dialogTitle: '',
      dialogButtonText: '',
      totalPatients: 0,
      isReadOnly: false, // 是否只读模式
      patientForm: {
        id: null,
        medicalRecordNo: '',
        age: null,
        gender: '',
        userId: null,
        doctorId: null,
        history: ''
      },
      // 表单验证规则
      formRules: {
        medicalRecordNo: [
          { required: true, message: '请输入病历号', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          { 
            validator: (rule, value, callback) => {
              if (value === '' || value === null || value === undefined) {
                callback()
                return
              }
              const age = Number(value)
              if (isNaN(age) || age < 0 || age > 150) {
                callback(new Error('请输入有效的年龄(0-150)'))
              } else {
                callback()
              }
            }, 
            trigger: 'blur' 
          }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ]
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
    }
  },
  created() {
    this.fetchPatients()
    this.fetchDoctorOptions()
    this.fetchPatientOptions()
  },
  methods: {
    fetchPatients() {
      this.loading = true
      listAllPatients(this.queryParams).then(response => {
        this.patientList = response.rows
        this.totalPatients = response.total
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    fetchDoctorOptions() {
      doctorList(this.queryParams).then(response => {
        this.doctorOptions = response.rows.map(doctor => ({
          userId: doctor.userId,
          nickName: doctor.nickName
        }))
      })
    },
    fetchPatientOptions() {
      patientList(this.queryParams).then(response => {
        this.patientOptions = response.rows.map(patient => ({
          userId: patient.userId,
          nickName: patient.nickName
        }))
      })
    },
    getDoctorName(doctorId) {
      if (!doctorId) return '-'
      const doctor = this.doctorOptions.find(d => d.userId === doctorId)
      return doctor ? doctor.nickName : `ID: ${doctorId}`
    },
    getPatientName(patientUserId) {
      if (!patientUserId) return '-'
      const patient = this.patientOptions.find(p => p.userId === patientUserId)
      return patient ? patient.nickName : `ID: ${patientUserId}`
    },
    handleView(row) {
      this.dialogTitle = '查看患者'
      this.dialogButtonText = '关闭'
      this.isReadOnly = true
      getPatientById(row.id).then(response => {
        this.patientForm = response.data
        this.dialogVisible = true
      })
    },
    handleAddPatient() {
      this.isReadOnly = false
      this.dialogTitle = '新增患者'
      this.dialogButtonText = '添加'
      this.dialogVisible = true
    },
    handleEdit(row) {
      this.isReadOnly = false
      this.dialogTitle = '编辑患者'
      this.dialogButtonText = '更新'
      this.patientForm = Object.assign({}, row)
      this.dialogVisible = true
    },
    handleDelete(row) {
      this.$confirm('确认删除该患者吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deletePatient(row.id).then(() => {
          this.$message.success('删除成功')
          this.fetchPatients()
        })
      })
    },
    handleCloseDialog() {
      this.dialogVisible = false
      this.clearForm()
      // 清除表单验证
      if (this.$refs.patientForm) {
        this.$refs.patientForm.clearValidate()
      }
    },
    clearForm() {
      this.patientForm = {
        id: null,
        medicalRecordNo: '',
        age: null,
        gender: '',
        userId: null,
        doctorId: null,
        history: ''
      }
    },
    handleSubmit() {
      this.$refs.patientForm.validate((valid) => {
        if (valid) {
          // 确保年龄字段为数字类型
          const submitData = {
            ...this.patientForm,
            age: this.patientForm.age ? Number(this.patientForm.age) : null
          }
          
          if (this.dialogButtonText === '添加') {
            addPatient(submitData).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.clearForm()
              this.fetchPatients()
            }).catch(error => {
              console.error('添加失败:', error)
              this.$message.error('添加失败，请检查数据')
            })
          } else if (this.dialogButtonText === '更新') {
            updatePatient(submitData).then(() => {
              this.$message.success('更新成功')
              this.dialogVisible = false
              this.clearForm()
              this.fetchPatients()
            }).catch(error => {
              console.error('更新失败:', error)
              this.$message.error('更新失败，请检查数据')
            })
          }
        } else {
          this.$message.error('请检查表单信息')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
/* 样式可以根据需求调整 */
</style>
