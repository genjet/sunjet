<template>
  <div>{{ remaining }} items left</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Todo-Items-Remaining",
  computed: {
    remaining() {
      return this.$store.getters.remaining;
    }
  }
};
</script>
