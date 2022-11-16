<template>
  <div>
    <h3>아파트 매물 검색</h3>
    <v-container fluid>
      <v-row align="center">
        <v-col class="d-flex">
          <v-select
            :items="sidoList"
            label="시도 선택"
            v-model="selectSido"
            solo
          ></v-select>
        </v-col>
        <v-col class="d-flex">
          <v-select
            :items="gugunList"
            label="구군 선택"
            v-model="selectGugun"
            dense
            solo
          ></v-select>
        </v-col>
        <v-col class="d-flex">
          <v-select
            :items="dongList"
            label="동 선택"
            v-model="selectDong"
            dense
            solo
          ></v-select>
        </v-col>
        <v-col class="d-flex">
          <v-select
            :items="yearList"
            label="년 선택"
            v-model="selectYear"
            dense
            solo
          ></v-select>
        </v-col>
        <v-col class="d-flex" cols="3" sm="3">
          <v-select
            :items="monthList"
            label="월 선택"
            v-model="selectMonth"
            dense
            solo
          ></v-select>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <v-btn variant="outline-primary" @click="selectList"> 검색</v-btn>
        </v-col>
      </v-row>
    </v-container>
    여기부터 검색결과
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  created() {
    this.getSidoNames();
  },
  data() {
    return {
      yearList: [
        2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011,
        2010, 2009,
      ],
      monthList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
      selectSido: "",
      selectGugun: "",
      selectDong: "",
      selectYear: "",
      selectMonth: "",
    };
  },
  watch: {
    selectSido() {
      this.getGugunNames(this.selectSido);
    },
    selectGugun() {
      let data = { sidoName: this.selectSido, gugunName: this.selectGugun };
      this.getDongNames(data);
    },
  },
  methods: {
    selectList() {
      let data = {
        sido: this.selectSido,
        dong: this.selectDong,
        year: this.selectYear,
        month: this.selectMonth,
      };
      this.getSearchList(data);
    },
    ...mapActions([
      "getSidoNames",
      "getGugunNames",
      "getDongNames",
      "getSearchList",
    ]),
  },
  computed: {
    ...mapState(["sidoList", "gugunList", "dongList"]),
  },
};
</script>

<style>
</style>