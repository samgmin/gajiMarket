<template>
  <div>
    <!-- <div>
      <v-row align="center">
        <v-col class="d-flex" cols="12" sm="2"> 분류 </v-col>
        <v-col class="d-flex" cols="12" sm="2">
          <v-select v-model="board.category" :items="['전체', '기타']" label="전체" dense solo>
          </v-select>
        </v-col>
        <v-col class="d-flex" cols="12" sm="2"> 작성자 </v-col>
        <v-col class="d-flex" cols="12" sm="6">
          <v-text-field v-model="board.writer" label="작성자를 입력하세요." required></v-text-field>
        </v-col>
      </v-row>
    </div> -->

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
          <!-- <th colspan="1">작성자</th>
          <td colspan="9">
            <v-text-field
              v-model="board.writer"
              label="작성자를 입력하세요."
              required
            ></v-text-field>
          </td> -->
        </tr>
        <tr>
          <th colspan="1">제목</th>
          <td colspan="11">
            <v-text-field
              v-model="board.title"
              label="제목을 입력하세요."
              required
            ></v-text-field>
          </td>
        </tr>
        <tr>
          <br /><br />
        </tr>
        <tr>
          <th colspan="1" style="padding-bottom: 40px">내용</th>
          <td colspan="11">
            <v-textarea
              outlined
              name="input-7-4"
              v-model="board.content"
            ></v-textarea>
          </td>
        </tr>
      </template>
    </v-simple-table>

    <v-row align="center" justify="end">
      <v-col class="d-flex" cols="12" sm="1">
        <v-btn variant="outline-primary" @click="onSubmit">작성</v-btn>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const boardStore = "boardStore";

export default {
  data() {
    return {
      board: {
        title: "",
        writer: "",
        content: "",
        category: "전체",
      },
      show: true,
    };
  },
  created() {
    console.log(this.userInfo.username);
    this.board.writer = this.userInfo.username;
  },
  methods: {
    async onSubmit() {
      let request = {
        title: this.board.title,
        writer: this.board.writer,
        content: this.board.content,
        category: this.board.category,
      };
      console.log(request);
      await this.boardCreate(request);
      this.$router.push({ name: "boardlist" });
    },
    onReset() {},
    ...mapActions(boardStore, ["boardCreate"]),
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style></style>
