import request from '@/utils/request'

// 查询所有说明书列表
export function listAllDrugInstructions(query) {
  return request({
    url: '/drugInstruction/listAll',
    method: 'get',
    params: query
  })
}

// 根据说明书ID获取说明书详情
export function getDrugInstructionById(instructionId) {
  return request({
    url: '/drugInstruction/detail',
    method: 'get',
    params: { instructionId }
  })
}

// 根据药品ID获取说明书列表
export function getDrugInstructionsByDrugId(drugId) {
  return request({
    url: '/drugInstruction/listByDrugId',
    method: 'get',
    params: { drugId }
  })
}

// 添加说明书
export function addDrugInstruction(data) {
  return request({
    url: '/drugInstruction/add',
    method: 'post',
    data: data
  })
}

// 更新说明书
export function updateDrugInstruction(data) {
  return request({
    url: '/drugInstruction/update',
    method: 'post',
    data: data
  })
}

// 删除说明书
export function deleteDrugInstruction(instructionId) {
  return request({
    url: '/drugInstruction/delete',
    method: 'get',
    params: { instructionId }
  })
}
