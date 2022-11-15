<template>
  <div>
    <div>
      <select v-model="sort">
        <option value="bno">글번호</option>
        <option value="readCount">조회수</option>
      </select>
      <select v-model="category">
        <option value="전체">전체</option>
        <!-- <option :value="other">조회수</option> -->
      </select>
      <select v-model="key">
        <option value="title">제목</option>
        <option value="writer">작성자</option>
      </select>
      <input type="text" id="word" name="word" v-model="word" ref="word" />
      <b-button variant="outline-primary" @click="selectList">검색</b-button>
    </div>
    <div v-if="boardList.length">
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
          <tr v-for="(board, index) in boardList" :key="index">
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
        <a v-for="i in endPage" :key="i">
          <a
            v-if="i + startPage - 1 <= endPage"
            @click="movePage(i + startPage - 1)"
          >
            [ {{ i + startPage - 1 }} ]
          </a>
        </a>
        <a v-if="endPage < totalPage" @click="movePage(endPage + 1)">[다음]</a>
      </div>
    </div>
    <div v-else class="text-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  created() {
    this.movePage(1);
  },
  data() {
    return {
      sort: "",
      category: "",
      word: "",
      key: "",
    };
  },

  methods: {
    movePage(page) {
      this.boardGetList(page);
    },
    titleClick(bno) {
      this.$router.push({ name: "boardread", query: { bno } });
    },
    selectList() {
      this.boardGetSearch({
        pg: 1,
        spp: 10,
        category: this.category,
        sort: this.sort,
        key: this.key,
        word: this.word,
      });
    },
    ...mapActions(["boardGetList", "boardGetSearch"]),
  },
  computed: {
    ...mapState(["boardList", "startPage", "totalPage", "endPage", "currPage"]),
  },
};
</script>

<style>
</style>