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
      <div class="regist_form" v-if="Clist.length">
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
            <tr v-for="(comment, index) in Clist" :key="index">
              <td>{{ comment.cwriter }}</td>
              <td>{{ comment.ccontent }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div style="padding-top: 15px">
        <b-button :to="`/update/${board.bno}`">수정</b-button>
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
import http from "@/util/http-common";

export default {
  data: function () {
    return {
      board: {},
      comment: {
        bno: this.$route.query.bno,
        cwriter: "",
        ccontent: "",
      },
      bno: this.$route.query.bno,
      Clist: [],
      show: true,
    };
  },
  // props: ["money"],
  props: ["cup"],
  created() {
    this.findboard(this.$route.query.bno);
  },

  methods: {
    findboard(bno) {
      let _this = this;
      console.log(bno);
      fetch(
        "http://localhost:8888/board/board/read?bno=" + this.$route.query.bno,
        {
          method: "get",
        }
      )
        .then((resp) => resp.json())
        .then((data) => {
          _this.Clist = data.Clist;
          _this.board = data.board;
        });
    },
    enterToBr(str) {
      // 문자열에 enter값을 <br />로 변경.(html상에서 줄바꿈 처리)
      return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
    deleteBoard() {
      http.delete(`/board/${this.$route.query.bno}`).then(({ data }) => {
        let msg = "삭제 처리 중 문제 발생 ~_~";
        if (data === "success") msg = "삭제 성공~_~_~_~~";
        alert(msg);
        this.$router.push("/");
      });
    },
    writecomment() {
      let _this = this;
      let bno = this.$route.query.bno;
      http.post(`/board/cwrite`, this.comment).then(({ data }) => {
        let msg = "댓글 처리 중 문제 발생 ~_~";
        if (data === "success") msg = "댓글 작성 성공~_~_~_~~";
        alert(msg);
        _this.findboard(bno);
      });
    },
  },
  filters: {
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
