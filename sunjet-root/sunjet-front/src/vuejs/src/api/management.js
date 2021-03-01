import request from '@/utils/request'

export function getUsers() {
  return request({
    url: '/management/user',
    method: 'get'
  })
}

export function getDeps() {
  return request({
    url: '/management/deps',
    method: 'get'
  })
}

export function addUser(data) {
  return request({
    url: '/management/user',
    method: 'post',
    data
  })
}

export function editUser(data) {
  return request({
    url: '/management/user',
    method: 'patch',
    data
  })
}

export function deleteUser(id) {
  return request({
    url: '/management/user/' + id,
    method: 'delete'
  })
}

export function getRoles() {
  return request({
    url: '/management/role',
    method: 'get'
  })
}

export function addRole(data) {
  return request({
    url: '/management/role',
    method: 'post',
    data
  })
}

export function editRole(data) {
  return request({
    url: '/management/role',
    method: 'patch',
    data
  })
}

export function getRoleOptions() {
  return request({
    url: '/management/roleOptions',
    method: 'get'
  })
}

export function deleteRole(id) {
  return request({
    url: '/management/role/' + id,
    method: 'delete'
  })
}
