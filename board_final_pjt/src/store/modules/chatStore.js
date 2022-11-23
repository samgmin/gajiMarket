import http from "@/api/http";

const chatStore = {
  namespaced: true,
  state: {
    roomList: [],
  },
  getters: {},
  mutations: {
    SELECT_CHAT_ROOM_LIST(state, payload) {
      state.roomList = payload;
    },
  },
  actions: {
    createChatRoom({ commit }, payload) {
      console.log(commit);
      console.log(payload);
      http.post("/chat/room?name=" + payload).then(({ data }) => {
        let msg = "방 생성 중 문제 발생 ~_~";
        if (data === "success") {
          msg = "방 생성 성공~_~_~_~~";
          console.log(data);
        }
        alert(msg);
      });
    },
    selectChatRoomList({ commit }) {
      http.get("/chat/rooms").then(({ data }) => {
        console.log(data);
        commit("SELECT_CHAT_ROOM_LIST", data);
      });
    },
  },
};

export default chatStore;
