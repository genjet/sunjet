import request from '@/utils/request'

export function getStatus() {
  return request({
    url: '/auth/getLeaveType',
    method: 'get'
  })
}

export function getAllOptions() {
  return request({
    url: '/auth/getAllOptions',
    method: 'get'
  })
}

export function getAllAuthoritys() {
  return request({
    url: '/auth/getAllAuthoritys',
    method: 'get'
  })
}
