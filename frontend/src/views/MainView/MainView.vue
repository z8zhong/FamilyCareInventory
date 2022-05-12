<template>

    <el-container class="app-wrapper">
      <el-aside width="200px" class="sidebar-container" >
        <MenuView/>
      </el-aside>
      <el-container>
        <el-header>
          <HeadersView></HeadersView>
        </el-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>

</template>

<script setup>
import MenuView from '@/views/MainView/MenuView'
import HeadersView from '@/views/MainView/HeadersView/HeadersView.vue'
</script>

<style lang="scss" scoped>
@import  "src/styles/variables.scss";
.app-container {
  position: relative;
  width: 100%;
  height: 100%;
}
.container {
  width: calc(100% - 210px );
  height: 100%;

  position: fixed;
  top: 0;
  right: 0;
  z-index: 9;
  transition: all 0.28s;
  &.hidderContainer {
    width: calc(100% - 67px);
  }
}
::v-deep .el-header {
  padding: 0;
}
.sidebar-container {
  transition: width #{$sideBarDuration};
  // width: $sideBarWidth !important;
  height: 100%;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1001;
  overflow: hidden;
  background-color: rgb(82, 140, 215);
  margin-top: 100px;
  font-size: 18px;
  // 重置 element-plus 的css
  .horizontal-collapse-transition {
    transition: 0s width ease-in-out, 0s padding-left ease-in-out,
    0s padding-right ease-in-out;
  }

  .scrollbar-wrapper {
    overflow-x: hidden !important;
  }

  .el-scrollbar__bar.is-vertical {
    right: 0px;
  }

  .el-scrollbar {
    height: 100%;
  }

  &.has-logo {
    .el-scrollbar {
      height: calc(100% - 50px);
    }
  }

  .is-horizontal {
    display: none;
  }

  a {
    display: inline-block;
    width: 100%;
    overflow: hidden;
  }

  .svg-icon {
    margin-right: 16px;
  }

  .sub-el-icon {
    margin-right: 12px;
    margin-left: -2px;
  }

  .el-menu {
    border: none;
    height: 100%;
    width: 100% !important;
  }

  .is-active > .el-submenu__title {
    color: $subMenuActiveText !important;
  }

  & .nest-menu .el-submenu > .el-submenu__title,
  & .el-submenu .el-menu-item {
    min-width: $sideBarWidth !important;
  }
}

</style>
