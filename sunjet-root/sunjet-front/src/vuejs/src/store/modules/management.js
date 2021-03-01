import {
  getUsers,
  addUser,
  editUser,
  deleteUser,
  getDeps,
  getRoles,
  addRole,
  editRole,
  getRoleOptions,
  deleteRole,
} from '@/api/management'

import { Message } from 'element-ui'
// import * as altMsg from '../altMsg'
import lang from '@/lang'

const state = {
  users: [{}],
  deps:[],
  roles:[{}],
  roleOptions:[],
}

const mutations = {
  GET_USERS(state, users) {
    state.users = users;
  },
  GET_DEPS(state, deps) {
    state.deps = deps;
  },
  ADD_USER(state, user) {
    state.users.push(user);
  },
  EDIT_USER(state, user) {
    const index = state.users.findIndex(
      item => item.oid == user.oid
    );
    state.users.splice(index, 1, user);
  },
  DELETE_USER(state, id) {
    const index = state.users.findIndex(
        item => item.id == id
    );
    state.users.splice(index, 1);
  },
  GET_ROLES(state, roles) {
    state.roles = roles;
  },
  ADD_ROLE(state, role) {
    state.roles.push(role);
  },
  EDIT_ROLE(state, role) {
    const index = state.roles.findIndex(
      item => item.oid == role.oid
    );
    state.roles.splice(index, 1, role);
  },
  GET_ROLE_OPTIONS(state, roleOptions) {
    state.roleOptions = roleOptions;
  },
  DELETE_ROLE(state, id) {
    const index = state.roles.findIndex(
        item => item.id == id
    );
    state.roles.splice(index, 1);
  },
}

const actions = {
  getUsers({commit, reject}) {
    getUsers().then(response => {
      const {data} = response
      commit('GET_USERS', data)
    }).catch(error => {
      reject(error)
    })
  },

  addUser({commit, reject},
    user) {
    addUser(user).then(response => {
      const {data} = response
      commit('ADD_USER', data)
      Message.success(lang.I18N(`__common.add`,`__common.sucess`))
    }).catch(error => {
      reject(error)
    })
  },

  editUser({commit, reject},
    user) {
      editUser(user).then(response => {
      const {data} = response
      commit('EDIT_USER', data)
      Message.success(lang.I18N(`__common.edit`,`__common.sucess`))
    }).catch(error => {
      reject(error)
    })
  },

  deleteUser({commit, reject},
    id) {
      deleteUser(id).then(response => {
      const {message} = response
      commit('DELETE_USER', id)
      Message.warning(message)
    }).catch(error => {
      reject(error)
    })
  },

  getDeps({commit, reject}) {
    getDeps().then(response => {
      const {data} = response
      commit('GET_DEPS', data)
    }).catch(error => {
      reject(error)
    })
  },

  getRoles({commit, reject}) {
    getRoles().then(response => {
      const {data} = response
      commit('GET_ROLES', data)
    }).catch(error => {
      reject(error)
    })
  },

  addRole({commit, reject},
    role) {
      addRole(role).then(response => {
      const {data} = response
      commit('ADD_ROLE', data)
      Message.success(lang.I18N(`__common.add`,`__common.sucess`))
    }).catch(error => {
      reject(error)
    })
  },

  editRole({commit, reject},
    user) {
      editRole(user).then(response => {
      const {data} = response
      commit('EDIT_ROLE', data)
      Message.success(lang.I18N(`__common.edit`,`__common.sucess`))
    }).catch(error => {
      reject(error)
    })
  },

  getRoleOptions({commit, reject}) {
    getRoleOptions().then(response => {
      const {data} = response
      commit('GET_ROLE_OPTIONS', data)
    }).catch(error => {
      reject(error)
    })
  },

  deleteRole({commit, reject},
    id) {
      deleteRole(id).then(response => {
      const {message} = response
      commit('DELETE_ROLE', id)
      Message.warning(message)
    }).catch(error => {
      Message.error(error);
      reject(error)
    })
  },

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
