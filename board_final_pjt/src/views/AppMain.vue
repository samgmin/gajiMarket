<template>
  <div>
    <div id="aptDiv">
      아파트
      <div class="banner1-component">
        <v-container>
          <!-- -----------------------------------------------
            Start Banner1
        ----------------------------------------------- -->
          <v-row justify="center">
            <v-col cols="12" md="7" lg="6" class="d-flex align-center">
              <div class="text-center text-md-left">
                <h2 class="banner1-title font-weight-bold text-uppercase">
                  어떤 집을 찾고 계세요?
                </h2>
                <p class="op-8 font-weight-regular">
                  집 알아보기 힘드시죠? 집가지마켓에는 최근 10년 동안의 아파트 거래 내역이 있습니다.
                  아파트 거래 내역을 분석하여 성공적인 아파트 매매 하세요. 주변 상권, 로드뷰 등을
                  참고할 수 있습니다.
                </p>
                <div class="mt-16 pt-2">
                  <v-btn
                    large
                    rounded
                    :to="{ name: 'aptsearch' }"
                    class="btn-custom-md white--text"
                    depressed
                    color="purple"
                    elevation="0"
                  >
                    나만의 집 찾으러 가기
                  </v-btn>
                </div>
              </div>
            </v-col>
            <v-col cols="12" md="5" lg="5">
              <v-img :src="require('@/assets/apt-purple.gif')" alt="banner" />
            </v-col>
          </v-row>

          <!-- -----------------------------------------------
            End Banner1
        ----------------------------------------------- -->
        </v-container>
      </div>
    </div>
    <div id="boardDiv" class="reveal">
      게시판
      <div class="banner2-component">
        <v-container-fluid>
          <!-- -----------------------------------------------
            Start Banner2
        ----------------------------------------------- -->
          <v-row justify="center">
            <v-col cols="12" md="7" lg="6" class="d-flex align-center">
              <div class="text-center text-md-left">
                <h2 class="banner1-title font-weight-bold text-uppercase">
                  새 것같은 중고제품 찾고 계세요?
                </h2>
                <p class="op-8 font-weight-regular">
                  물가가 오른 요즘 새 제품을 구매하기 부담 되시나요? 바가지마켓 커뮤니티를 통해
                  이웃과 합리적인 거래 하세요. 공지글을 참고하시어 기본적인 매너를 지켜주세요.
                </p>
                <div class="mt-16 pt-2">
                  <v-btn
                    large
                    rounded
                    :to="{ name: 'boardlist' }"
                    class="btn-custom-md white--text"
                    depressed
                    color="purple"
                    elevation="0"
                  >
                    물건 건지러 가기
                  </v-btn>
                </div>
              </div>
            </v-col>
            <v-col cols="12" md="5" lg="5">
              <v-img :src="require('@/assets/shop.png')" alt="banner" />
            </v-col>
          </v-row>

          <!-- -----------------------------------------------
            End Banner2
        ----------------------------------------------- -->
        </v-container-fluid>
      </div>
    </div>
    <div id="photoDiv" class="reveal">
      <div class="banner1-component">
        <v-container>
          <!-- -----------------------------------------------
            Start Banner3
        ----------------------------------------------- -->
          <v-row justify="center">
            <v-col cols="12" md="7" lg="6" class="d-flex align-center">
              <div class="text-center text-md-left">
                <h2 class="banner1-title font-weight-bold text-uppercase">닮은꼴을 찾아보세염.</h2>
                <p class="op-8 font-weight-regular">
                  프로필 사진을 등록하여 추정 나이, 성별, 닮은꼴 연예인을 확인해보세요. 다른
                  회원에게 얼굴이 공개되니 매너있는 가지마켓을 함께 만들어보아요.
                </p>
                <div class="mt-16 pt-2">
                  <v-btn
                    large
                    rounded
                    :to="{ name: 'usersignup' }"
                    class="btn-custom-md white--text"
                    depressed
                    color="purple"
                    elevation="0"
                    v-if="userInfo == null"
                  >
                    회원가입
                  </v-btn>
                  <v-btn
                    large
                    rounded
                    :to="{ name: 'usermypage' }"
                    class="btn-custom-md white--text"
                    depressed
                    color="purple"
                    elevation="0"
                    v-if="userInfo != null"
                  >
                    마이페이지
                  </v-btn>
                </div>
              </div>
            </v-col>
            <v-col cols="12" md="5" lg="5">
              <v-img :src="require('@/assets/sangmin4.jpg')" alt="banner" />
            </v-col>
          </v-row>

          <!-- -----------------------------------------------
            End Banner3
        ----------------------------------------------- -->
        </v-container>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};

function reveal() {
  var reveals = document.querySelectorAll(".reveal");

  for (var i = 0; i < reveals.length; i++) {
    var windowHeight = window.innerHeight;
    var elementTop = reveals[i].getBoundingClientRect().top;
    var elementVisible = 150;

    if (elementTop < windowHeight - elementVisible) {
      reveals[i].classList.add("active");
    } else {
      reveals[i].classList.remove("active");
    }
  }
}

window.addEventListener("scroll", reveal);
</script>

<style>
#aptDiv {
}
#boardDiv {
  background-color: #ede7f6;
}
#photoDiv {
}

.banner-title {
  font-size: 42px;
  line-height: 54px;
  margin: 20px 0 15px;
}

.banner-subtitle {
  font-size: 21px;
}

.banner-wrapper {
  background: $accent;
  padding: 20px 0 20px;
  min-height: 600px;
  display: flex;
  align-items: center;
}

@media (max-width: 767px) {
  .banner-title {
    font-size: 29px;
    line-height: 40px;
  }
  .banner-wrapper {
    padding: 90px 0 20px;
  }
}

@media (min-width: 1200px) {
  .banner-subtitle {
    margin-right: 150px;
  }
}

/* Banner1 */
.banner1-component {
  padding: 60px 0;
  background-color: #ffd5c6;
  .banner1-title {
    font-size: 48px;
    line-height: 50px;
    margin: 20px 0 15px;
  }
}

@media (max-width: 991px) {
  .banner1-component {
    .banner1-title {
      font-size: 35px;
    }
  }
}

/* Banner2 */
.banner2-component {
  padding: 145px 0;

  .banner2-title {
    line-height: 50px;
    font-size: 36px;
    margin: 20px 0 15px;
  }
}

@media (max-width: 991px) {
  .banner2-component {
    padding: 45px 0;
    .banner2-title {
      font-size: 32px;
    }
  }
}

* {
  width: 100%;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Asap", sans-serif;
}

.reveal {
  position: relative;
  transform: translateY(200px);
  opacity: 0;
  transition: 1s all ease;
}

.reveal.active {
  transform: translateY(0);
  opacity: 1;
}
</style>
