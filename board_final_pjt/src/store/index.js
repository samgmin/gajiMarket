import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    /////////////////////////////////////////////////////////////////////
    //여기부터 게시판 관련
    boardList: [],
    startPage: "",
    currPage: "",
    totalPage: "",
    endPage: "",
    board: {},
    cList: [],
    /////////////////////////////////////////////////////////////////////
    //여기부터 아파트 관련
    sidoList: [],
    gugunList: [],
    dongList: [],
    aptSearchList: [],
  },
  getters: {
    getBoard(state) {
      return state.board;
    },
    getAptSearchList(state) {
      return state.aptSearchList;
    },
  },
  mutations: {
    /////////////////////////////////////////////////////////////////////
    //여기부터 게시판 관련
    BOARD_GET_LIST(state, payload) {
      state.boardList = payload.boardList;
      state.startPage = payload.startPage;
      state.currPage = payload.currPage;
      state.totalPage = payload.totalPage;
      state.endPage = payload.endPage;
    },
    BOARD_GET_ONE(state, payload) {
      state.cList = payload.cList;
      state.board = payload.board;
    },
    BOARD_UPDATE(state, payload) {
      state.board = payload;
    },
    COMMENT_WRITE(state, payload) {
      state.cList = payload;
    },
    /////////////////////////////////////////////////////////////////////
    //여기부터 아파트 관련

    GET_SIDO_NAMES(state, payload) {
      state.sidoList = payload;
    },
    GET_GUGUN_NAMES(state, payload) {
      state.gugunList = payload;
    },
    GET_DONG_NAMES(state, payload) {
      state.dongList = payload;
    },
    GET_SEARCH_LIST(state, payload) {
      state.aptSearchList = payload;
    },
  },
  actions: {
    /////////////////////////////////////////////////////////////////////
    //여기부터 게시판 관련
    boardGetList({ commit }, payload) {
      console.log(payload);
      http.get("/board?page=" + payload).then(({ data }) => {
        console.log("data : ", data);
        console.log("commit : ", commit);
        commit("BOARD_GET_LIST", data);
      });
    },
    boardGetOne({ commit }, payload) {
      http.get("/board/read?bno=" + payload).then(({ data }) => {
        console.log(data);
        commit("BOARD_GET_ONE", data);
      });
    },
    boardGetSearch({ commit }, payload) {
      http.post("/board/list", payload).then(({ data }) => {
        commit("BOARD_GET_LIST", data);
      });
    },
    boardCreate({ commit }, payload) {
      console.log(commit);
      http.post("/board", payload).then(({ data }) => {
        console.log(data);
        alert(data);
      });
    },
    boardDelete({ commit }, payload) {
      console.log(commit);
      http.delete("/board/" + payload).then(({ data }) => {
        console.log(data);
        alert(data);
      });
    },
    boardUpdate({ commit }, payload) {
      console.log(payload);
      http.put("/board/modify", payload).then(({ data }) => {
        console.log(data);
        alert(data);
        commit("BOARD_UPDATE", payload);
      });
    },
    commentWrite({ commit }, payload) {
      console.log(payload);
      console.log("commit : ", commit);
      http.post(`/board/comment`, payload).then(({ data }) => {
        let msg = "댓글 처리 중 문제 발생 ~_~";
        if (data.msg === "success") {
          msg = "댓글 작성 성공~_~_~_~~";
          console.log(data);
          commit("COMMENT_WRITE", data.cList);
        }
        alert(msg);
      });
    },
    /////////////////////////////////////////////////////////////////////
    //여기부터 아파트 관련
    getSidoNames({ commit }) {
      http.get("/apt/sido").then(({ data }) => {
        console.log(data);
        commit("GET_SIDO_NAMES", data);
      });
    },
    getGugunNames({ commit }, payload) {
      http.get("/apt/gugun?sidoName=" + payload).then(({ data }) => {
        console.log(data);
        commit("GET_GUGUN_NAMES", data);
      });
    },
    getDongNames({ commit }, payload) {
      console.log(payload);
      http
        .get("/apt/dong?sidoName=" + payload.sidoName + "&gugunName=" + payload.gugunName)
        .then(({ data }) => {
          console.log(data);
          commit("GET_DONG_NAMES", data);
        });
    },
    getSearchList({ commit }, payload) {
      console.log(payload.sido);
      console.log(payload.dong);

      http
        .get(
          "/apt/search?sido=" +
            payload.sido +
            "&dong=" +
            payload.dong +
            "&dealYear=" +
            payload.year +
            "&dealMonth=" +
            payload.month
        )
        .then(({ data }) => {
          console.log(data);
          commit("GET_SEARCH_LIST", data);
        });
    },
  },
  modules: {},
});
