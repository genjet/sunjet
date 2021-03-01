<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-input
          v-model="newTodo"
          placeholder="请输入内容"
          class="todo-input"
          @keyup.enter.native="addTodo"
        />
      </el-col>
      <el-col :span="18">
        <transition-group
          name="fade"
          enter-active-class="animated fadeInUp"
          leave-active-class="animated fadeOutDown"
        >
          <TodoItem
            v-for="(todo) in todosFiltered"
            :key="todo.id"
            :todo="todo"
            :checkAll="!anyRemaining"
          ></TodoItem>
        </transition-group>
      </el-col>
      <el-col :span="18">
        <div class="extra-container">
          <TodoCheckAll></TodoCheckAll>
          <TodoItemsRemaining></TodoItemsRemaining>
        </div>
      </el-col>
      <el-col :span="18">
        <div class="extra-container">
          <TodoFiltered></TodoFiltered>
          <TodoClearCompleted></TodoClearCompleted>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import TodoItem from "@/components/todo/TodoItem.vue";
import TodoFiltered from "@/components/todo/TodoFiltered.vue";
import TodoClearCompleted from "@/components/todo/TodoClearCompleted.vue";
import TodoCheckAll from "@/components/todo/TodoCheckAll.vue";
import TodoItemsRemaining from "@/components/todo/TodoItemsRemaining.vue";

export default {
  name: "todo-list",
  components: {
    TodoItem,
    TodoFiltered,
    TodoClearCompleted,
    TodoCheckAll,
    TodoItemsRemaining
  },
  data() {
    return {
      newTodo: ""
    };
  },
  created() {
    this.$store.dispatch("todoStore/getTodos");
  },
  computed: {
    anyRemaining() {
      return this.$store.getters.anyRemaining;
    },
    todosFiltered() {
      return this.$store.getters.todosFiltered;
    }
  },
  methods: {
    addTodo() {
      if (this.newTodo.trim().length == 0) {
        return;
      }
      this.$store.dispatch("todoStore/addTodo", {
        id: this.$store.getters.getLastTodoId + 1,
        title: this.newTodo
      });
      this.newTodo = "";
    }
  }
};
</script>

<style lang="scss">
@import url("https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css");

.todo-input {
  width: 100%;
  padding: 10px 18px;
  font-size: 18px;
  margin-bottom: 16px;

  &:focus {
    outline: 0;
  }
}

.todo-item {
  margin-bottom: 12px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  animation-duration: 0.3s;
}

.remove-item {
  cursor: pointer;
  margin-left: 14px;
  &:hover {
    color: black;
  }
}

.todo-item-left {
  // later
  display: flex;
  align-items: center;
}

.todo-item-label {
  padding: 10px;
  border: 1px solid white;
  margin-left: 12px;
}

.todo-item-edit {
  font-size: 24px;
  color: #2c3e50;
  margin-left: 12px;
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc; //override defaults
  font-family: "Avenir", Helvetica, Arial, sans-serif;

  &:focus {
    outline: none;
  }
}

.completed {
  text-decoration: line-through;
  color: grey;
}

.extra-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 16px;
  border-top: 1px solid lightgrey;
  padding-top: 14px;
  margin-bottom: 14px;
}

// CSS Transitions
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>

