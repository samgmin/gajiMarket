<template>
  <div>
    <h1 class="underline">SSAFY 글 수정</h1>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-1" label="제목 :" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="board.title"
          placeholder="Enter title"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="작성자 : " label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="board.writer"
          placeholder="Enter writer"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="내용 : " label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="board.content"
          placeholder="Enter content"
          required
        ></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary">수정하기</b-button>
      <b-button type="reset" variant="danger">입력 초기화</b-button>
    </b-form>
  </div>
</template>
<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      board: {
        bno: "",
        title: "",
        writer: "",
        content: "",
      },
      show: true,
    };
  },
  created() {
    this.onReset();
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      http.put("/board", this.board).then(({ data }) => {
        let msg = "수정 처리 중 문제 발생 ~_~";
        if (data === "success") msg = "수정성공~_~_~_~~";
        alert(msg);
        this.$router.push("/");
      });
    },
    onReset() {
      // Reset our form values
      let _this = this;
      let bno = this.$route.params.bno;
      console.log("/board/read?bno=" + bno);
      http.get("/board/read?bno=" + bno).then((resp) => {
        console.log(resp.data);
        _this.board = resp.data.board;
      });
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style></style>
