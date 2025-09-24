<template>
  <div>
    <!-- 药品管理页面内容 -->
    <div>
      <!-- 添加药品按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddDrug"
                     v-hasPermi="['drug:drug:add']">新增药品
          </el-button>
        </el-col>
      </el-row>

      <!-- 药品列表 -->
      <el-table :data="drugList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="药品ID" prop="id" align="center" width="80"></el-table-column>
        <el-table-column label="药品名称" prop="drugName" align="center" width="150"></el-table-column>
        <el-table-column label="药品描述" prop="description" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="适应症" prop="indications" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="注意事项" prop="precautions" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" align="center" width="280px">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(scope.row)"
                       v-hasPermi="['drug:drug:view']">查看
            </el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['drug:drug:edit']">编辑
            </el-button>
            <el-button type="text" icon="el-icon-document" size="mini" @click="handleViewInstructions(scope.row)"
                       v-hasPermi="['drug:instruction:view']">说明书
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['drug:drug:delete']">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <pagination
      v-show="totalDrugs > 0"
      :total="totalDrugs"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchDrugs"
    />

    <!-- 添加/编辑药品对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="50%" @close="handleCloseDialog">
      <!-- 对话框内容 -->
      <div>
        <el-form :model="drugForm" label-width="120px" :rules="formRules" ref="drugForm">
          <el-form-item label="药品名称" prop="drugName">
            <el-input v-model="drugForm.drugName" :disabled="isReadOnly" placeholder="请输入药品名称"></el-input>
          </el-form-item>
          <el-form-item label="药品描述" prop="description">
            <el-input v-model="drugForm.description" :disabled="isReadOnly" type="textarea" 
                      autosize placeholder="请输入药品描述"></el-input>
          </el-form-item>
          <el-form-item label="适应症" prop="indications">
            <el-input v-model="drugForm.indications" :disabled="isReadOnly" type="textarea" 
                      autosize placeholder="请输入药品适应症"></el-input>
          </el-form-item>
          <el-form-item label="注意事项" prop="precautions">
            <el-input v-model="drugForm.precautions" :disabled="isReadOnly" type="textarea" 
                      autosize placeholder="请输入药品注意事项"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 对话框按钮 -->
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit" v-if="!isReadOnly">{{ dialogButtonText }}</el-button>
      </div>
    </el-dialog>

    <!-- 药品说明书管理对话框 -->
    <el-dialog :visible.sync="instructionDialogVisible" :title="instructionDialogTitle" width="70%" @close="handleCloseInstructionDialog">
      <!-- 说明书操作按钮 -->
      <div style="margin-bottom: 15px;">
        <el-button type="primary" size="small" @click="handleAddInstruction"
                   v-hasPermi="['drug:instruction:add']">新增说明书
        </el-button>
      </div>
      
      <!-- 说明书列表 -->
      <el-table :data="instructionList" v-loading="instructionLoading" style="width: 100%" border>
        <el-table-column label="版本号" prop="version" align="center" width="120"></el-table-column>
        <el-table-column label="更新时间" prop="updateTime" align="center" width="160">
          <template slot-scope="scope">
            {{ formatDate(scope.row.updateTime) }}
          </template>
        </el-table-column>
        <el-table-column label="说明书内容" prop="content" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" align="center" width="180px">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleViewInstruction(scope.row)">查看</el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEditInstruction(scope.row)"
                       v-hasPermi="['drug:instruction:edit']">编辑
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDeleteInstruction(scope.row)"
                       v-hasPermi="['drug:instruction:delete']">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加/编辑说明书对话框 -->
    <el-dialog :visible.sync="instructionFormDialogVisible" :title="instructionFormDialogTitle" width="60%" @close="handleCloseInstructionFormDialog">
      <div>
        <el-form :model="instructionForm" label-width="120px" :rules="instructionFormRules" ref="instructionForm">
          <el-form-item label="版本号" prop="version">
            <el-input v-model="instructionForm.version" :disabled="instructionIsReadOnly" placeholder="请输入版本号，如：v1.0"></el-input>
          </el-form-item>
          <el-form-item label="说明书内容" prop="content">
            <el-input v-model="instructionForm.content" :disabled="instructionIsReadOnly" type="textarea" 
                      :rows="15" placeholder="请输入详细的说明书内容"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 对话框按钮 -->
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseInstructionFormDialog">取消</el-button>
        <el-button type="primary" @click="handleInstructionSubmit" v-if="!instructionIsReadOnly">{{ instructionFormDialogButtonText }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listAllDrugs, addDrug, updateDrug, deleteDrug, getDrugById} from '@/api/patient/drug'
