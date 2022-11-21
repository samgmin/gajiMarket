<template>
  <div>
    <h3>관심지역~</h3>
    <v-sheet
      class="mx-auto"
      max-width="1500"
      elevation="8"
      v-if="aptInterestList.length"
    >
      <v-slide-group multiple show-arrows class="pa-4">
        <v-slide-item v-for="(interest, index) in aptInterestList" :key="index">
          <v-card class="ma-4" max-width="374" v-if="aptInterestList.length">
            <v-img
              height="250"
              :src="'https://loremflickr.com/320/240/apartment'"
            ></v-img>

            <v-card-title>{{ interest.aptname }}</v-card-title>

            <v-card-text>
              <div class="my-4 text-subtitle-1">
                {{ interest.dongname }}<br />
                법정코드 : {{ interest.aptcode }} <br />
              </div>
            </v-card-text>
            <v-btn color="deep-purple lighten-2" text
              ><a
                :href="`https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=${interest.dongname}${interest.aptname}`"
                target="”_blank”"
              >
                네이버검색
              </a>
            </v-btn>

            <v-btn
              color="deep-purple lighten-2"
              @click="deleteInterest(interest.aptname)"
              text
              >관심지역 해제</v-btn
            >
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const aptStore = "aptStore";
const userStore = "userStore";
export default {
  data() {
    return {
      url: [],
    };
  },
  created() {
    this.AptInterestList(this.userInfo.userid);
  },
  watch: {},
  methods: {
    deleteInterest(aname) {
      let data = {
        userid: this.userInfo.userid,
        aptname: aname,
      };
      this.AptInterestDelete(data);
    },
    ...mapActions(aptStore, ["AptInterestList", "AptInterestDelete"]),
  },
  computed: {
    ...mapState(aptStore, ["aptInterestList"]),
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style>
</style>
