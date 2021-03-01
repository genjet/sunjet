<template>
  <div>
    <label>
      <el-checkbox :value="!anyRemaining" @change="checkAllTodos">Check All</el-checkbox>
    </label>
  </div>
</template>

<script>
export default {
  name: "Todo-Check-All",
  computed: {
    anyRemaining() {
      return this.$store.getters.anyRemaining;
    }
  },
  methods: {
    checkAllTodos() {
      this.$store.dispatch("todoStore/checkAll", this.anyRemaining);
    }
  }
};
</script>
