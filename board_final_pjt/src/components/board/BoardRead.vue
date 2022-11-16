<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 정보</h1>
    <div class="regist_form" v-if="board">
      <label for="bno">글번호</label>
      <div class="view">{{ board.bno }}</div>
      <label for="title">제목</label>
      <div class="view">{{ board.title }}</div>
      <label for="writer">작성자</label>
      <div class="view">{{ board.writer }}</div>
      <label for="content">내용</label>
      <div class="view" v-html="board.content"></div>
      <label for="content">작성일자</label>
      <div class="view">{{ board.writeDate }}</div>
      <label for="content">조회수</label>
      <div class="view">{{ board.readCount }}</div>
      <div class="regist_form" v-if="cList">
        <h1 class="underline">댓글</h1>
        <table id="book-list">
          <colgroup>
            <col style="width: 30%" />
            <col style="width: 70%" />
          </colgroup>
          <thead>
            <tr>
              <th>작성자</th>
              <th>내용</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(comment, index) in cList" :key="index">
              <td>{{ comment.cwriter }}</td>
              <td>{{ comment.ccontent }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div style="padding-top: 15px">
        <b-button v-on:click="updateBoard">수정</b-button>
        <b-button v-on:click="deleteBoard">삭제</b-button>
      </div>
      <b-form v-if="show">
        <b-form-group id="input-group-1" label="댓글작성자" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="comment.cwriter"
            placeholder="Enter cwriter"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="댓글내용" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="comment.ccontent"
            placeholder="Enter ccontent"
            required
          ></b-form-input>
        </b-form-group>
        <b-button @click="writecomment" variant="primary">Submit</b-button>
      </b-form>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";

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
    },
    updateBoard() {
      this.$router.push({ name: "boardupdate" });
    },
    ...mapActions(["boardGetOne", "boardDelete", "commentWrite"]),
  },
  filters: {
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  computed: {
    ...mapState(["board", "cList"]),
  },
};
</script>

<style></style>
