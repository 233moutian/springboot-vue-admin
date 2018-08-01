<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="success" icon="el-icon-refresh"
                     v-if="hasPermission('permission:list')"
                     @click.native.prevent="getList">refresh
          </el-button>
          <el-button type="primary" icon="el-icon-plus"
                     v-if="hasPermission('permission:add')"
                     @click.native.prevent="showAddPermissionDialog">add
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list"
              element-loading-text="loading"
              border fit highlight-current-row>
      <el-table-column label="#" align="center" width="80">
        <template slot-scope="scope">
          <span v-text="scope.$index"></span>
        </template>
      </el-table-column>
      <el-table-column label="resource" align="center">
        <template slot-scope="scope">
          {{scope.row.resource}}
        </template>
      </el-table-column>
      <el-table-column label="code" align="center">
        <template slot-scope="scope">
          {{scope.row.code}}
        </template>
      </el-table-column>
      <el-table-column label="handle" align="center">
        <template slot-scope="scope">
          {{scope.row.handle}}
        </template>
      </el-table-column>

      <el-table-column label="Admin" align="center"
                       v-if="hasPermission('permission:update') || hasPermission('permission:delete')">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit-outline"
                     v-if="hasPermission('permission:update')"
                     @click="showUpdatePermissionDialog(scope.$index)">update
          </el-button>
          <el-button type="danger" icon="el-icon-delete"
                     v-if="hasPermission('permission:delete')"
                     @click="removePermission(scope.$index)">delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.page"
      :page-size="listQuery.size"
      :total="total"
      :page-sizes="[10, 30, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]"
               :visible.sync="dialogFormVisible">
      <el-form status-icon class="small-space" label-position="left" label-width="75px"
               style="width: 300px; margin-left:50px;"
               :model="tmpPermission"
               ref="tmpPermission">
        <el-form-item label="Resource" prop="resource" required>
          <el-input type="text" prefix-icon="el-icon-message" auto-complete="off"
                    :readonly="readonly"
                    v-model="tmpPermission.resource"/>
        </el-form-item>
        <el-form-item label="Code" prop="code" required>
          <el-input type="text" prefix-icon="el-icon-edit" auto-complete="off"
                    :readonly="readonly"
                    v-model="tmpPermission.code"/>
        </el-form-item>
        <el-form-item label="Handle" prop="handle" required>
          <el-input type="text" prefix-icon="el-icon-edit" auto-complete="off"
                    :readonly="readonly"
                    v-model="tmpPermission.handle"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">cancel</el-button>
        <el-button type="danger"
                   v-if="dialogStatus === 'add'"
                   @click="$refs['tmpPermission'].resetFields()">reset
        </el-button>
        <el-button type="success"
                   v-if="dialogStatus === 'add'"
                   :loading="btnLoading"
                   @click.native.prevent="addPermission">add
        </el-button>
        <el-button type="primary"
                   v-else
                   :loading="btnLoading"
                   @click.native.prevent="updatePermission">update
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listPermission, remove, createPermission, updatePermission } from '@/api/permission'

  export default {
    name: 'selectPermission',
    data() {
      return {
        list: [], // 资源列表
        listLoading: false, // 数据加载等待动画
        total: 0, // 数据总数
        listQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        },
        dialogStatus: 'add',
        dialogFormVisible: false,
        textMap: {
          update: 'Update Permission',
          add: 'Add Permission'
        },
        btnLoading: false, // 按钮等待动画
        readonly: false, // 只读输入框
        tmpPermission: {
          id: '',
          resource: '',
          code: '',
          handle: ''
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        listPermission(this.listQuery).then(response => {
          console.log(response.data.list)
          this.list = response.data.list
          this.listLoading = false
        })
      },
      /**
       * 改变每页数量
       * @param size 页大小
       */
      handleSizeChange(size) {
        this.listQuery.size = size
        this.listQuery.page = 1
        this.getList()
      },
      /**
       * 改变页码
       * @param page 页号
       */
      handleCurrentChange(page) {
        this.listQuery.page = page
        this.getList()
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]))
      },
      /**
       * 显示修改权限对话框
       * @param index 权限资源下标
       */
      showUpdatePermissionDialog(index) {
        this.dialogFormVisible = true
        this.dialogStatus = 'update'
        this.tmpPermission.id = this.list[index].id
        this.tmpPermission.resource = this.list[index].resource
        this.tmpPermission.code = this.list[index].code
        this.tmpPermission.handle = this.list[index].handle
        this.readonly = false
      },
      /**
       * 显示添加权限资源对话框
       */
      showAddPermissionDialog() {
        // 显示新增对话框
        this.dialogFormVisible = true
        this.dialogStatus = 'add'
        this.tmpPermission.resource = ''
        this.tmpPermission.code = ''
        this.tmpPermission.handle = ''
        this.readonly = false
      },
      /**
       * 添加权限资源
       */
      addPermission() {
        this.$refs.tmpPermission.validate(valid => {
          this.btnLoading = true
          createPermission(this.tmpPermission).then(() => {
            this.$message.success('add success')
            this.getList()
            this.dialogFormVisible = false
            this.btnLoading = false
          })
        })
      },
      /**
       * 更新权限资源
       */
      updatePermission() {
        updatePermission(this.tmpPermission).then(() => {
          this.$message.success('update success')
          this.getList()
          this.dialogFormVisible = false
        })
      },
      /**
       * 删除权限资源
       * @param index 权限资源下标
       */
      removePermission(index) {
        this.$confirm('Delete this Permission?', 'Waring', {
          confirmButtonText: 'yes',
          showCancelButton: false,
          type: 'warning'
        }).then(() => {
          const id = this.list[index].id
          remove(id).then(() => {
            this.$message.success('delete success')
            this.getList()
          })
        })
      }
    }
  }
</script>
