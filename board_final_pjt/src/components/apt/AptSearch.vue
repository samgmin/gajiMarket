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
    <div v-if="aptSearchList.length">
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-center">아파트이름</th>
              <th class="text-center">동이름</th>
              <th class="text-center">거래가격</th>
              <th class="text-center">거래년월</th>
              <th class="text-center">층</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(apt, index) in aptSearchList"
              :key="index"
              @click="titleClick(apt.dno)"
            >
              <td>{{ apt.apartmentName }}</td>
              <td>{{ apt.dongName }}</td>
              <td>{{ apt.dealAmount }}</td>
              <td>{{ apt.dealYear }}/{{ apt.dealMonth }}</td>
              <td>{{ apt.floor }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
      <div>
        <v-card class="mx-auto my-12" max-width="374">
          <template slot="progress">
            <v-progress-linear
              color="deep-purple"
              height="6"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-card-title>Cafe Badilico</v-card-title>

          <v-card-text>
            <v-row align="center" class="mx-0">
              <v-rating
                :value="4.5"
                color="amber"
                dense
                half-increments
                readonly
                size="14"
              ></v-rating>
            </v-row>

            <div>
              Small plates, salads & sandwiches - an intimate setting with 12
              indoor seats plus patio seating.
            </div>
          </v-card-text>

          <v-divider class="mx-4"></v-divider>

          <v-card-title>Tonight's availability</v-card-title>

          <v-card-actions>
            <v-btn color="deep-purple lighten-2" text> Reserve </v-btn>
          </v-card-actions>
        </v-card>
      </div>
      <div id="map" style="width: 500px; height: 400px"></div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const aptStore = "aptStore";

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
      lat: [],
      lng: [],
      map: null,
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
    aptSearchList() {
      console.log("agtList 변경됨");
      console.log(this.aptSearchList);
      if (window.kakao && window.kakao.maps) {
        this.loadMap();
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.loadMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?appkey=78b26667d20a38aa946ea1f6a8384730&autoload=false";
        document.head.appendChild(script);
      }
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
    loadMaker() {
      // 마커 이미지의 이미지 주소입니다
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < this.aptSearchList.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        let latlng = new kakao.maps.LatLng(
          this.aptSearchList[i].let,
          this.aptSearchList[i].lng
        );
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: latlng, // 마커를 표시할 위치
          title: this.aptSearchList[i].apartmentName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        marker.setMap(this.map);
      }
    },
    loadMap() {
      window.kakao.maps.load(() => {
        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(
            this.aptSearchList[0].let,
            this.aptSearchList[0].lng
          ),
          level: 5,
        };

        this.map = new window.kakao.maps.Map(container, options);
        this.loadMaker();
      });
    },
    ...mapActions(aptStore, [
      "getSidoNames",
      "getGugunNames",
      "getDongNames",
      "getSearchList",
    ]),
  },
  mounted() {},
  computed: {
    ...mapState(aptStore, [
      "sidoList",
      "gugunList",
      "dongList",
      "aptSearchList",
    ]),
  },
};
</script>

<style></style>
