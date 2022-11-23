<template>
  <v-container class="bv-example-row mt-3 text-center" style="width: 1200px; margin-bottom: 100px">
    <h3 class="underline-hotpink" style="margin-top: 100px; margin-bottom: 50px">
      <b-icon icon="journals"></b-icon> 채팅
    </h3>

    <div>
      <v-col class="d-flex justify-end">
        <v-text-field
          type="text"
          v-model="chatRoomName"
          label="이름"
          style="max-width: 120px; margin-top: -10px"
        ></v-text-field>
        &nbsp;&nbsp;&nbsp;
        <v-btn variant="outline-primary" @click="makeChatRoom">채팅방 개설</v-btn>
      </v-col>
      <div v-if="roomList.length">
        <v-simple-table>
          <template v-slot:default>
            <colgroup>
              <col width="10%" />
              <col width="20%" />
              <col width="60%" />
              <col width="20%" />
            </colgroup>
            <thead>
              <tr>
                <th class="text-center">번호</th>
                <th class="text-center">이름</th>
                <th class="text-center">설명</th>
                <th class="text-center">관리자</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(chatRoom, index) in roomList"
                :key="index"
                @click="enterChatRoom(chatRoom)"
              >
                <td>{{ index + 1 }}</td>
                <td>{{ chatRoom.name }}</td>
                <td>{{ chatRoom.decription }}</td>
                <td>{{ chatRoom.roomMaker }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>
    </div>

    <!-- 채팅방 dialog -->
    <v-dialog v-model="dialog" width="500px" height="1000px">
      <v-card style="overflow-x: hidden" class="chatbox" ref="chatbox">
        <v-toolbar color="#7E57C2" width="500px" dark style="position: fixed; z-index: 3">
          <b>{{ this.nowroomname }}</b>
        </v-toolbar>
        <v-list style="height: 660px; margin-top: 60px">
          <div v-for="(item, idx) in recvList" :key="idx">
            <v-list-item class="d-flex justify-end" v-if="item.writer === userInfo.username">
              <span>{{ item.message }}</span>
            </v-list-item>
            <v-list-item class="d-flex justify-start" v-if="item.writer !== userInfo.username">
              <span
                ><b>{{ item.writer }}</b
                >&nbsp;&nbsp;{{ item.message }}</span
              >
            </v-list-item>
          </div>
        </v-list>

        <div class="inner-wrap" style="position: fixed">
          <v-text-field
            style="overflow-x: hidden; width: 500px"
            v-model="message"
            placeholder="보낼 메세지를 입력하세요."
            solo
            @keyup="sendMessage"
          ></v-text-field>
        </div>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import { mapState, mapActions } from "vuex";

const chatStore = "chatStore";
const userStore = "userStore";

export default {
  name: "App",
  data() {
    return {
      nowroomid: "",
      nowroomname: "",
      message: "",
      recvList: [],
      chatRoom: [],
      chatRoomName: "",
      dialog: false,
    };
  },
  created() {
    this.selectChatRoomList();
  },
  methods: {
    makeChatRoom() {
      this.createChatRoom(this.chatRoomName);
      this.selectChatRoomList();
    },
    enterChatRoom(chatroom) {
      // 방 입장 시 소켓 연결을 시도합니다.
      console.log(chatroom.roomId);
      this.connect(chatroom.roomId);
      this.nowroomname = chatroom.name;
      this.dialog = true;
    },
    sendMessage(e) {
      if (e.keyCode === 13 && this.userName !== "" && this.message !== "") {
        this.send();
        this.message = "";
      }
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          roomId: this.nowroomid,
          writer: this.userInfo.username,
          message: this.message,
        };
        console.log("메세지", msg);
        this.stompClient.send("/pub/chat/message", JSON.stringify(msg), {});
      }
    },
    connect(roomid) {
      const serverURL = "http://localhost:8888/board/chat";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.

          this.selectChatList(roomid);
          this.nowroomid = roomid;
          console.log("nowroomid", this.nowroomid);
          this.stompClient.subscribe("/sub/chat/room/" + this.nowroomid, (res) => {
            console.log("구독으로 받은 메시지 입니다.", res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body));
          });
          let data = {
            roomId: this.nowroomid,
            writer: this.userInfo.username,
          };
          console.log(this.userInfo);
          this.stompClient.send("/pub/chat/enter", JSON.stringify(data), {});
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
    },
    ...mapActions(chatStore, ["createChatRoom", "selectChatRoomList", "selectChatList"]),
  },
  computed: {
    ...mapState(chatStore, ["roomList", "chatList"]),
    ...mapState(userStore, ["userInfo"]),
  },
  watch: {
    dialog() {
      if (!this.dialog) {
        console.log("다이아로그 끔");
        this.stompClient.disconnect();
      }
    },
    chatList() {
      this.recvList = [];
      this.recvList = this.chatList;
    },
    recvList() {
      this.$nextTick(() => {
        let chatbox = this.$refs.chatbox;
        chatbox.scrollTo({ top: chatbox.scrollHeight, behavior: "smooth" });
      });
    },
  },
};
</script>

<style>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(128, 30, 255, 0.3) 30%);
}
</style>
