import {
  login,
  logout,
  getInfo,
} from "@/api/user";
import { getToken, setToken, removeToken } from "@/utils/auth";
import { resetRouter } from "@/router";
import { Message } from 'element-ui'

const getDefaultState = () => {
  return {
    account: "",
    token: getToken(),
    name: "",
    avatar: "",
  };
};

const state = getDefaultState();

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState());
  },
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_ACCOUNT: (state, account) => {
    state.account = account;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  },
};

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo;
    return new Promise((resolve, reject) => {
      login({
        username: username.trim(),
        password: password,
      })
        .then((response) => {
          //const data = response;
          commit("SET_TOKEN", response.accessToken);
          setToken(response.accessToken);
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token)
        .then((response) => {
          const data = response;
          if (!response) {
            reject("Verification failed, please Login again.");
          }

          const { username, account, avatar } = data;
          commit("SET_NAME", username);
          commit("SET_ACCOUNT", account);
          commit("SET_AVATAR", avatar);
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then((response) => {
        removeToken(); // must remove  token  first
        resetRouter();
        commit("RESET_STATE");
        Message.success(response.message)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise((resolve) => {
      removeToken(); // must remove  token  first
      commit("RESET_STATE");
      resolve();
    });
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
