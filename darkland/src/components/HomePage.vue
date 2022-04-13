<template>
  <div class="welcome">
    <p>WELCOME TO</p>
    <p>BAD WORLD</p>
  </div>
  <div class="content">
    <div class="post">
      分享生活
      <button @click="$emit('toPost')">
        <img src="../assets/post.svg" alt="" />
      </button>
    </div>
    <div class="artifact">
      分享作品
      <button @click="$emit('toArtifact')">
        <img src="../assets/artifact.svg" alt="" />
      </button>
    </div>
  </div>
  <div class="oneword">
    <p id="word">
      我愿意真有所谓鬼魂，真有所谓地狱，那么，即使在孽风怒吼之中，我也将寻觅子君，
      当面说出我的悔恨和悲哀，祈求她的饶恕；否则，地狱的毒焰将围绕我，猛烈地烧尽我的悔恨和悲哀。
    </p>
    <p id="author">——鲁迅</p>
  </div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    return {
      logined: false,
    };
  },
  emits: ["toPost", "toArtifact"],
  methods: {
    toPost() {
      //TODO: To Post Page
      if (!this.logined) {
        alert("你未登录，请先登录");
      } else {
        this.toPostImpl();
      }
    },
    toPostImpl() {},
    toArtifact() {
      //TODO: To Artifact Page
      if (!this.logined) {
        alert("你未登录，请先登录");
      } else {
        this.toArtifactImpl();
      }
    },
    toArtifactImpl() {},
  },
};
</script>

<style scoped>
.welcome {
  margin: 5%;
  font-size: 6em;
}

.welcome p {
  margin: 0px;
  text-align: center;
  line-height: 1.2em;
  text-decoration: line-through;
  text-decoration-color: gainsboro;
  text-decoration-style: wavy;
  text-shadow: 5px 5px 10px gray;
}

.content {
  margin: 5%;
  display: flex;
  text-align: center;
  font-size: 3em;
}

.post {
  padding-left: 15%;
  padding-right: 15%;
  width: 30%;
  margin-top: 0px;
}

.content img:hover {
  background-color: whitesmoke;
  box-shadow: 10px 10px 100px gray;
  border-radius: 20px;
  cursor: pointer;
}

.artifact {
  padding-left: 15%;
  padding-right: 15%;
  width: 30%;
  margin-top: 0px;
}

.artifact button,
.post button {
  background: none;
  border: none;
  width: 100%;
  height: 100%;
}

.oneword {
  margin: 100px;
  margin-top: 0px;
  padding: 2.5%;
  border-radius: 20px;
  background-color: #2c3e50;
  color: white;
}

#word {
  text-indent: 2em;
  font-size: 2.3em;
  line-height: 1.75em;
}

#author {
  text-align: right;
  font-size: 2.3em;
  line-height: 1.75em;
}
</style>