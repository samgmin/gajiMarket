<template>
  <div>
    <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> 회원가입
      <v-form id="sendForm" ref="sendFrom" enctype="multipart/form-data">
        <v-simple-table v-if="show">
          <template v-slot:default>
            <tr>
              <th colspan="1">아이디</th>
              <td colspan="11">
                <v-text-field
                  name="id"
                  v-model="id"
                  label="아이디를 입력하세요."
                  required
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th colspan="1">비밀번호</th>
              <td colspan="11">
                <v-text-field
                  name="pwd"
                  v-model="pwd"
                  label="비밀번호를 입력하세요."
                  required
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th colspan="1">이름</th>
              <td colspan="11">
                <v-text-field
                  name="name"
                  v-model="name"
                  label="이름을 입력하세요."
                  required
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th colspan="1">이메일</th>
              <td colspan="11">
                <v-text-field
                  name="email"
                  v-model="email"
                  label="이메일을 입력하세요."
                  required
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <td colspan="12">
                사진을 첨부해주세요 추정나이, 성별, 닮은꼴연예인을 알려드립니다
                아쉽지만 필수에요
              </td>
            </tr>
            <tr>
              <th colspan="1">이미지 첨부</th>
              <td colspan="11">
                <v-file-input type="file" name="uploadFile" v-model="file" />
              </td>
              <v-btn variant="outline-primary" @click="naverCFRApi"
                >사진제출</v-btn
              >
            </tr>

            <tr v-if="age">
              <th colspan="1">나이</th>
              <td colspan="11">
                {{ ageconfidence * 100 }}%확률로 {{ age }}살입니다
              </td>
            </tr>
            <tr v-if="gender">
              <th colspan="1">성별</th>
              <td colspan="11">
                {{ genderconfidence * 100 }}%확률로 {{ gender }}입니다
              </td>
            </tr>
            <tr v-if="celebrity">
              <th colspan="1">닮은꼴</th>
              <td colspan="11">
                {{ celebrityconfidence * 100 }}%확률로 {{ celebrity }}닮았습니다
              </td>
            </tr>
          </template>
        </v-simple-table>
      </v-form>

      <v-row align="center" justify="end">
        <v-col class="d-flex" cols="12" sm="1">
          <v-btn variant="outline-primary" @click="onSubmit">가입</v-btn>
        </v-col>
      </v-row>
    </h3>
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
    };
  },
  methods: {
    async onSubmit() {
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
    },
    async naverCFRApi() {
      var fd = new FormData();
      fd.append("loadFile", this.file);
      console.log(this.file);

      await this.CFRApi(fd);
    },
    ...mapActions(userStore, ["userSignup", "CFRApi"]),
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
    ]),
  },
};
</script>

<style></style>
