import request from '@/utils/request'

// 查询所有不良反应反馈记录
export function listAllFeedbacks(query) {
  return request({
    url: '/adverseReactionFeedback/listAll',
    method: 'get',
    params: query
  })
}

// 根据反馈ID查询详情
export function getFeedbackById(id) {
  return request({
    url: '/adverseReactionFeedback/detail',
    method: 'get',
    params: { id }
  })
}

// 根据不良反应ID查询反馈列表
export function getFeedbacksByReactionId(reactionId) {
  return request({
    url: '/adverseReactionFeedback/listByReactionId',
    method: 'get',
    params: { reactionId }
  })
}

// 添加反馈记录
export function addFeedback(data) {
  return request({
    url: '/adverseReactionFeedback/add',
    method: 'post',
    data: data
  })
}

// 更新反馈记录
export function updateFeedback(data) {
  return request({
    url: '/adverseReactionFeedback/update',
    method: 'post',
    data: data
  })
}

// 删除反馈记录
export function deleteFeedback(id) {
  return request({
    url: '/adverseReactionFeedback/delete',
    method: 'get',
    params: { id }
  })
}
