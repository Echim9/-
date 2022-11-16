<template>
  <div>
    <a-menu
        v-model:selectedKeys="selectedKeys"
        :open-keys="openKeys"
        mode="inline"
        style="width: 256px;height: 100%"
        @openChange="onOpenChange"
        @select="selectedChange"
    >
      <a-sub-menu key="admin">
        <template #icon>
          <CloudSyncOutlined/>
        </template>
        <template #title>后台</template>
        <a-menu-item key="hotels">
          <database-filled />
          宾馆情况
        </a-menu-item>
        <a-menu-item key="epidemic">
          <file-text-filled />
          疫情防控
        </a-menu-item>
        <a-menu-item key="people">
          <folder-open-filled />
          人员管理
        </a-menu-item>
        <!--        <a-menu-item key="4">Option 4</a-menu-item>-->
      </a-sub-menu>
      <a-sub-menu key="sub2">
        <template #icon>
          <MailOutlined/>
        </template>
        <template #title>管理</template>
<!--        <a-menu-item key="login">Option 1</a-menu-item>-->
<!--        <a-menu-item key="6">Option 2</a-menu-item>-->
<!--        <a-menu-item key="7">Option 3</a-menu-item>-->
<!--        <a-menu-item key="8">Option 4</a-menu-item>-->
      </a-sub-menu>
      <div class="sider-footer">
        <a-tooltip title="设置">
          <a-button class="sider-footer-btn" @click="goSetting" shape="circle">
            <template #icon><setting-two-tone /></template>
          </a-button>
        </a-tooltip>
        <a-tooltip title="退出登录">
          <a-button @click="confirmLogout" class="sider-footer-btn" shape="circle">
            <template #icon><logout-outlined /></template>
          </a-button>
        </a-tooltip>
      </div>
      <!--      <a-sub-menu key="sub2">-->
      <!--        <template #icon></template>-->
      <!--        <template #title>-->
      <!--          <AppstoreOutlined />-->
      <!--          &nbsp;&nbsp;Navigation Two-->
      <!--        </template>-->
      <!--        <a-menu-item key="5">Option 5</a-menu-item>-->
      <!--        <a-menu-item key="6">Option 6</a-menu-item>-->
      <!--        <a-sub-menu key="sub3" title="Submenu">-->
      <!--          <a-menu-item key="7">Option 7</a-menu-item>-->
      <!--          <a-menu-item key="8">Option 8</a-menu-item>-->
      <!--        </a-sub-menu>-->
      <!--      </a-sub-menu>-->
    </a-menu>
  </div>
</template>

<script>
import {
  MailOutlined,
  AppstoreOutlined,
  SettingOutlined,
  CheckCircleOutlined,
  LogoutOutlined,
  SettingTwoTone,
  ExclamationCircleOutlined,
  CloudSyncOutlined,
  DatabaseFilled,
  FileTextFilled,
  FolderOpenFilled,
  CloseCircleOutlined,
  SyncOutlined
} from '@ant-design/icons-vue';
import store from "@/store";
import {message, Modal} from "ant-design-vue";
import {createVNode} from "vue";

export default {
  name: "Sider",
  components: {
    MailOutlined,
    AppstoreOutlined,
    SettingOutlined,
    CheckCircleOutlined,
    LogoutOutlined,
    SettingTwoTone,
    CloudSyncOutlined,
    DatabaseFilled,
    FileTextFilled,
    FolderOpenFilled,
    CloseCircleOutlined,
    SyncOutlined
  },
  data() {
    return {
      selectedKeys: ['hotels'],
      openKeys: ['admin'],
      rootSubmenuKeys: ['admin', 'manage'],
    }
  },
  methods: {
    goSetting() {
      this.$router.push({name: 'setting'})
      this.selectedKeys[0] = ['']
    },
    onOpenChange(openKeys) {
      const latestOpenKey = openKeys.find(key => this.openKeys.indexOf(key) === -1);
      if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
        this.openKeys = openKeys;
      } else {
        this.openKeys = latestOpenKey ? [latestOpenKey] : [];
      }
    },
    selectedChange(item) {
      this.$router.push({name: item.key})
    },
    clearToken() {
      store.dispatch('loginState/clearToken')
      message.success('登出成功！')
      this.$router.push({name: 'login'})
    },
    confirmLogout() {
      Modal.confirm({
        title: '您确定退出登录吗？',
        icon: createVNode(ExclamationCircleOutlined),
        content: '退出后需要重新登录',
        cancelText: '取消',
        okText: '确定',
        onOk: () => {
          this.clearToken()
        },
        onCancel() {},
      });
    }
  },
  mounted() {
  }
}
</script>

<style lang="scss" scoped>
.sider-footer {
  height: 60px;
  width: 256px;
  position: fixed;
  bottom: 0;
  left: 0;
  box-shadow: rgba(0, 0, 0, 0.15) -1px -1px 2px;
  display: flex;
  align-items: center;
}
.sider-footer-btn {
  border: 0;
  flex: 1;
  margin: 8px;
}
.sider-footer-status {
  flex: 3;
  margin-left: 15px;
  display: flex;
  align-items: center;
  text-align: center;
  padding-left: 15px;
}
</style>