import {
  listAllDrugInstructions, 
  getDrugInstructionById, 
  getDrugInstructionsByDrugId,
  addDrugInstruction, 
  updateDrugInstruction, 
  deleteDrugInstruction
} from '@/api/patient/drugInstruction'

export default {
  data() {
    return {
      loading: false,
      drugList: [],
      dialogVisible: false,
      dialogTitle: '',
      dialogButtonText: '',
      totalDrugs: 0,
      isReadOnly: false, // 是否只读模式
      drugForm: {
        id: null,
        drugName: '',
        description: '',
        indications: '',
        precautions: ''
      },
      // 说明书对话框相关
      instructionDialogVisible: false,
      instructionDialogTitle: '',
      instructionList: [],
      instructionLoading: false,
      currentDrugId: null,
      currentDrugName: '',
      // 说明书表单对话框相关
      instructionFormDialogVisible: false,
      instructionFormDialogTitle: '',
      instructionFormDialogButtonText: '',
      instructionIsReadOnly: false,
      instructionForm: {
        id: null,
        drugId: null,
        version: '',
        content: ''
      },
      // 表单验证规则
      formRules: {
        drugName: [
          { required: true, message: '请输入药品名称', trigger: 'blur' },
          { min: 2, max: 50, message: '药品名称长度在2到50个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入药品描述', trigger: 'blur' },
          { max: 500, message: '药品描述不能超过500个字符', trigger: 'blur' }
        ],
        indications: [
          { required: true, message: '请输入适应症', trigger: 'blur' },
          { max: 500, message: '适应症不能超过500个字符', trigger: 'blur' }
        ],
        precautions: [
          { max: 500, message: '注意事项不能超过500个字符', trigger: 'blur' }
        ]
      },
      instructionFormRules: {
        version: [
          { required: true, message: '请输入版本号', trigger: 'blur' },
          { max: 20, message: '版本号不能超过20个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入说明书内容', trigger: 'blur' },
          { min: 10, message: '说明书内容至少10个字符', trigger: 'blur' }
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
    this.fetchDrugs()
  },
  methods: {
    fetchDrugs() {
      this.loading = true
      listAllDrugs(this.queryParams).then(response => {
        this.drugList = response.rows
        this.totalDrugs = response.total
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    handleView(row) {
      this.dialogTitle = '查看药品'
      this.dialogButtonText = '关闭'
      this.isReadOnly = true
      getDrugById(row.id).then(response => {
        this.drugForm = response.data
        this.dialogVisible = true
      })
    },
    handleAddDrug() {
      this.isReadOnly = false
      this.dialogTitle = '新增药品'
      this.dialogButtonText = '添加'
      this.dialogVisible = true
    },
    handleEdit(row) {
      this.isReadOnly = false
      this.dialogTitle = '编辑药品'
      this.dialogButtonText = '更新'
      this.drugForm = Object.assign({}, row)
      this.dialogVisible = true
    },
    handleDelete(row) {
      this.$confirm('确认删除该药品吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteDrug(row.id).then(() => {
          this.$message.success('删除成功')
          this.fetchDrugs()
        })
      })
    },
    handleCloseDialog() {
      this.dialogVisible = false
      this.clearForm()
      // 清除表单验证
      if (this.$refs.drugForm) {
        this.$refs.drugForm.clearValidate()
      }
    },
    clearForm() {
      this.drugForm = {
        id: null,
        drugName: '',
        description: '',
        indications: '',
        precautions: ''
      }
    },
    handleSubmit() {
      this.$refs.drugForm.validate((valid) => {
        if (valid) {
          if (this.dialogButtonText === '添加') {
            addDrug(this.drugForm).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.clearForm()
              this.fetchDrugs()
            }).catch(error => {
              console.error('添加失败:', error)
              this.$message.error('添加失败，请检查数据')
            })
          } else if (this.dialogButtonText === '更新') {
            updateDrug(this.drugForm).then(() => {
              this.$message.success('更新成功')
              this.dialogVisible = false
              this.clearForm()
              this.fetchDrugs()
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
    },

    // ========== 说明书管理相关方法 ==========
    handleViewInstructions(row) {
      this.currentDrugId = row.id
      this.currentDrugName = row.drugName
      this.instructionDialogTitle = `${row.drugName} - 说明书管理`
      this.instructionDialogVisible = true
      this.fetchInstructions()
    },
    fetchInstructions() {
      this.instructionLoading = true
      getDrugInstructionsByDrugId(this.currentDrugId).then(response => {
        console.log('说明书数据响应:', response)
        // 兼容不同的响应格式
        let instructionData = []
        if (response.data) {
          instructionData = Array.isArray(response.data) ? response.data : []
        } else if (response.rows) {
          instructionData = Array.isArray(response.rows) ? response.rows : []
        } else if (Array.isArray(response)) {
          instructionData = response
        }
        
        this.instructionList = instructionData
        console.log('说明书列表数据:', this.instructionList)
        this.instructionLoading = false
      }).catch(error => {
        console.error('获取说明书列表失败:', error)
        this.instructionLoading = false
        this.$message.error('获取说明书列表失败')
      })
    },
    handleAddInstruction() {
      this.instructionIsReadOnly = false
      this.instructionFormDialogTitle = `新增说明书 - ${this.currentDrugName}`
      this.instructionFormDialogButtonText = '添加'
      this.instructionForm.drugId = this.currentDrugId
      this.instructionFormDialogVisible = true
    },
    handleViewInstruction(row) {
      this.instructionIsReadOnly = true
      this.instructionFormDialogTitle = `查看说明书 - ${this.currentDrugName}`
      this.instructionFormDialogButtonText = '关闭'
      this.instructionForm = Object.assign({}, row)
      this.instructionFormDialogVisible = true
    },
    handleEditInstruction(row) {
      this.instructionIsReadOnly = false
      this.instructionFormDialogTitle = `编辑说明书 - ${this.currentDrugName}`
      this.instructionFormDialogButtonText = '更新'
      this.instructionForm = Object.assign({}, row)
      this.instructionFormDialogVisible = true
    },
    handleDeleteInstruction(row) {
      this.$confirm('确认删除该说明书版本吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteDrugInstruction(row.id).then(() => {
          this.$message.success('删除成功')
          this.fetchInstructions()
        })
      })
    },
    handleCloseInstructionDialog() {
      this.instructionDialogVisible = false
      this.instructionList = []
      this.currentDrugId = null
      this.currentDrugName = ''
    },
    handleCloseInstructionFormDialog() {
      this.instructionFormDialogVisible = false
      // 只有在不是从说明书管理页面进入时才完全清空
      if (!this.instructionDialogVisible) {
        this.clearInstructionForm()
      } else {
        // 保留drugId，只清空表单内容
        this.instructionForm = {
          id: null,
          drugId: this.currentDrugId,
          version: '',
          content: ''
        }
      }
      // 清除表单验证
      if (this.$refs.instructionForm) {
        this.$refs.instructionForm.clearValidate()
      }
    },
    clearInstructionForm() {
      // 保留drugId，因为这是当前操作的药品ID
      const currentDrugId = this.instructionForm.drugId
      this.instructionForm = {
        id: null,
        drugId: currentDrugId,
        version: '',
        content: ''
      }
    },
    handleInstructionSubmit() {
      this.$refs.instructionForm.validate((valid) => {
        if (valid) {
          if (this.instructionFormDialogButtonText === '添加') {
            addDrugInstruction(this.instructionForm).then(response => {
              console.log('添加说明书成功响应:', response)
              this.$message.success('添加成功')
              this.instructionFormDialogVisible = false
              this.clearInstructionForm()
              // 延时刷新确保数据已保存
              setTimeout(() => {
                this.fetchInstructions()
              }, 500)
            }).catch(error => {
              console.error('添加失败:', error)
              this.$message.error('添加失败，请检查数据')
            })
          } else if (this.instructionFormDialogButtonText === '更新') {
            updateDrugInstruction(this.instructionForm).then(response => {
              console.log('更新说明书成功响应:', response)
              this.$message.success('更新成功')
              this.instructionFormDialogVisible = false
              this.clearInstructionForm()
              // 延时刷新确保数据已保存
              setTimeout(() => {
                this.fetchInstructions()
              }, 500)
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
    },

    // ========== 工具方法 ==========
    formatDate(dateStr) {
      if (!dateStr) return '-'
      const date = new Date(dateStr)
      return date.toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
      })
    }
  }
}
</script>

<style scoped>
/* 样式可以根据需求调整 */
</style>
