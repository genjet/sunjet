import {
    addTodo,
    getTodos,
    deleteTodo,
    updateTodo,
    checkAllTodo,
    clearCompleted
} from '@/api/todo'

const state = {
    filter: "all",
    todos: []
}
const mutations = {
    DELETE_TODO(state, id) {
        const index = state.todos.findIndex(
            item => item.id == id
        );
        state.todos.splice(index, 1);
    },
    UPDATE_TODO(state, todo) {
        const index = state.todos.findIndex(
            item => item.id == todo.id
        );
        state.todos.splice(index, 1, todo);
    },
    updateFilter(state, filter) {
        state.filter = filter
    },
    CLEAR_COMPLETED(state) {
        state.todos = state.todos.filter(
            todo => !todo.completed
        );
    },
    CHECK_TODO(state, checked) {
        state.todos.forEach(
            todo => (todo.completed = checked)
        );
    },
    ADD_TODO(state, todo) {
        state.todos.push({
            id: todo.id,
            title: todo.title,
            completed: false,
            editing: false
        });
    },
    GET_TODOS(state, todos) {
        state.todos = todos
    }
}


const actions = {

    // deleteTodo(context, id) {
    //     // setTimeout(() => {
    //     context.commit('DELETE_TODO', id)
    //     // }, 100)
    // },
    // updateTodo(context, data) {
    //     context.commit('UPDATE_TODO', data)
    // },
    updateFilter(context, filter) {
        context.commit('updateFilter', filter)
    },
    // clearCompleted(context) {
    //     context.commit('CLEAR_COMPLETED')
    // },
    // checkAll(context, checked) {
    //     context.commit('CHECK_TODO', checked)
    // },
    // addTodo(context, todo) {
    //     context.commit('ADD_TODO', todo)
    // },
    addTodo({
            commit
        },
        todo
    ) {
        return new Promise((resolve, reject) => {
            addTodo(todo.id, todo.title).then(response => {
                const {
                    data
                } = response
                console.info(response)
                commit('ADD_TODO', data)
            }).catch(error => {
                reject(error)
            })
        })
    },
    getTodos({
        commit
    }) {
        return new Promise((resolve, reject) => {
            getTodos().then(response => {
                const {
                    data
                } = response
                commit('GET_TODOS', data)
            }).catch(error => {
                reject(error)
            })
        })
    },

    deleteTodo({
            commit
        },
        id
    ) {
        return new Promise((resolve, reject) => {
            deleteTodo(id).then(() => {
                commit('DELETE_TODO', id)
            }).catch(error => {
                reject(error)
            })
        })
    },

    updateTodo({
            commit
        },
        todo
    ) {
        return new Promise((resolve, reject) => {
            updateTodo(todo).then((response) => {
                const {
                    data
                } = response
                commit('UPDATE_TODO', data)
            }).catch(error => {
                reject(error)
            })
        })
    },

    checkAll({
        commit
    }, completed) {
        return new Promise((resolve, reject) => {
            checkAllTodo(completed).then(() => {
                commit('CHECK_TODO', completed)
            }).catch(error => {
                reject(error)
            })
        })
    },

    clearCompleted({
        commit
    }) {
        return new Promise((resolve, reject) => {
            clearCompleted().then(() => {
                commit('CLEAR_COMPLETED')
            }).catch(error => {
                reject(error)
            })
        })
    },


}


export default {
    namespaced: true,
    state,
    mutations,
    actions
}
