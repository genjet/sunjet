const getters = {
  sidebar: (state) => state.app.sidebar,
  device: (state) => state.app.device,
  options: (state) => state.app.options,
  cachedViews: state => state.tagsView.cachedViews,
  token: (state) => state.user.token,
  avatar: (state) => state.user.avatar,
  name: (state) => state.user.name,
  account: (state) => state.user.account,
  users: (state) => state.management.users,
  roles: (state) => state.management.roles,
  roleOptions: (state) => state.management.roleOptions,
  deps: (state) => state.management.deps,
  leaves: (state) => state.leave.leaves,
  authoritys: (state) => state.app.authoritys,

  remaining(state) {
    return state.todoStore.todos.filter((todo) => !todo.completed).length;
  },
  anyRemaining(state, getters) {
    return getters.remaining != 0;
  },
  todosFiltered(state) {
    if (state.todoStore.filter === "all") {
      return state.todoStore.todos;
    } else if (state.todoStore.filter === "active") {
      return state.todoStore.todos.filter((todo) => !todo.completed);
    } else if (state.todoStore.filter === "completed") {
      return state.todoStore.todos.filter((todo) => todo.completed);
    }

    return state.todoStore.todos;
  },
  getLastTodoId(state) {
    const highest = state.todoStore.todos.reduce((a, b) => (a.id > b.id ? a : b));
    return highest.id;
  },
  showClearCompletedButton(state) {
    return state.todoStore.todos.filter((todo) => todo.completed).length > 0;
  },
};
export default getters;
