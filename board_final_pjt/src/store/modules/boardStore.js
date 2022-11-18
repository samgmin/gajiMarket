import http from "@/api/http";

const boardStore = {
  namespaced: true,
  state: {
    boardList: [],
    startPage: "",
    currPage: "",
    totalPage: "",
    endPage: "",
    board: {},
    cList: [],
  },
  getters: {},
  mutations: {
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
    BOARD_RECOMMEND_UPDATE(state, payload) {
      state.board.recommend = payload;
    },
  },
  actions: {
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
    boardRecommendUpdate({ commit }, payload) {
      console.log("조회수 업데이트");
      console.log(payload);
      http
        .get("/board/recommend?userid=" + payload.userid + "&bno=" + payload.bno)
        .then(({ data }) => {
          console.log(data);
          commit("BOARD_RECOMMEND_UPDATE", data);
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
    commentUpdate({ commit }, payload) {
      console.log(payload);
      http.post(`board/comment/update`, payload).then(({ data }) => {
        let msg = "댓글 처리 중 문제 발생 ~_~";
        if (data.msg === "success") {
          msg = "댓글 수정 성공~_~_~_~~";
          console.log(data);
          commit("COMMENT_WRITE", data.cList);
        }
        alert(msg);
      });
    },
    commentDelete({ commit }, payload) {
      console.log(commit);
      console.log(payload);
      http
        .delete("/board/comment/delete?bno=" + payload.bno + "&cno=" + payload.cno)
        .then(({ data }) => {
          let msg = "댓글 처리 중 문제 발생 ~_~";
          if (data.msg === "success") {
            msg = "댓글 삭제 성공~_~_~_~~";
            console.log(data);
            commit("COMMENT_WRITE", data.cList);
          }
          alert(msg);
        });
    },
  },
};

export default boardStore;
