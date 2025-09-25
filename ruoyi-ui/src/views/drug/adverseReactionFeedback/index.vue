<template>
  <div>
    <!-- 不良反应反馈管理页面内容 -->
    <div>
      <!-- 添加反馈按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddFeedback"
                     v-hasPermi="['drug:adverseReactionFeedback:add']">新增反馈
          </el-button>
        </el-col>
      </el-row>

      <!-- 反馈记录列表 -->
      <el-table :data="feedbackList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="反馈ID" prop="id" align="center" width="80"></el-table-column>
        <el-table-column label="不良反应ID" prop="reactionId" align="center" width="120">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleViewReaction(scope.row.reactionId)">
              {{ scope.row.reactionId }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column label="患者信息" align="center" min-width="150">
          <template slot-scope="scope">
            {{ getReactionInfo(scope.row.reactionId, 'patient') }}
          </template>
        </el-table-column>
        <el-table-column label="药品信息" align="center" min-width="150">
          <template slot-scope="scope">
            {{ getReactionInfo(scope.row.reactionId, 'drug') }}
          </template>
        </el-table-column>
        <el-table-column label="反馈医生" prop="doctorName" align="center" min-width="120">
          <template slot-scope="scope">
            {{ getDoctorName(scope.row.doctorId) }}
          </template>
        </el-table-column>
        <el-table-column label="反馈内容" prop="feedbackContent" align="center" min-width="200" show-overflow-tooltip></el-table-column>
        <el-table-column label="反馈时间" prop="createTime" align="center" width="160">
          <template slot-scope="scope">
            {{ formatDate(scope.row.createTime) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="210px">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(scope.row)"
                       v-hasPermi="['drug:adverseReactionFeedback:view']">查看
            </el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['drug:adverseReactionFeedback:edit']">编辑
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['drug:adverseReactionFeedback:delete']">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <pagination
      v-show="totalFeedbacks > 0"
      :total="totalFeedbacks"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchFeedbacks"
    />

    <!-- 添加/编辑反馈对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="60%" @close="handleCloseDialog">
      <!-- 对话框内容 -->
      <div>
        <el-form :model="feedbackForm" label-width="120px" :rules="formRules" ref="feedbackForm">
          <el-form-item label="不良反应" prop="reactionId">
            <el-select v-model="feedbackForm.reactionId" :disabled="isReadOnly" placeholder="请选择不良反应记录" clearable filterable>
              <el-option
                v-for="reaction in reactionOptions"
                :key="reaction.id"
                :label="`#${reaction.id} - ${reaction.patientName} - ${reaction.drugName} (${reaction.severity})`"
                :value="reaction.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="反馈医生" prop="doctorId">
            <el-select v-model="feedbackForm.doctorId" :disabled="isReadOnly" placeholder="请选择反馈医生" clearable filterable>
              <el-option
                v-for="doctor in doctorOptions"
                :key="doctor.userId"
                :label="doctor.nickName"
                :value="doctor.userId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="反馈内容" prop="feedbackContent">
            <el-input v-model="feedbackForm.feedbackContent" :disabled="isReadOnly" type="textarea" 
                      :rows="6" placeholder="请输入详细的反馈内容，包括处理建议、注意事项等"></el-input>
          </el-form-item>
          <el-form-item label="反馈时间" v-if="isReadOnly">
            <span>{{ formatDate(feedbackForm.createTime) }}</span>
          </el-form-item>
        </el-form>
      </div>
      <!-- 对话框按钮 -->
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit" v-if="!isReadOnly">{{ dialogButtonText }}</el-button>
      </div>
    </el-dialog>

    <!-- 不良反应详情对话框 -->
    <el-dialog :visible.sync="reactionDetailVisible" title="不良反应详情" width="50%" @close="reactionDetailVisible = false">
      <div v-if="selectedReaction">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="记录ID">{{ selectedReaction.id }}</el-descriptions-item>
          <el-descriptions-item label="患者姓名">{{ selectedReaction.patientName }}</el-descriptions-item>
          <el-descriptions-item label="药品名称">{{ selectedReaction.drugName }}</el-descriptions-item>
          <el-descriptions-item label="严重程度">
            <el-tag :type="getSeverityType(selectedReaction.severity)">{{ selectedReaction.severity }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="发生时间" :span="2">{{ formatDate(selectedReaction.occurTime) }}</el-descriptions-item>
          <el-descriptions-item label="反应描述" :span="2">{{ selectedReaction.description }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listAllFeedbacks, 
  addFeedback, 
  updateFeedback, 
  deleteFeedback, 
  getFeedbackById
} from '@/api/patient/adverseReactionFeedback'
import {listAllAdverseReactions, getAdverseReactionById} from '@/api/patient/adverseReaction'
import {doctorList} from '@/api/system/user'

export default {
  data() {
    return {
      loading: false,
      feedbackList: [],
      reactionOptions: [], // 不良反应下拉选项
      doctorOptions: [], // 医生下拉选项
      reactionDetailsMap: {}, // 不良反应详情缓存
      dialogVisible: false,
      dialogTitle: '',
      dialogButtonText: '',
      totalFeedbacks: 0,
      isReadOnly: false, // 是否只读模式
      // 不良反应详情对话框
      reactionDetailVisible: false,
      selectedReaction: null,
      feedbackForm: {
        id: null,
        reactionId: null,
        doctorId: null,
        feedbackContent: ''
      },
      // 表单验证规则
      formRules: {
        reactionId: [
          { required: true, message: '请选择不良反应记录', trigger: 'change' }
        ],
        doctorId: [
          { required: true, message: '请选择反馈医生', trigger: 'change' }
        ],
        feedbackContent: [
          { required: true, message: '请输入反馈内容', trigger: 'blur' },
          { min: 10, message: '反馈内容至少10个字符', trigger: 'blur' },
          { max: 1000, message: '反馈内容不能超过1000个字符', trigger: 'blur' }
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
    // 先加载基础数据，再加载反馈列表
    Promise.all([
      this.fetchReactionOptions(),
      this.fetchDoctorOptions()
    ]).then(() => {
      this.fetchFeedbacks()
    })
  },
  methods: {
    fetchFeedbacks() {
      this.loading = true
      listAllFeedbacks(this.queryParams).then(response => {
        this.feedbackList = response.rows
        this.totalFeedbacks = response.total
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    fetchReactionOptions() {
      return listAllAdverseReactions({pageNum: 1, pageSize: 1000}).then(response => {
        this.reactionOptions = response.rows.map(reaction => ({
          id: reaction.id,
          patientName: reaction.patientName || `患者${reaction.patientId}`,
          drugName: reaction.drugName || `药品${reaction.drugId}`,
          severity: reaction.severity,
          description: reaction.description,
          occurTime: reaction.occurTime
        }))
        // 缓存详情信息
        this.reactionOptions.forEach(reaction => {
          this.reactionDetailsMap[reaction.id] = reaction
        })
      })
    },
    fetchDoctorOptions() {
      return doctorList({pageNum: 1, pageSize: 1000}).then(response => {
        this.doctorOptions = response.rows.map(doctor => ({
          userId: doctor.userId,
          nickName: doctor.nickName
        }))
      })
    },
    getDoctorName(doctorId) {
      if (!doctorId) return '-'
      const doctor = this.doctorOptions.find(d => d.userId === doctorId)
      return doctor ? doctor.nickName : (this.doctorOptions.length === 0 ? '加载中...' : `医生ID: ${doctorId}`)
    },
    getReactionInfo(reactionId, type) {
      if (!reactionId) return '-'
      const reaction = this.reactionDetailsMap[reactionId]
      if (!reaction) {
        // 如果缓存中没有数据，显示加载中或默认值
        switch (type) {
          case 'patient':
            return '加载中...'
          case 'drug':
            return '加载中...'
          default:
            return '-'
        }
      }
      
      switch (type) {
        case 'patient':
          return reaction.patientName || `患者${reactionId}`
        case 'drug':
          return reaction.drugName || `药品${reactionId}`
        default:
          return '-'
      }
    },
    getSeverityType(severity) {
      switch (severity) {
        case '轻微': return 'info'
        case '中度': return 'warning'
        case '严重': return 'danger'
        default: return 'info'
      }
    },
    handleViewReaction(reactionId) {
      getAdverseReactionById(reactionId).then(response => {
        this.selectedReaction = response.data
        this.reactionDetailVisible = true
      })
    },
    handleView(row) {
      this.dialogTitle = '查看反馈'
      this.dialogButtonText = '关闭'
      this.isReadOnly = true
      getFeedbackById(row.id).then(response => {
        this.feedbackForm = response.data
        this.dialogVisible = true
      })
    },
    handleAddFeedback() {
      this.isReadOnly = false
      this.dialogTitle = '新增反馈'
      this.dialogButtonText = '添加'
      this.dialogVisible = true
    },
    handleEdit(row) {
      this.isReadOnly = false
      this.dialogTitle = '编辑反馈'
      this.dialogButtonText = '更新'
      this.feedbackForm = Object.assign({}, row)
      this.dialogVisible = true
    },
    handleDelete(row) {
      this.$confirm('确认删除该反馈记录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteFeedback(row.id).then(() => {
          this.$message.success('删除成功')
          this.fetchFeedbacks()
        })
      })
    },
    handleCloseDialog() {
      this.dialogVisible = false
      this.clearForm()
      // 清除表单验证
      if (this.$refs.feedbackForm) {
        this.$refs.feedbackForm.clearValidate()
      }
    },
    clearForm() {
      this.feedbackForm = {
        id: null,
        reactionId: null,
        doctorId: null,
        feedbackContent: ''
      }
    },
    handleSubmit() {
      this.$refs.feedbackForm.validate((valid) => {
        if (valid) {
          if (this.dialogButtonText === '添加') {
            addFeedback(this.feedbackForm).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.clearForm()
              this.fetchFeedbacks()
            }).catch(error => {
              console.error('添加失败:', error)
              this.$message.error('添加失败，请检查数据')
            })
          } else if (this.dialogButtonText === '更新') {
            updateFeedback(this.feedbackForm).then(() => {
              this.$message.success('更新成功')
              this.dialogVisible = false
              this.clearForm()
              this.fetchFeedbacks()
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
    // 工具方法
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
