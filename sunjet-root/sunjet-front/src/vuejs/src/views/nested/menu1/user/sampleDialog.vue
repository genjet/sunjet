<template>
  <div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <slot></slot>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">{{$I18N(`__common.cancel`)}}</el-button>
        <el-button type="primary" @click="createOrUpdate">{{$I18N(`__common.define`)}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "sampleDialog",
  props: {
    dialogStatus: {
      type: String,
      required: true
    },
    dialogFormVisible: {
      type: Boolean,
      required: true,
      default:false
    }
  },
   data() {
    return {
      dialogVisible: false,
      textMap: {
        update: this.$I18N("__common.edit"),
        create: this.$I18N("__common.add")
      },
    };
  },
  methods: {
    cancel() {
      this.$emit("cancel");
    },
    createOrUpdate() {
      this.$emit("createOrUpdate");
    },
    handleClose() {
      this.$confirm(this.$I18N("__common.confirmClose"))
        .then(() => {
           this.$emit("handleClose", false);
        })
        .catch(()=>{
          this.$emit("handleClose", true);
        });
    }
  }
};
</script>
<style lang="scss">
</style>
