import request from '@/utils/request'

export function getLeaves(data) {
  return request({
    url: '/leave',
    method: 'post',
    data
  })
}
