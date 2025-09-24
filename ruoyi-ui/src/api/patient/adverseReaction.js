import request from '@/utils/request'

// 查询所有不良反应记录
export function listAllAdverseReactions(query) {
  return request({
    url: '/adverseReaction/listAll',
    method: 'get',
    params: query
  })
}

// 根据不良反应ID查询记录详情
export function getAdverseReactionById(reactionId) {
  return request({
    url: '/adverseReaction/detail',
    method: 'get',
    params: { id: reactionId }
  })
}

// 根据患者ID查询不良反应列表
export function getAdverseReactionsByPatientId(patientId) {
  return request({
    url: '/adverseReaction/listByPatientId',
    method: 'get',
    params: { patientId }
  })
}

// 根据药品ID查询不良反应列表
export function getAdverseReactionsByDrugId(drugId) {
  return request({
    url: '/adverseReaction/listByDrugId',
    method: 'get',
    params: { drugId }
  })
}

// 添加不良反应记录
export function addAdverseReaction(data) {
  return request({
    url: '/adverseReaction/add',
    method: 'post',
    data: data
  })
}

// 更新不良反应记录
export function updateAdverseReaction(data) {
  return request({
    url: '/adverseReaction/update',
    method: 'post',
    data: data
  })
}

// 删除不良反应记录
export function deleteAdverseReaction(reactionId) {
  return request({
    url: '/adverseReaction/delete',
    method: 'get',
    params: { id: reactionId }
  })
}
