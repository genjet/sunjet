import {
  getLeaves
} from '@/api/leave'

import {LeaveType} from '@/typings/Enums'

const state = {
  leaves: [{}]
}

const mutations = {
  GET_LEAVES(state, data) {
    if(data.length > 0){

      data.forEach(element => {
        element.leaveType = LeaveType[element.leaveType]
      });

    }
    state.leaves = data;


  }
}

const actions = {
  async getLeaves({commit, reject}, searchForm) {
    await getLeaves(searchForm).then(response => {
      const {data} = response
      commit('GET_LEAVES', data)
    }).catch(error => {
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
