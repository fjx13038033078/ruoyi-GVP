import request from '@/utils/request'

// 查询所有药品列表
export function listAllDrugs(query) {
  return request({
    url: '/drug/listAll',
    method: 'get',
    params: query
  })
}

// 根据药品ID获取药品详情
export function getDrugById(drugId) {
  return request({
    url: '/drug/detail',
    method: 'get',
    params: { drugId }
  })
}

// 添加药品
export function addDrug(data) {
  return request({
    url: '/drug/add',
    method: 'post',
    data: data
  })
}

// 更新药品信息
export function updateDrug(data) {
  return request({
    url: '/drug/update',
    method: 'post',
    data: data
  })
}

// 删除药品
export function deleteDrug(drugId) {
  return request({
    url: '/drug/delete',
    method: 'get',
    params: { drugId }
  })
}

