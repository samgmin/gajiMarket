<template>
  <div>
    <h1 class="underline">SSAFY 글 등록</h1>
    <b-form @reset="onReset" v-if="show">
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
      <b-button @click="onSubmit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>
<script>
import { mapActions } from "vuex";

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
    async onSubmit() {
      let request = {
        title: this.board.title,
        writer: this.board.writer,
        content: this.board.content,
      };
      console.log(request);
      await this.boardCreate(request);
      this.$router.push({ name: "boardlist" });
    },
    onReset() {},
    ...mapActions(["boardCreate"]),
  },
};
</script>

<style></style>