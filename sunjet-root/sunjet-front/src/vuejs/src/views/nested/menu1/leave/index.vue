<template>
  <div class="query-page">
    <div class="query-area">
      <div class="title">{{$I18N(`__router.leave`)}}</div>
      <div class="content">
        <el-form :model="searchForm" label-width="25%">
          <el-row>
            <el-col :xs="24" :sm="12">
              <el-form-item label="發動者(企業統編)">
                <el-input v-model.number="searchForm.taxId" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="12">
              <el-form-item :label="$I18N(`__common.pleaseSelect`,`__leave.type`)">
                <el-select
                  v-model="searchForm.leaveType"
                  :placeholder="$I18N(`__common.pleaseSelect`,`__leave.type`)"
                >
                  <el-option
                    v-for="(value,key) in leaveTypeOptions"
                    :key="key"
                    :label="value"
                    :value="key"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="12">
              <el-form-item prop="queryDate" :label="$I18N(`__common.queryDateRange`)">
                <el-date-picker
                  v-model="searchForm.rangeDate"
                  type="datetimerange"
                  unlink-panels
                  :range-separator="$I18N(`__common.to`)"
                  :start-placeholder="$I18N(`__common.startDate`)"
                  :end-placeholder="$I18N(`__common.endDate`)"
                  @change="selectDate"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :xs="24" :sm="12">
            <el-col :span="6">
              <el-button
                type="warning"
                icon="el-icon-search"
                @click="query()"
              >{{$I18N(`__common.query`)}}</el-button>
            </el-col>
            <el-col :span="6">
              <el-button
                type="warning"
                icon="el-icon-search"
                @click="handleCreate()"
              >{{$I18N(`__common.add`)}}</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <!-- content end -->
    </div>
    <!-- query-area end -->

    <div v-if="showQueryResult" class="result-area">
      <div v-if="hasResult">
        <div class="title">
          <span>{{$I18N(`__leave.queryResult`)}}</span>
        </div>
        <sample-table :resultDatas="resultDatas" :columns="getColumns" :pageSize= 5></sample-table>
      </div>
      <!-- hasResult end-->
      <div v-else class="noData">
        <div class="title">{{$I18N(`__common.queryResult`)}}</div>
        <p class="font">{{$I18N(`__common.queryNoData`)}}</p>
      </div>
    </div>
    <!-- result-area end -->

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
  <!-- query-page end -->
</template>

<script>
import sampleTable from "../user/sampleTable.vue";
import sampleDialog from "../user/sampleDialog.vue";
import editForm from "../user/editForm.vue";

import { LeaveType } from "@/typings/Enums";

export default {
  name: "leave",
  components: {
    sampleTable,
    sampleDialog,
    editForm
  },
  mounted() {
    //this.leaveTypeOptions = this.$store.getters.options["leaveType"];
  },
  computed: {
    getLeaveTypeOptions() {
      return Object.keys(this.leaveTypeOptions).map(key => {
        return { value: this.leaveTypeOptions[key], key: key };
      });
    },
    getColumns() {
      this.columns.forEach(e => {
        if (e.prop === "dep") {
          e.operate.dataList = this.$store.getters.deps;
        }
        if (e.prop === "leaveType") {
          e.operate.dataList = this.getLeaveTypeOptions;
        }
      });
      return this.columns;
    },
    hasResult() {
      return this.resultDatas.length > 0;
    }
  },
  data() {
    return {
      rules: {},
      tempData: {},
      dialogStatus: "",
      dialogFormVisible: false,
      leaveTypeOptions: LeaveType,
      searchForm: {
        rangeDate: null,
        taxId: "",
        leaveType: null
      },
      resultDatas: [],
      columns: [
        {
          prop: "id",
          label: this.$I18N(`id`),
          sort:true,
          operate: { show: false }
        },
        {
          prop: "name",
          label: this.$I18N(`name`),
          operate: { name: "input" }
        },
        {
          prop: "dep",
          label: this.$I18N(`dep`),
          operate: { name: "select", dataList: [] }
        },
        {
          prop: "leaveType",
          label: this.$I18N(`leaveType`),
          operate: { name: "select", dataList: [] }
        },
        {
          prop: "leaveStatus",
          label: this.$I18N(`leaveStatus`),
          operate: { name: "select", dataList: [] }
        },
        {
          prop: "startDate",
          label: this.$I18N(`startDate`),
          operate: { name: "input" }
        },
        {
          prop: "endDate",
          label: this.$I18N(`endDate`),
          operate: { name: "input" }
        },
        {
          prop: "leaveHours",
          label: this.$I18N(`leaveHours`),
          operate: { name: "input" }
        }
      ],
      showQueryResult: false
    };
  },
  methods: {
    async query() {
      await this.$store.dispatch("leave/getLeaves", this.searchForm);
      this.resultDatas = this.$store.getters.leaves;
      this.showQueryResult = true;
    },
    selectDate(date) {
      this.searchForm.rangeDate = date;
    },
    handleClose(flag) {
      this.dialogFormVisible = flag;
    },
    createOrUpdate() {
      this.dialogStatus === "create" ? this.createData() : this.updateData();
    },
    cancel() {
      this.dialogFormVisible = false;
      this.dialogStatus = "";
      //this.resetTemp();
    },
    handleCreate() {
      //this.resetTemp();
      this.dialogStatus = "create";
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        this.$refs["refEditForm"].$refs["dataForm"].clearValidate();
      });
    },
    resetTemp() {
      this.tempData = { endDate: new Date() };
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/styles/myStyle.scss";
@import "@/styles/query-page.scss";
</style>
