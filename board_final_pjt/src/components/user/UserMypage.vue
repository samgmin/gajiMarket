<template>
  <div style="width: 800px; margin: 0 auto">
    <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon>마이페이지
    </h3>
    <v-form id="sendForm" ref="sendFrom" enctype="multipart/form-data">
      <v-simple-table v-if="show && myInfo && myImg">
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
                v-model="myInfo.userid"
                required
                readonly
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <th class="text-left" colspan="1">비밀번호</th>
            <td class="text-left" colspan="11">
              <v-text-field
                v-if="flag"
                name="pwd"
                v-model="myInfo.userpwd"
                required
                readonly
                type="password"
              ></v-text-field>
              <v-text-field
                v-if="!flag"
                name="pwd"
                v-model="myInfo.userpwd"
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
                v-model="myInfo.username"
                required
                readonly
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <th class="text-left" colspan="1">이메일</th>
            <td class="text-left" colspan="10">
              <v-text-field
                v-if="flag"
                name="email"
                v-model="myInfo.email"
                required
                readonly
              ></v-text-field>
              <v-text-field
                v-if="!flag"
                name="email"
                v-model="myInfo.email"
                label="이메일을 입력하세요."
                required
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <br />
          </tr>
          <!-- <tr>
            <td colspan="12">
              사진을 첨부해주세요 추정나이, 성별, 닮은꼴 연예인을 알려드립니다 아쉽지만 필수에요
            </td>
          </tr> -->
          <tr>
            <th class="text-left" colspan="1">사진</th>
            <td class="text-left" colspan="11" style="overflow: hidden">
              <v-img
                v-if="flag"
                class="pa-12 primary rounded-circle d-inline-block"
                :src="require(`@/assets/memberImg/${myImg}`)"
                width="30"
              />
              <v-img
                v-if="!flag && preview === ''"
                class="pa-12 primary rounded-circle d-inline-block"
                :src="require(`@/assets/memberImg/${myImg}`)"
                width="30"
              />
              <v-img
                v-if="!flag && preview !== ''"
                class="pa-12 primary rounded-circle d-inline-block"
                :src="preview"
                width="30"
              />
              <v-file-input
                v-if="!flag"
                type="file"
                name="uploadFile"
                v-model="file"
              />
              <!-- <v-btn variant="outline-primary" @click="naverCFRApi">사진제출</v-btn> -->
            </td>
          </tr>
          <tr>
            <br />
          </tr>
        </template>
      </v-simple-table>

      <v-simple-table v-if="show && myInfo">
        <template v-slot:default>
          <colgroup>
            <col width="20%" />
            <col width="80%" />
          </colgroup>
          <tr style="padding-top: 100px" v-if="!flag && age">
            <th class="text-left" colspan="1">나이</th>
            <td class="text-left" colspan="11">
              {{ Math.round(ageconfidence * 10000) / 100 }}% 확률로 {{ age }}살
              입니다.
            </td>
          </tr>
          <tr v-if="flag || !age">
            <th class="text-left" colspan="1">나이</th>
            <td class="text-left" colspan="10">
              <v-text-field
                name="age"
                v-model="myInfo.age"
                required
                readonly
              ></v-text-field>
            </td>
          </tr>
          <tr id="naver" v-if="!flag && gender">
            <th class="text-left" colspan="1">성별</th>
            <td class="text-left" colspan="11">
              {{ Math.round(genderconfidence * 10000) / 100 }}% 확률로
              {{ gender }}입니다.
            </td>
          </tr>
          <tr v-if="flag || !gender">
            <th class="text-left" colspan="1">성별</th>
            <td class="text-left" colspan="11">
              <v-text-field
                name="gender"
                v-model="myInfo.gender"
                required
                readonly
              ></v-text-field>
            </td>
          </tr>
          <tr id="naver" v-if="!flag && celebrity">
            <th class="text-left" colspan="1">닮은꼴</th>
            <td class="text-left" colspan="11">
              {{ Math.round(celebrityconfidence * 10000) / 100 }}% 확률로
              {{ celebrity }}을(를) 닮았습니다.
            </td>
          </tr>
          <tr v-if="flag || !celebrity">
            <th class="text-left" colspan="1">닮은꼴</th>
            <td class="text-left" colspan="11">
              <v-text-field
                name="celebrity"
                v-model="myInfo.celebrity"
                required
                readonly
              ></v-text-field>
            </td>
          </tr>
        </template>
      </v-simple-table>
    </v-form>

    <v-row align="center" justify="end">
      <v-col class="d-flex justify-end" cols="12" sm="1">
        <v-btn variant="outline-primary" v-if="flag" @click="flag = !flag"
          >수정</v-btn
        >
        <v-btn variant="outline-primary" v-if="!flag" @click="modifyInfo"
          >수정</v-btn
        >
        &nbsp;&nbsp;&nbsp;&nbsp;
        <v-btn variant="outline-primary" @click="deleteInfo">탈퇴</v-btn>
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
      file: [],
      show: true,
      flag: true,
      preview: "",
    };
  },
  created() {
    this.userDataReset();
    let data = {
      userid: this.userInfo.userid,
      username: this.userInfo.username,
    };
    this.getMyInfo(data);
  },
  mounted() {
    this.userDataReset();
    let data = {
      userid: this.userInfo.userid,
      username: this.userInfo.username,
    };
    this.getMyInfo(data);
  },
  methods: {
    async naverCFRApi() {
      var fd = new FormData();
      fd.append("loadFile", this.file);
      console.log(this.file);

      await this.CFRApi(fd);
    },
    modifyInfo() {
      let data = {
        userid: this.myInfo.userid,
        username: this.myInfo.username,
        userpwd: this.myInfo.userpwd,
        email: this.myInfo.email,
        age: this.age,
        gender: this.gender,
        celebrity: this.celebrity,
      };
      console.log(data);
      this.updateInfo(data);
      this.flag = true;
    },
    modifyImg() {
      var fd = new FormData();
      fd.append("username", this.myInfo.username);
      fd.append("uploadFile", this.file);
      this.updateImg(fd);
    },
    modifyBtn() {
      this.userDataReset();
      this.modifyInfo();
      this.modifyImg();
    },
    deleteInfo() {},
    previewFile(file) {
      const fileData = (data) => {
        this.preview = data;
      };
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.addEventListener(
        "load",
        function () {
          fileData(reader.result);
        },
        false
      );
    },
    ...mapActions(userStore, [
      "userSignup",
      "getMyInfo",
      "updateImg",
      "updateInfo",
      "CFRApi",
      "userDataReset",
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
      "myInfo",
      "myImg",
    ]),
  },
  watch: {
    file() {
      this.previewFile(this.file);
      this.naverCFRApi();
    },
  },
};
</script>

<style>
#naver {
  margin-top: 130px;
}
</style>
