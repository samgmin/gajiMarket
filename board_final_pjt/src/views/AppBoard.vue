<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> Board Service
    </h3>
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item to="/board">글 목록</b-nav-item>
        <b-nav-item to="/board/create">글 작성</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppBoard",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
