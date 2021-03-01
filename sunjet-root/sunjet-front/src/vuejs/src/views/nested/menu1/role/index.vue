<template>
  <div class="query-page">
    <div class="query-area">
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        size="small"
        @click="handleCreate"
      >{{$I18N(`__common.add`)}}</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="success" round
        icon="el-icon-edit"
        size="small"
        @click="handleEditAuthority"
      >{{$I18N(`__role.editAuthority`)}}</el-button>
      <sample-table
        :resultDatas="getRoles"
        :columns="getColumns"
        @handleUpdate="handleUpdate"
        @handleDelete="handleDelete"
        :pageSize= 10
      ></sample-table>
      <sample-dialog
        :dialogStatus="dialogStatus"
        :dialogFormVisible="dialogFormVisible"
        @handleClose="handleClose"
        @cancel="cancel"
        @createOrUpdate="createOrUpdate"
      >
        <edit-form ref="refEditForm" :resultData="tempData" :columns="getColumns" :rules="rules" :checkList="checkList"></edit-form>
      </sample-dialog>
    </div>
  </div>
</template>

<script>
import sampleTable from "../user/sampleTable.vue";
import sampleDialog from "../user/sampleDialog.vue";
import editForm from "../user/editForm.vue";
export default {
  name: "roleVue",
  components: {
    sampleTable,
    sampleDialog,
    editForm
  },
  created() {
    this.$store.dispatch("management/getRoles");
    this.$store.dispatch("app/getAllAuthoritys");
  },
  computed: {
    getRoles() {
      const rolesDb = this.$store.getters.roles;
      let authorityArray = [];
      let authorityArray1 = [];
      rolesDb.forEach(element => {
        if(undefined !== element.authorityVo && element.authorityVo.length > 0){
          element.authorityVo.forEach(e => {
            authorityArray.push(e.authorityCode);
            authorityArray1.push(e.authorityName);
          });
          console.log(authorityArray);
          element.authority = authorityArray;
          element.authority1 = authorityArray1;
          authorityArray = [];
          authorityArray1 = [];
        }else{
          element.authority = [];
        }
      });
      console.log(rolesDb);
      return rolesDb;
    },
    getColumns() {
      this.columns.forEach(e => {
        if (e.prop === "authority") {
          e.operate.dataList = this.$store.getters.authoritys;
        }
      });
      return this.columns;
    },
  },
  data() {
    return {
      tempData: {},
      checkList:['A'],
      rules: {},
      dialogStatus: "",
      dialogFormVisible: false,
      columns: [
        {
          prop: "oid",
          label: '',
          show:false,
          operate: { show: false }
        },
        {
          prop: "roleCode",
          label: this.$I18N(`__role.roleCode`),
          operate: { name: "input" }
        },
        {
          prop: "roleName",
          label: this.$I18N(`__role.roleName`),
          operate: { name: "input" }
        },
        {
          prop: "authority",
          label: this.$I18N(`__role.authority`),
          width: 500,
          show:false,
          operate: { name: "block", dataList: [] }
        },
        {
          prop: "authority1",
          label: this.$I18N(`__role.authority`),
          width: 500,
          operate: {show: false }
        },
        {
          prop: "btn",
          label: this.$I18N("__common.btn"),
          option: "edit-button",
          operate: { show: false }
        },
        {
          prop: "btn",
          label: this.$I18N("__common.btn"),
          option: "delete-button",
          fixed: "right",
          operate: { show: false }
        }
      ],
    }
  },
  methods: {
    handleEditAuthority() {
      console.log("object");
    },
    handleCreate() {
      this.resetTemp();
      this.dialogStatus = "create";
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        this.$refs["refEditForm"].$refs["dataForm"].clearValidate();
      });
    },
    handleDelete(row) {
      this.$confirm(this.$I18N("__common.confirmDelete"))
        .then(() => {
          this.$store.dispatch("management/deleteRole", row.oid);
        })
        .catch();
    },
    handleUpdate(row) {
      this.dialogStatus = "update";
      this.tempData = Object.assign({}, row); // copy obj
      this.dialogFormVisible = true;

    },
    resetTemp() {
      console.log("=======================================");
      this.tempData = { authority:[] };
    },
    handleClose(flag) {
      this.dialogFormVisible = flag;
    },
    cancel() {
      this.dialogFormVisible = false;
      this.dialogStatus = "";
      this.resetTemp();
    },
    createOrUpdate() {
      this.dialogStatus === "create" ? this.createData() : this.updateData();
    },
    createData() {
      this.$refs["refEditForm"].$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$store.dispatch("management/addRole", this.tempData);
          this.dialogFormVisible = false;
        }
      });
    },
    updateData() {
      this.$refs["refEditForm"].$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$store.dispatch("management/editRole", this.tempData);
          this.dialogFormVisible = false;
        }
      });
    },
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/myStyle.scss";
@import "@/styles/query-page.scss";
</style>
