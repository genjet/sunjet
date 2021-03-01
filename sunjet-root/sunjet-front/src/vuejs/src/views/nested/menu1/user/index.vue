<template>
  <div class="query-page">
    <div class="query-area">
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >{{$I18N(`__common.add`)}}</el-button>
      <sample-table
        :resultDatas="users"
        :columns="getColumns"
        :pageSize= 5
        @handleUpdate="handleUpdate"
        @handleDelete="handleDelete"
      ></sample-table>
      <sample-dialog
        :dialogStatus="dialogStatus"
        :dialogFormVisible="dialogFormVisible"
        @handleClose="handleClose"
        @cancel="cancel"
        @createOrUpdate="createOrUpdate"
      >
        <edit-form ref="refEditForm" :resultData="tempData" :columns="getColumns" :rules="rules"></edit-form>
      </sample-dialog>
    </div>
  </div>
</template>

<script>
import sampleTable from "./sampleTable.vue";
import editForm from "./editForm.vue";
import sampleDialog from "./sampleDialog.vue";

// // arr to obj, such as { CN : "China", US : "USA"
export default {
  name: "ComplexTable",
  components: {
    sampleTable,
    sampleDialog,
    editForm
  },
  data() {
    return {
      tempData: {},
      rules: {},
      dialogFormVisible: false,
      columns: [
        {
          prop: "oid",
          label: '',
          show:false,
          operate: { show: false }
        },
        {
          prop: "empId",
          label: this.$I18N("__user.empId"),
          sort: true,
          operate: { show: false }
        },
        {
          prop: "account",
          label: this.$I18N("__user.account"),
          operate: {
            name: "input",
            rule: {
              required: true,
              message: "account is required",
              trigger: "blur"
            }
          }
        },
        {
          prop: "name",
          label: this.$I18N("__user.name"),
          operate: {
            name: "input",
            rule: {
              required: true,
              message: "name is required",
              trigger: "blur"
            }
          }
        },
        {
          prop: "pwd",
          label: this.$I18N("__user.pwd"),
          operate: {
            name: "input",
            rule: {
              required: true,
              pattern: /^(\w){6,16}$/,
              message: "请设置6-16位字母、数字组合"
            }
          }
        },
        {
          prop: "nickName",
          label: this.$I18N("__user.nickName"),
          operate: { name: "input" }
        },
        {
          prop: "avatar",
          label: this.$I18N("__user.avatar"),
          option: "icon",
          operate:  { name: "select", dataList: [] }
        },
        {
          prop: "enabled",
          label: this.$I18N("__user.enabled"),
          option: "switch",
          operate: { name: "switch" }
        },
        {
          prop: "dep",
          label: this.$I18N("__user.dep"),
          replaceShow: true,
          operate: { name: "select", dataList: [] }
        },
        {
          prop: "arrivalDay",
          label: this.$I18N("__user.arrivalDay"),
          operate: {
            name: "date",
            rule: {
              required: true,
              message: "timestamp is required",
              trigger: "change"
            }
          }
        },
        {
          prop: "roles",
          label: this.$I18N("__user.role"),
          replaceShow: true,
          operate: { name: "select", dataList: [], multiple: true }
        },
        {
          prop: "btn",
          label: this.$I18N("__common.btn"),
          option: "edit-button",
          fixed: "right",
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
      resultDatas: [{}],
      // listQuery: {
      //   page: 1,
      //   limit: 20,
      //   importance: undefined,
      //   title: undefined,
      //   type: undefined,
      //   sort: "+empId"
      // },

      dialogStatus: ""
    };
  },
  created() {
    this.$store.dispatch("management/getUsers");
    this.$store.dispatch("management/getDeps")
    this.$store.dispatch("management/getRoleOptions")
  },
  mounted() {
    const tempRule = {};
    this.columns.forEach(e => {
      if (e.operate.rule) {
        tempRule[e.prop] = e.operate.rule;
      }
    });
    this.rules = tempRule;
  },
  computed: {
    users() {
      const localUsers = this.$store.getters.users;
      return localUsers;
    },
    // roleOptions(){
    //   return this.$store.getters.roleOptions;
    // },
    getColumns() {
      this.columns.forEach(e => {
        if (e.prop === "dep") {
          e.operate.dataList = this.$store.getters.deps;
        }
        if (e.prop === "roles") {
          e.operate.dataList = this.$store.getters.roleOptions;
        }
        if (e.prop === "avatar") {
          e.operate.dataList = ['Villager','Sorceress-Witch', 'my-sysmenu', 'Adventure-Map'];
        }
      });
      return this.columns;
    }
  },
  methods: {
    createOrUpdate() {
      this.dialogStatus === "create" ? this.createData() : this.updateData();
    },
    cancel() {
      this.dialogFormVisible = false;
      this.dialogStatus = "";
      this.resetTemp();
    },
    resetTemp() {
      this.tempData = { arrivalDay: new Date() };
    },
    handleClose(flag) {
      this.dialogFormVisible = flag;
    },
    handleCreate() {
      this.resetTemp();
      this.dialogStatus = "create";
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        this.$refs["refEditForm"].$refs["dataForm"].clearValidate();
      });
    },
    createData() {
      this.$refs["refEditForm"].$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$store.dispatch("management/addUser", this.tempData);

          this.dialogFormVisible = false;
        }
      });
    },
    handleUpdate(row) {
      this.dialogStatus = "update";
      this.tempData = Object.assign({}, row); // copy obj

      const rolesKey = [];
      this.tempData.roles.forEach(t => {
        this.$store.getters.roleOptions.forEach(e => {
          if(e.label === t){
            rolesKey.push(e.value);
          }
        });
      });
      this.tempData.roles = rolesKey;

      let depKey = '';
      this.$store.getters.deps.forEach(e => {
        if(e.label === this.tempData.dep){
          depKey = e.value;
          }
      });
      this.tempData.dep = depKey;

      this.dialogFormVisible = true;

      // this.$nextTick(() => {
      //   this.$refs["refEditForm"].$refs["dataForm"].clearValidate();
      // });
    },
    updateData() {
      this.$refs["refEditForm"].$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$store.dispatch("management/editUser", this.tempData);

          this.dialogFormVisible = false;
        }
      });
    },
    handleDelete(row) {
      this.$confirm(this.$I18N("__common.confirmDelete"))
        .then(() => {
          this.$store.dispatch("management/deleteUser", row.oid);
        })
        .catch();
    },
    // getSortClass: function(key) {
    //   const sort = this.listQuery.sort;
    //   return sort === `+${key}` ? "ascending" : "descending";
    // }
  }
};
</script>

<style lang="scss" scoped>
@import "@/styles/myStyle.scss";
@import "@/styles/query-page.scss";
</style>
