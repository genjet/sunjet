<template>
  <div>
    <el-form
      ref="dataForm"
      :rules="rules"
      :model="getResultData"
      class="main-form"
      label-position="left"
      label-width="80px"
    >
      <template v-for="(column, index) in columns">
        <div :key="index" v-if="isShow(column.operate.show)">
          <el-form-item :key="index" :prop="column.prop" :label="column.label">
            <div :key="index" v-if="'select' === column.operate.name">
              <el-select
                v-model="getResultData[column.prop]"
                class="filter-item"
                :placeholder="$I18N(`pleaseSelect`)"
                :multiple="column.operate.multiple"
              >
                <el-option
                  v-for="item in getSelectList(column.operate.dataList)"
                  :key="item.key"
                  :label="item.label"
                  :value="item.value"

                />
              </el-select>
            </div>
            <div :key="index" v-else-if="'date' === column.operate.name">
              <el-date-picker
                v-model="getResultData[column.prop]"
                type="date"
                :placeholder="$I18N(`pleasePickADate`)"
              />
            </div>
            <div :key="index" v-else-if="'switch' === column.operate.name">
               <el-switch  v-model="getResultData[column.prop]"></el-switch>
            </div>
            <div :key="index" v-else-if="'checkBox' === column.operate.name">
              <el-checkbox-group v-model="getResultData[column.prop]">
                <el-checkbox  v-for="(operate)  in getSelectList(column.operate.dataList)"
                   :label="operate.label"
                   :key="operate.value">
                  {{operate.value}}
                </el-checkbox>
              </el-checkbox-group>
            </div>
            <div class="block" :key="index" v-else-if="'block' === column.operate.name">
              <el-cascader
                v-model="getResultData[column.prop]"
                :options="column.operate.dataList"
                :props="props"
                ></el-cascader>
            </div>
            <div :key="index" v-else-if="'input' === column.operate.name">
              <el-input v-model="getResultData[column.prop]" />
            </div>
          </el-form-item>
        </div>
      </template>
    </el-form>

  </div>
</template>

<script>
export default {
  name: "updateForm",
  props: {
    resultData: {
      type: Object,
      required: true
    },
    columns: {
      type: Array,
      required: true
    },
    rules: {
      type: Object,
      required: true
    },
  },
  data() {
    return {
      checkList: ['A'],
       props: { multiple: true },
        options: [{
          value: 1,
          label: '东南',
          children: [{
            value: 2,
            label: '上海'
          }, {
            value: 7,
            label: '江苏'
          }, {
            value: 12,
            label: '浙江'
          }]
        }, {
          value: 17,
          label: '西北',
          children: [{
            value: 18,
            label: '陕西'
          }, {
            value: 21,
            label: '新疆维吾尔族自治区'
          }]
        }, {
          value: 9,
          label: '東北'
        }]
      // dialogFormVisible: false,
    };
  },
  computed: {
    getResultData(){
      console.log(this.resultData);
      return this.resultData;
    },
    getCheckList(){
      console.log(this.checkList);
      return this.checkList;
    },
  },
  methods: {
    getSelectList(list) {
      console.log(list);
      return undefined === list ? [] : list;
    },
    isShow(show) {
      return undefined === show ? true : show;
    },
    handleEdit(data){
      console.log("handleEdit",data);
    }
  }
};
</script>
<style lang="scss">
.main-form {
  width: 400px;
  margin-left: 60px;
}
</style>
