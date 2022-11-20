<template>
  <div style="width: 800px; margin: 0 auto">
    <v-form id="sendForm" ref="sendFrom" enctype="multipart/form-data">
      <v-simple-table v-if="show">
        <template v-slot:default>
          <colgroup>
            <col width="20%" />
            <col width="80%" />
          </colgroup>
          <tr>
            <th class="text-left" colspan="1">분류</th>
            <td class="text-left" colspan="1">
              <v-select
                v-if="board.writer !== '관리자'"
                style="max-width: 100px; padding-top: 25px"
                name="category"
                v-model="board.category"
                :items="['전체', '기타']"
                label="전체"
                dense
                solo
              >
              </v-select>
              <v-select
                v-if="board.writer === '관리자'"
                style="max-width: 100px; padding-top: 25px"
                name="category"
                v-model="board.category"
                :items="['공지']"
                label="공지"
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
            <th class="text-left" colspan="1">제목</th>
            <td class="text-left" colspan="11">
              <v-text-field
                name="title"
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
            <th class="text-left" colspan="1" style="padding-bottom: 40px">내용</th>
            <td class="text-left" colspan="11">
              <v-textarea outlined name="content" v-model="board.content"></v-textarea>
            </td>
          </tr>

          <tr>
            <th class="text-left" colspan="1">이미지 첨부</th>
            <td class="text-left" colspan="11">
              <v-file-input type="file" name="uploadFile" v-model="file" />
            </td>
          </tr>
        </template>
      </v-simple-table>
    </v-form>
    <br />
    <v-row align="center" justify="end">
      <v-col class="d-flex flex-row-reverse" cols="12" sm="1">
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
        category: "",
      },
      file: [],
      show: true,
    };
  },
  created() {
    console.log(this.userInfo.username);
    this.board.writer = this.userInfo.username;
  },
  methods: {
    async onSubmit() {
      var fd = new FormData();
      fd.append("uploadFile", this.file);
      fd.append("title", this.board.title);
      fd.append("writer", this.board.writer);
      fd.append("content", this.board.content);
      fd.append("category", this.board.category);
      // let request = {
      //   title: this.board.title,
      //   writer: this.board.writer,
      //   content: this.board.content,
      //   category: this.board.category,
      // };
      // console.log(request);
      // fd.append("board", request);
      console.log(fd);
      await this.boardCreate(fd);
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
