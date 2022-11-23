<template>
  <div style="width: 800px; margin: 0 auto; margin-bottom: 150px">
    <h3
      class="underline-hotpink"
      style="margin-top: 100px; margin-bottom: 50px"
    >
      <b-icon icon="journals"></b-icon>
      <!-- <v-img src="@/assets/eggplant-logo.png" width="40px"></v-img> -->
      회원가입
    </h3>
    <v-form id="sendForm" ref="sendFrom" enctype="multipart/form-data">
      <v-simple-table v-if="show">
        <template v-slot:default>
          <colgroup>
            <col width="20%" />
            <col width="80%" />
          </colgroup>
          <tr>
            <th class="text-left" colspan="1">아이디</th>
            <td class="text-left" colspan="11">
              <v-text-field
                name="id"
                v-model="id"
                :error-messages="idErrorMessages"
                label="아이디를 입력하세요."
                required
                @blur="checkid"
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <th class="text-left" colspan="1">비밀번호</th>
            <td class="text-left" colspan="11">
              <v-text-field
                name="pwd"
                v-model="pwd"
                :error-messages="pwdErrorMessages"
                label="비밀번호를 입력하세요."
                required
                type="password"
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <th class="text-left" colspan="1">이름</th>
            <td class="text-left" colspan="11">
              <v-text-field
                name="name"
                v-model="name"
                :error-messages="nameErrorMessages"
                label="이름을 입력하세요."
                required
                @blur="checkname"
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <th class="text-left" colspan="1">이메일</th>
            <td class="text-left" colspan="10">
              <v-text-field
                name="email"
                v-model="email"
                :error-messages="emailErrorMessages"
                label="이메일을 입력하세요."
                required
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <br />
          </tr>
          <tr>
            <td colspan="12">
              얼굴 사진을 첨부하면 추정 나이, 성별, 닮은꼴 연예인을
              알려드립니다. 아쉽지만 필! 수! 에요.
            </td>
          </tr>
          <tr>
            <th class="text-left" colspan="1">이미지 첨부</th>
            <td class="text-left" colspan="11" style="overflow: hidden">
              <v-file-input
                type="file"
                name="uploadFile"
                v-model="file"
                :rules="fileRules"
                :error-messages="fileErrorMessages"
                required
              />
              <!-- <v-btn variant="outline-primary" @click="naverCFRApi">사진제출</v-btn> -->
            </td>
          </tr>
          <tr>
            <br />
          </tr>
        </template>
      </v-simple-table>

      <v-simple-table>
        <template>
          <tr v-if="age">
            <th class="text-left" colspan="1">나이</th>
            <td class="text-left" colspan="11">
              {{ Math.round(ageconfidence * 10000) / 100 }}% 확률로 {{ age }}살
              입니다.
            </td>
          </tr>
          <tr v-if="gender">
            <th class="text-left" colspan="1">성별</th>
            <td class="text-left" colspan="11">
              {{ Math.round(genderconfidence * 10000) / 100 }}% 확률로
              {{ gender }}입니다.
            </td>
          </tr>
          <tr v-if="celebrity">
            <th class="text-left" colspan="1">닮은꼴</th>
            <td class="text-left" colspan="11">
              {{ Math.round(celebrityconfidence * 10000) / 100 }}% 확률로
              {{ celebrity }}을(를) 닮았습니다.
            </td>
          </tr>
        </template>
      </v-simple-table>
    </v-form>

    <v-row align="center" justify="end">
      <v-col class="d-flex flex-row-reverse" cols="12" sm="1">
        <v-btn variant="outline-primary" @click="onSubmit">가입</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
export default {
  data() {
    return {
      id: "",
      pwd: "",
      name: "",
      email: "",
      file: [],
      show: true,
      idErrorMessages: "",
      pwdErrorMessages: "",
      nameErrorMessages: "",
      emailErrorMessages: "",
      fileErrorMessages: "",
      fileRules: [(v) => !!v || "이미지를 첨부해주세요."],
    };
  },
  created() {
    this.userDataReset();
  },
  mounted() {
    this.userDataReset();
  },
  methods: {
    checkid() {
      this.userIdCheck(this.id);
    },
    checkname() {
      this.userNameCheck(this.name);
    },
    async onSubmit() {
      if (this.inputCheck()) {
        var fd = new FormData();
        fd.append("uploadFile", this.file);
        fd.append("userid", this.id);
        fd.append("userpwd", this.pwd);
        fd.append("username", this.name);
        fd.append("email", this.email);
        fd.append("age", this.age);
        fd.append("gender", this.gender);
        fd.append("celebrity", this.celebrity);

        console.log(fd);
        await this.userSignup(fd);
        this.$router.push({ name: "userlogin" });
      }
    },
    async naverCFRApi() {
      var fd = new FormData();
      fd.append("loadFile", this.file);
      console.log(this.file);

      await this.CFRApi(fd);
    },
    inputCheck() {
      if (!this.id) {
        this.idErrorMessages = "아이디를 입력해주세요.";
      }
      if (!this.pwd) {
        this.pwdErrorMessages = "비밀번호를 입력해주세요.";
      }
      if (!this.name) {
        this.nameErrorMessages = "이름을 입력해주세요.";
      }
      if (!this.email) {
        this.emailErrorMessages = "이메일을 입력해주세요.";
      }
      if (this.file.length == 0) {
        this.fileErrorMessages = "이미지를 첨부해주세요.";
      }

      if (
        this.idErrorMessages ||
        this.pwdErrorMessages ||
        this.nameErrorMessages ||
        this.emailErrorMessages ||
        this.fileErrorMessages
      ) {
        return false;
      }

      return true;
    },
    ...mapActions(userStore, [
      "userSignup",
      "CFRApi",
      "userDataReset",
      "userIdCheck",
      "userNameCheck",
    ]),
  },
  computed: {
    ...mapState(userStore, [
      "userInfo",
      "age",
      "ageconfidence",
      "gender",
      "genderconfidence",
      "celebrity",
      "celebrityconfidence",
      "canUseId",
      "canUseName",
    ]),
  },
  watch: {
    id() {
      this.idErrorMessages = "";
    },
    pwd() {
      this.pwdErrorMessages = "";
    },
    name() {
      this.nameErrorMessages = "";
    },
    email() {
      this.emailErrorMessages = "";
    },
    file() {
      this.fileErrorMessages = "";
      this.naverCFRApi();
    },
  },
};
</script>

<style>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(128, 30, 255, 0.3) 30%
  );
}
</style>
