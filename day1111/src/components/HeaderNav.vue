<template>
  <div class="header">
    <a href="index.html">
      <img src="@/assets/ssafy_logo.png" class="ssafy_logo"
    /></a>
    <p class="logo">게시파안</p>
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item to="/">글 목록</b-nav-item>
        <b-nav-item to="/create">글 작성</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "HeaderNav",
};
</script>
<style></style>
