<template>
  <div>
    <el-button :type="this.miStatusColor(filter, 'all')" @click="changeFilter('all')">All</el-button>
    <el-button :type="this.miStatusColor(filter, 'active')" @click="changeFilter('active')">active</el-button>
    <el-button
      :type="miStatusColor(filter,'completed')"
      @click="changeFilter('completed')"
    >completed</el-button>
  </div>
</template>
<script>
export default {
  name: "todo-filtered",
  data() {
    return {
      filter: "all"
    };
  },
  methods: {
    miStatusColor(filter, str) {
      if (filter == str) {
        return "success";
      } else {
        return "primary";
      }
    },
    changeFilter(filter) {
      this.filter = filter;
      this.$store.dispatch("todoStore/updateFilter", filter);
    }
  }
};
</script>
