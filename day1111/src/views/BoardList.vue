<template>
  <div>
    <h1 class="underline">글 목록</h1>
    <div v-if="boards.length">
      <table id="book-list">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(board, index) in boards" :key="index">
            <td>{{ board.bno }}</td>
            <td>
              <a @click="titleClick(board.bno)">{{ board.title }}</a>
            </td>
            <td>{{ board.writer }}</td>
            <td>{{ board.writeDate }}</td>
            <td>{{ board.readCount }}</td>
          </tr>
        </tbody>
      </table>
      <div>
        <a v-if="startPage > 1" @click="movePage(startPage - 1)">[이전]</a>
        <a v-for="i in endPage" :key="i" @click="movePage(i + startPage - 1)">
          [ {{ i + startPage - 1 }} ]
        </a>
        <a v-if="endPage < totalPage" @click="movePage(endPage + 1)">[다음]</a>
      </div>
    </div>
    <div v-else class="t ext-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      boards: [],
      startPage: "",
      currPage: "",
      totalPage: "",
      endPage: "",
    };
  },
  created() {
    this.movePage(1);
  },
  methods: {
    movePage(page) {
      let _this = this;
      fetch("http://localhost:8888/board/board?page=" + page, {
        method: "get",
      })
        .then((resp) => resp.json())
        .then((data) => {
          console.log(data);
          _this.boards = data.boardList;
          _this.startPage = data.startPage;
          _this.currPage = data.currPage;
          _this.totalPage = data.totalPage;
          _this.endPage = data.endPage;
        });
    },
    titleClick(bno) {
      this.$router.push({ path: "/read", query: { bno } });
    },
  },
};
</script>

<style></style>
