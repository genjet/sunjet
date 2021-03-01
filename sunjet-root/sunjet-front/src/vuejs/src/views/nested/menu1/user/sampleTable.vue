<template>
  <div>
    <el-table :data="getTableData" border  style="width: 100%" @sort-change="sortChange">
      <template v-for="(column, index) in columns">
        <el-table-column
          :key="index"
          :prop="column.prop"
          :label="column.label"
          :width="getWidth(column.width)"
          :sortable="column.sort"
          :fixed="getFixed(column.fixed)"
          v-if="column.show !== false"
        >
          <template slot-scope="scope">
            <div v-if="column.option ==='icon'">
              <svg-icon :iconClass="getIcon(scope.row[column.prop])" />
            </div>
            <div v-else-if="column.option ==='switch'">
              <el-switch :disabled="true" v-model="scope.row[column.prop]"></el-switch>
            </div>
            <div v-else-if="column.option ==='edit-button'">
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit"
                @click.native="handleUpdate(scope.row)"
              >{{$I18N("__common.edit")}}</el-button>
            </div>
            <div v-else-if="column.option ==='delete-button'">
              <el-button
                type="warning"
                size="mini"
                icon="el-icon-delete"
                @click.native="handleDelete(scope.row)"
              >{{$I18N("__common.delete")}}</el-button>
            </div>
            <!-- <div v-else-if="'select' === column.operate.name">
               <span>{{dd(scope.row[column.prop], column)}}</span>
            </div> -->
            <div v-else>
              <span>{{scope.row[column.prop]}}</span>
            </div>
          </template>
        </el-table-column>
      </template>
    </el-table>
    <div class="pagination">
      <el-pagination :total="getTotal"  :page-size="pageSize" @current-change="currentChange"/>
    </div>
  </div>
</template>
<script>
import _ from 'lodash';

export default {
  name: "sampleTable",
  components: {},
  props: {
    resultDatas: {
      type: Array,
      required: true
    },
    columns: {
      type: Array,
      required: true
    },
    pageSize:{
      type: Number,
      required: true
    }
  },
  data() {
    return {
      dialogStatus: "",
      currentPage: 1,
      sortProp:"",
      sortOrder:"descending",
      a: 0,
    };
  },
  computed:{
    getTotal(){
      return  this.resultDatas.length;
    },
    getTableData(){
      const sortDatas = _.orderBy(this.resultDatas, this.sortProp, (this.sortOrder === 'descending' ?'desc' : 'asc'));
      const rtnDatas = sortDatas.slice((this.currentPage-1)*this.pageSize, this.currentPage*this.pageSize);
      // this.columns.forEach(element => {
      //   if(element.replaceShow){
      //     rtnDatas.forEach(e => {
      //       element.operate.dataList.forEach(d => {
      //         if(d.value === e[element.prop]){

      //           console.log(d.label);
      //         }
      //       })
      //     });
      //   }
      // });
      return rtnDatas;
    }
  },
  methods: {
    dd(a, column){
      if('select' === column.operate.name){
      // console.log(a);
      // console.log(column);
      }

    },
    getWidth(width) {
      return undefined === width ? "100px" : width;
    },
    getIcon(icon) {
      return undefined === icon || null === icon ? "" : icon;
    },
    currentChange(currentPage){
        this.currentPage = currentPage;
      },
    handleUpdate(row) {
      this.$emit("handleUpdate", row);
    },
    handleDelete(row) {
      this.$emit("handleDelete", row);
    },
    getFixed(fixed){
      return undefined === fixed ? false : fixed;
    },
    sortChange(data) {
      const { prop, order } = data
      this.sortProp = prop;
      this.sortOrder = order;
    },
  }
};
</script>
