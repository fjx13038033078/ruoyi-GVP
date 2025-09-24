import request from '@/utils/request'

// 查询所有服药打卡记录
export function listAllMedicationLogs(query) {
  return request({
    url: '/medicationLog/listAll',
    method: 'get',
    params: query
  })
}

// 根据打卡ID查询记录详情
export function getMedicationLogById(logId) {
  return request({
    url: '/medicationLog/detail',
    method: 'get',
    params: { logId }
  })
}

// 根据用药记录ID查询打卡记录列表
export function getMedicationLogsByRecordId(recordId) {
  return request({
    url: '/medicationLog/listByRecordId',
    method: 'get',
    params: { recordId }
  })
}

// 添加打卡记录
export function addMedicationLog(data) {
  return request({
    url: '/medicationLog/add',
    method: 'post',
    data: data
  })
}

// 更新打卡记录
export function updateMedicationLog(data) {
  return request({
    url: '/medicationLog/update',
    method: 'post',
    data: data
  })
}

// 删除打卡记录
export function deleteMedicationLog(logId) {
  return request({
    url: '/medicationLog/delete',
    method: 'get',
    params: { logId }
  })
}
