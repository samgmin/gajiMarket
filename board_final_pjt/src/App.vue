<template>
  <v-app ref="app">
    <v-card class="overflow-hidden">
      <the-header-navbar />
      <v-sheet id="scrolling-techniques-2" class="overflow-y-auto" max-height="auto">
        <v-container style="height: 1000px; margin-top: 300px">
          <router-view />
        </v-container>
      </v-sheet>
    </v-card>
  </v-app>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
  },
};
</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
