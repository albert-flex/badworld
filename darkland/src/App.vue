<template>
  <div>
    <a-nav-bar
      @to-home="toHome"
      @to-about-site="toAboutSite"
      @logined="logined"
    />
    <component :is="currentHome" @to-post="toPost" @to-artifact="toArtifact" :id="id"/>
    <a-foot-bar />
  </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import FootBar from "./components/FootBar.vue";
import HomePage from "./components/HomePage.vue";
import AboutSite from "./components/AboutSite.vue";
import PostHome from "./components/PostHome.vue";
import ArtifactHome from "./components/ArtifactHome.vue";
import axios from "axios";
axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/api";

export default {
  name: "App",
  data() {
    return {
      currentTab: "AHomePage",
      Tabs: ["AHomePage", "AAboutSite", "APostHome", "AArtifactHome"],
      islogined: false,
      id: 0,
    };
  },
  computed: {
    currentHome() {
      return this.currentTab;
    },
  },
  components: {
    ANavBar: NavBar,
    AHomePage: HomePage,
    AAboutSite: AboutSite,
    AFootBar: FootBar,
    APostHome: PostHome,
    AArtifactHome: ArtifactHome,
  },
  methods: {
    toPost() {
      if (!this.islogined) {
        alert("未登录，请先登录");
      } else {
        this.currentTab = this.Tabs[2];
      }
    },
    logined() {
      this.islogined = true;
      axios
        .post("/user/self")
        .then(res=>{
          if(res.status===200){
            this.id=res.data;
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    toHome() {
      this.currentTab = this.Tabs[0];
      console.log("to Home!");
    },
    toAboutSite() {
      this.currentTab = this.Tabs[1];
    },
    toArtifact() {
      if (!this.islogined) {
        alert("未登录，请先登录");
      } else {
        this.currentTab = this.Tabs[3];
      }
    },
  },
  mounted() {},
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin: 0px;
}

body {
  padding: 0px;
  margin: 0px;
  scrollbar-highlight-color: #2c3e50;
}
</style>
