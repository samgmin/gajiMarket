import Vue from "vue";
import Vuex from "vuex";
import boardStore from "@/store/modules/boardStore";
import aptStore from "@/store/modules/aptStore";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    boardStore, aptStore,
  },
});
