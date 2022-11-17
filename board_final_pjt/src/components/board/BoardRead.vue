<template>
  <div>
    <!-- 상세 글 -->
    <div v-if="board">
      <v-row align="center">
        <v-col class="d-flex" cols="12" sm="12" style="font-size: 20px">
          <span style="color: #b39ddb">{{ board.category }} &nbsp;</span>
          <span
            ><b>{{ board.title }}</b></span
          >
        </v-col>
      </v-row>
      <v-row justify="space-between">
        <v-col class="d-flex" cols="6" sm="6">
          <span>
            <v-icon color="green darken-2"> mdi-domain </v-icon>
            {{ board.writer }} | &nbsp;
          </span>
          <span style="font-size: 15px">{{ board.writeDate }}</span>
        </v-col>
        <v-col class="d-flex" cols="4" sm="4"></v-col>
        <v-col class="d-flex align-end" cols="2" sm="2">
          <span style="font-size: 15px">{{ board.readCount }}</span>
          <span
            ><v-btn icon color="deep-orange">
              <v-icon>mdi-thumb-up</v-icon>
            </v-btn></span
          >
        </v-col>
      </v-row>
      <hr />
      <v-row align="center">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="view" v-html="board.content"></div>
        </v-col>
      </v-row>
    </div>

    <!-- 수정 삭제 버튼 -->
    <div style="padding-top: 15px">
      <v-row align="center" justify="end">
        <v-col class="d-flex" cols="12" sm="2">
          <v-btn variant="outline-primary" v-on:click="updateBoard">수정</v-btn>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <v-btn variant="outline-primary" v-on:click="deleteBoard">삭제</v-btn>
        </v-col>
      </v-row>
      <!-- <v-btn variant="outline-primary" v-on:click="updateBoard">수정</v-btn>
      &nbsp;&nbsp;
      <v-btn variant="outline-primary" v-on:click="deleteBoard">삭제</v-btn> -->
    </div>
    <br />

    <!-- 댓글 목록 -->
    <div v-if="cList.length">
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th>댓글</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(comment, index) in cList" :key="index">
              <td>{{ comment.cwriter }}</td>
              <td>{{ comment.ccontent }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
      <hr />
      <br />

      <!-- 댓글 작성 -->
      <div class="comment">
        <v-form ref="form" lazy-validation v-if="show">
          <v-text-field
            v-model="comment.cwriter"
            label="작성자"
            required
          ></v-text-field>
          <v-text-field v-model="comment.ccontent" label="내용" required
            ><v-icon slot="append" color="red" @click="writecomment">
              mdi-plus
            </v-icon></v-text-field
          >
        </v-form>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";

export default {
  data: function () {
    return {
      comment: {
        bno: this.$route.query.bno,
        cwriter: "",
        ccontent: "",
      },
      bno: this.$route.query.bno,
      show: true,
    };
  },
  // props: ["money"],
  created() {
    this.findboard(this.$route.query.bno);
  },

  methods: {
    findboard(bno) {
      this.boardGetOne(bno);
    },
    enterToBr(str) {
      // 문자열에 enter값을 <br />로 변경.(html상에서 줄바꿈 처리)
      return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
    deleteBoard() {
      console.log(this.$route.query.bno);
      this.boardDelete(this.$route.query.bno);
      this.$router.push({ name: "boardlist" });
    },
    writecomment() {
      this.commentWrite(this.comment);
      this.comment.cwriter = "";
      this.comment.ccontent = "";
    },
    updateBoard() {
      this.$router.push({ name: "boardupdate" });
    },
    ...mapActions(boardStore, ["boardGetOne", "boardDelete", "commentWrite"]),
  },
  filters: {
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  computed: {
    ...mapState(boardStore, ["board", "cList"]),
  },
};
</script>

<style>
.comment {
  border: 1px solid rgb(190, 190, 190);
  border-radius: 10px;
  padding-left: 10px;
  padding-right: 10px;
}
</style>
