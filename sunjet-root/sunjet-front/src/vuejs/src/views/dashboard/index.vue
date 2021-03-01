<template>
  <div class="dashboard-container">
    <svg-icon icon-class="dashboard" />
    <svg-icon icon-class="Centaur" />
    <div class="dashboard-text" @click="test">{{$I18N(`__user.name`)}}:{{name}}</div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Dashboard",
  computed: {
    ...mapGetters(["name"])
  },
  created() {
    //this.test();
    this.$message.success(this.$I18N(`__user.name`))
  },
  methods: {
    // test() {
    //   const test2 = e => {
    //   };
    //   test2();
    // }
  }
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
