import request from '@/utils/request'

// 查询所有患者列表
export function listAllPatients(query) {
  return request({
    url: '/patient/listAll',
    method: 'get',
    params: query
  })
}

// 查询患者详细信息
export function getPatientById(patientId) {
  return request({
    url: '/patient/detail',
    method: 'get',
    params: { patientId }
  })
}

// 添加患者
export function addPatient(data) {
  return request({
    url: '/patient/add',
    method: 'post',
    data: data
  })
}

// 更新患者信息
export function updatePatient(data) {
  return request({
    url: '/patient/update',
    method: 'post',
    data: data
  })
}

// 删除患者
export function deletePatient(patientId) {
  return request({
    url: '/patient/delete',
    method: 'get',
    params: { patientId }
  })
}
