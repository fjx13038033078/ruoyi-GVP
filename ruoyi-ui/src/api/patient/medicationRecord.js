import request from '@/utils/request'

// 查询所有用药记录列表
export function listAllMedicationRecords(query) {
  return request({
    url: '/medicationRecord/listAll',
    method: 'get',
    params: query
  })
}

// 根据用药记录ID查询详细信息
export function getMedicationRecordById(recordId) {
  return request({
    url: '/medicationRecord/detail',
    method: 'get',
    params: { recordId }
  })
}

// 根据患者ID查询用药记录列表
export function getMedicationRecordsByPatientId(patientId) {
  return request({
    url: '/medicationRecord/listByPatientId',
    method: 'get',
    params: { patientId },
  })
}

// 根据药品ID查询用药记录列表
export function getMedicationRecordsByDrugId(drugId) {
  return request({
    url: '/medicationRecord/listByDrugId',
    method: 'get',
    params: { drugId }
  })
}

// 添加用药记录
export function addMedicationRecord(data) {
  return request({
    url: '/medicationRecord/add',
    method: 'post',
    data: data
  })
}

// 更新用药记录
export function updateMedicationRecord(data) {
  return request({
    url: '/medicationRecord/update',
    method: 'post',
    data: data
  })
}

// 删除用药记录
export function deleteMedicationRecord(recordId) {
  return request({
    url: '/medicationRecord/delete',
    method: 'get',
    params: { recordId }
  })
}
