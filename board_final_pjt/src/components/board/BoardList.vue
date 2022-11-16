<template>
  <div>
    <div>
      <v-row align="center">
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
            v-model="sort"
            :items="[
              { name: '최신순', value: 'BNO' },
              { name: '조회수', value: 'READ_COUNT' },
            ]"
            item-text="name"
            item-value="value"
            label="최신순"
            dense
            solo
          >
            <!-- <option value="bno">최신순</option>
            <option value="readCount">조회수</option> -->
          </v-select>
        </v-col>
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
            v-model="category"
            :items="[
              { name: '전체', value: '전체' },
              { name: '의류', value: '의류' },
            ]"
            item-text="name"
            item-value="value"
            label="전체"
            dense
            solo
          >
            <!-- <option value="전체">전체</option> -->
            <!-- <option :value="other">조회수</option> -->
          </v-select>
        </v-col>
      </v-row>

      <v-row align="center">
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
            v-model="key"
            :items="[
              { name: '제목', value: 'title' },
              { name: '작성자', value: 'writer' },
            ]"
            item-text="name"
            item-value="value"
            label="제목"
            dense
            solo
          >
            <!-- <option value="title">제목</option>
            <option value="writer">작성자</option> -->
          </v-select>
        </v-col>
        <v-col class="d-flex" cols="12" sm="6">
          <v-text-field
            type="text"
            id="word"
            name="word"
            v-model="word"
            ref="word"
            label="검색어"
          ></v-text-field>
          <v-btn variant="outline-primary" @click="selectList">검색</v-btn>
        </v-col>
      </v-row>
    </div>

    <div v-if="boardList.length">
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-center">번호</th>
              <th class="text-center">분류</th>
              <th class="text-center">제목</th>
              <th class="text-center">작성자</th>
              <th class="text-center">작성일시</th>
              <th class="text-center">조회수</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(board, index) in boardList" :key="index">
              <td>{{ board.bno }}</td>
              <td>{{ board.category }}</td>
              <td>
                <a @click="titleClick(board.bno)">{{ board.title }}</a>
              </td>
              <td>{{ board.writer }}</td>
              <td>{{ board.writeDate }}</td>
              <td>{{ board.readCount }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
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
      sort: "BNO",
      category: "전체",
      word: "",
      key: "title",
    };
  },

  methods: {
    movePage(page) {
      this.boardGetSearch({
        pg: page,
        spp: 10,
        category: this.category,
        sort: this.sort,
        key: this.key,
        word: this.word,
      });
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
  watch: {
    sort() {
      this.selectList();
    },
    category() {
      this.selectList();
    },
  },
};
</script>

<style></style>
