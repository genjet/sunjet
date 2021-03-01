<template>
  <div>
    <transition name="fade">
      <el-button
        icon="el-icon-delete"
        v-if="showClearCompletedButton"
        @click="clearCompleted"
      >Clear Completed</el-button>
    </transition>
  </div>
</template>
<script>
export default {
  name: "Todo-Clear-Completed",
  computed: {
    showClearCompletedButton() {
      return this.$store.getters.showClearCompletedButton;
    }
  },
  methods: {
    clearCompleted() {
      // this.todos = this.todos.filter(todo => !todo.completed);
      this.$store.dispatch("todoStore/clearCompleted");
    }
  }
};
</script>
