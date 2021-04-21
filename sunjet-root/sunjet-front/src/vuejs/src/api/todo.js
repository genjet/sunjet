import request from '@/utils/request'

export function addTodo(id, title) {
  return request({
    url: '/todo',
    method: 'post',
    params: {
      id,
      title
    }
  })
}

export function getTodos() {
  return request({
    url: '/todo',
    method: 'get'
  })
}

export function updateTodo(todo) {
  return request({
    url: '/todo/' + todo.id,
    method: 'patch',
    params: {
      title: todo.title,
      completed: todo.completed,
    }
  })
}

export function deleteTodo(id) {
  return request({
    url: '/todo/' + id,
    method: 'delete'
  })
}

export function checkAllTodo(completed) {
  return request({
    url: '/todo/checkAll',
    method: 'patch',
    params: {
      completed: completed,
    }
  })
}

export function clearCompleted() {
  return request({
    url: '/todo/deleteCompleted',
    method: 'patch'
  })
}
