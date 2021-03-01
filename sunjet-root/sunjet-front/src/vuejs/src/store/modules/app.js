import Cookies from 'js-cookie'
import {
  getAllOptions,
  getAllAuthoritys,
} from '@/api/common'

const state = {
  sidebar: {
    opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
    withoutAnimation: false
  },
  device: 'desktop',
  options:[],
  leaveType:[],
  authoritys:[],
}

const mutations = {
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened
    state.sidebar.withoutAnimation = false
    if (state.sidebar.opened) {
      Cookies.set('sidebarStatus', 1)
    } else {
      Cookies.set('sidebarStatus', 0)
    }
  },
  CLOSE_SIDEBAR: (state, withoutAnimation) => {
    Cookies.set('sidebarStatus', 0)
    state.sidebar.opened = false
    state.sidebar.withoutAnimation = withoutAnimation
  },
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  },
  GET_ALL_OPTIONS: (state, options) => {
    state.options = options
    state.leaveType = options['leaveType']
  },
  GET_ALL_AUTHORITYS: (state, authoritys) => {
    let newAuthoritys = authoritys.map(function(obj) {
      if( obj.children && obj.children.length > 0){
        return {
          value: obj.value,
          label: obj.label,
          children: obj.children
        }
      }else{
        return {
          value: obj.value,
          label: obj.label
        }
      }
    });
    state.authoritys = newAuthoritys;
  },
}

const actions = {
  toggleSideBar({ commit }) {
    commit('TOGGLE_SIDEBAR')
  },
  closeSideBar({ commit }, { withoutAnimation }) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  toggleDevice({ commit }, device) {
    commit('TOGGLE_DEVICE', device)
  },
  getAllOptions({commit, reject}) {
    getAllOptions().then(response => {
      console.log(response.data);
      commit('GET_ALL_OPTIONS', response.data)
    }).catch(error => {
      console.log(error);
      reject(error)
    })
  },
  getAllAuthoritys({commit, reject}) {
    getAllAuthoritys().then(response => {
      console.log(response.data);
      commit('GET_ALL_AUTHORITYS', response.data)
    }).catch(error => {
      console.log(error);
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
