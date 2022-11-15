<template>
  <div>
    <h1 class="underline">SSAFY 글 등록</h1>
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
      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>
<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      board: {
        title: "",
        writer: "",
        content: "",
      },
      show: true,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let request = {
        title: this.board.title,
        writer: this.board.writer,
        content: this.board.content,
      };
      console.log(request);
      http.post("/board", request).then(({ data }) => {
        let msg = "등록 처리 중 문제 발생 ~_~";
        if (data === "success") msg = "등록성공~_~_~_~~";
        alert(msg);
        this.$router.push("/");
      });
    },
    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.board.title = "";
      this.board.writer = "";
      this.board.content = "";
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
