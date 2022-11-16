<template>
  <div>
    <v-simple-table v-if="show">
      <template v-slot:default>
        <tr>
          <th colspan="1">분류</th>
          <td colspan="1">
            <v-select
              style="max-width: 100px; padding-top: 25px"
              v-model="board.category"
              :items="['전체', '기타']"
              label="전체"
              dense
              solo
            >
            </v-select>
          </td>
          <th colspan="1">작성자</th>
          <td colspan="9">
            <v-text-field
              v-model="board.writer"
              label="작성자를 입력하세요."
              required
            ></v-text-field>
          </td>
        </tr>
        <tr>
          <th colspan="1">제목</th>
          <td colspan="11">
            <v-text-field v-model="board.title" label="제목을 입력하세요." required></v-text-field>
          </td>
        </tr>
        <tr>
          <br /><br />
        </tr>
        <tr>
          <th colspan="1" style="padding-bottom: 40px">내용</th>
          <td colspan="11">
            <v-textarea outlined name="input-7-4" v-model="board.content"></v-textarea>
          </td>
        </tr>
      </template>
    </v-simple-table>

    <v-row align="center" justify="end">
      <v-col class="d-flex" cols="12" sm="2">
        <v-btn variant="outline-primary" @click="onSubmit">수정</v-btn>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <v-btn variant="outline-primary" @click="onReset">초기화</v-btn>
      </v-col>
    </v-row>

    <!-- <b-form v-if="show">
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
    </b-form> -->
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
