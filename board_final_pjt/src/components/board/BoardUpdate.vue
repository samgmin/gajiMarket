<template>
  <div>
    <h1 class="underline">SSAFY 글 수정</h1>
    <b-form v-if="show">
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
      <b-button @click="onSubmit" variant="primary">수정하기</b-button>
      <b-button @click="onReset" variant="danger">입력 초기화</b-button>
    </b-form>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      show: true,
    };
  },
  created() {
    this.onReset();
  },
  methods: {
    async onSubmit() {
      await this.boardUpdate(this.board);
      this.$router.push({ name: "boardlist" });
    },
    onReset() {},
    ...mapActions(["boardUpdate"]),
  },
  computed: {
    ...mapState(["board"]),
  },
};
</script>

<style></style>
