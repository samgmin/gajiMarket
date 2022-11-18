<template>
  <div>
    <div style="width: 500px; margin: 0 auto">
      <v-simple-table>
        <thead>
          <tr>
            <th class="text-left">아이디</th>
          </tr>
          <tr>
            <!-- <v-text-field
              label="아이디를 입력해주세요."
              single-line
              outlined
            ></v-text-field> -->
            <v-text-field
              id="userid"
              v-model="user.userid"
              label="아이디를 입력해주세요."
              @keyup.enter="confirm"
              single-line
              outlined
              dense
            ></v-text-field>
          </tr>
        </thead>
      </v-simple-table>
      <v-simple-table>
        <thead>
          <tr>
            <th class="text-left">비밀번호</th>
          </tr>
          <tr>
            <v-text-field
              id="userpwd"
              type="password"
              v-model="user.userpwd"
              label="비밀번호를 입력해주세요."
              @keyup.enter="confirm"
              single-line
              outlined
              dense
            ></v-text-field>
          </tr>
        </thead>
      </v-simple-table>
      <v-btn @click="confirm">로그인</v-btn>
      <v-btn :to="{ name: 'usersignup' }">회원가입</v-btn>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        console.log("if문 안에");
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style></style>
