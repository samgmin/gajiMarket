<template>
  <!-- <v-card class="overflow-hidden"> -->
  <v-app-bar
    absolute
    color="#fcb69f"
    dark
    shrink-on-scroll
    src="https://picsum.photos/1920/1080?random"
    scroll-target="#scrolling-techniques-2"
  >
    <template v-slot:img="{ props }">
      <v-img
        v-bind="props"
        gradient="to top right, rgba(19,84,122,.5), rgba(128,208,199,.8)"
      ></v-img>
    </template>

    <v-app-bar-nav-icon></v-app-bar-nav-icon>

    <v-app-bar-title>^^</v-app-bar-title>

    <v-spacer></v-spacer>

    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn icon>
      <v-icon>mdi-heart</v-icon>
    </v-btn>

    <v-menu bottom left offset-y open-on-hover>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon color="white" v-bind="attrs" v-on="on">
          <v-icon>mdi-account-circle</v-icon>
        </v-btn>
      </template>

      <v-list>
        <v-list-item>
          <v-list-item-title>로그인</v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>회원가입</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <template v-slot:extension>
      <v-tabs align-with-title>
        <v-tab to="/">메인</v-tab>
        <v-tab :to="{ name: 'board' }">아파트</v-tab>
        <v-tab :to="{ name: 'board' }">게시판</v-tab>
      </v-tabs>
    </template>
  </v-app-bar>
  <!-- <v-sheet id="scrolling-techniques-2" class="overflow-y-auto" max-height="600">
      <v-container style="height: 1000px"></v-container>
    </v-sheet>
  </v-card> -->

  <!-- <b-navbar-brand href="#">
        <router-link :to="{ name: 'board' }">
          <b-img
            :src="require('@/assets/ssafy_logo.png')"
            id="logo"
            class="d-inline-block align-top"
            alt="logo"
          ></b-img>
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link :to="{ name: 'board' }" class="link">
              <b-icon
                icon="house-door"
                animation="fade"
                font-scale="2"
              ></b-icon>
              메인
            </router-link>
            <router-link :to="{ name: 'board' }" class="m-2 link">
              <b-icon icon="journal" animation="fade" font-scale="2"></b-icon>
              게시판
            </router-link>
          </b-nav-item>
        </b-navbar-nav>

        Right aligned nav items -->
  <!--<b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'user' }" class="link">
                <b-icon icon="person-circle"></b-icon> 회원가입
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'user' }" class="link">
                <b-icon icon="key"></b-icon> 로그인
              </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar> -->
</template>

<script>
export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
};
</script>

<style scoped>
/* #logo {
  width: 120px;
}

.link {
  text-decoration: none;
} */
</style>
