import http from "@/api/http";

const aptStore = {
  namespaced: true,
  state: {
    sidoList: [],
    gugunList: [],
    dongList: [],
    aptSearchList: [],
  },
  getters: {},
  mutations: {
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
};

export default aptStore;
