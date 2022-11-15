import Vue from 'vue'
import Vuex from 'vuex'
import http from "@/api/http";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    boardList: [],
    startPage: "",
    currPage: "",
    totalPage: "",
    endPage: "",
    board: {},
    Clist: [],

  },
  getters: {
  },
  mutations: {
    BOARD_GET_LIST(state, payload) {
      state.boardList = payload.boardList;
      state.startPage = payload.startPage;
      state.currPage = payload.currPage;
      state.totalPage = payload.totalPage;
      state.endPage = payload.endPage;
      console.log(state);
    },
    BOARD_GET_ONE(state, payload) {
      state.Clist = payload.Clist;
      state.board = payload.board;
    }
  },
  actions: {
    boardGetList({ commit }, payload) {
      console.log(payload);
      http.get("/board?page=" + payload).then(({ data }) => {
        console.log('data : ', data);
        console.log('commit : ', commit);
        commit("BOARD_GET_LIST", data);
      });
    },
    boardGetOne({ commit }, payload) {
      console.log(payload);
      console.log('commit : ', commit);
      http.get("/board/read?bno=" + payload).then(({ data }) => {
        console.log(data);
        commit("BOARD_GET_ONE", data);
      });
    },
  },
  modules: {
  }
})
