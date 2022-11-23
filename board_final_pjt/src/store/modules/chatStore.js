import http from "@/api/http";

const chatStore = {
  namespaced: true,
  state: {
    roomList: [],
    chatList: [],
  },
  getters: {},
  mutations: {
    SELECT_CHAT_ROOM_LIST(state, payload) {
      state.roomList = payload;
    },
    SELECT_CHAT_LIST(state, payload) {
      state.chatList = payload;
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
    selectChatList({ commit }, payload) {
      http.get("/chat/chats?roomId=" + payload).then(({ data }) => {
        console.log(data);
        commit("SELECT_CHAT_LIST", data);
      });
    },
  },
};

export default chatStore;
