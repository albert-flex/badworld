<template>
  <div class="content">
    <div class="aside">
      <a-user-profile :id="id" />
      <div class="tool">
        <button @click="toWrite">发布新作品</button>
        <button @click="toSelf">看自己</button>
      </div>
      <form class="query-form">
        <h1>查询作品</h1>
        <p>
          <label for="aid">作品Id</label>
          <input
            type="text"
            name="aid"
            id="aid"
            v-model="query_data.artifactId"
          />
        </p>
        <p>
          <label for="uid">用户Id</label>
          <input type="text" name="uid" id="uid" v-model="query_data.userId" />
        </p>
        <p>
          <label for="uname">用户名</label>
          <input
            type="text"
            name="uname"
            id="uname"
            v-model="query_data.userName"
          />
        </p>
        <p>
          <label for="title">标题</label>
          <input
            type="text"
            name="title"
            id="title"
            v-model="query_data.title"
          />
        </p>
        <p>
          <label for="timefrom">时间起点</label>
          <input
            type="date"
            name="timefrom"
            id="timefrom"
            v-model="query_data.startDate"
          />
        </p>
        <p>
          <label for="timeend">时间终点</label>
          <input
            type="date"
            name="timeend"
            id="timeend"
            v-model="query_data.endDate"
          />
        </p>
        <div class="tool">
          <button @click.stop.prevent="query">查询</button>
          <button @click.stop.prevent="clearQuery">清空</button>
          <button @click.stop.prevent="getNews">最新</button>
        </div>
      </form>
    </div>
    <div class="main">
      <component
        :is="compName"
        @view="view"
        @back="toPack"
        :is-view="isView"
        :view-id="viewId"
        :user-id="id"
        :items="items"
        :img-v="imgV"
      />

      <a-page-back :disable="compName == 'AArtifactWriter'" />
    </div>
  </div>
</template>

<script>
import UserProfile from "./UserProfile.vue";
import ArtifactPack from "./artifact/ArtifactPack.vue";
import PageBack from "./PageBack.vue";
import ArtifactWriter from "./artifact/ArtifactWriter.vue";
import axios from "axios";
axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/api";

export default {
  name: "ArtifactHome",
  props: {
    id: Number,
  },
  data() {
    return {
      nowComp: "AArtifactPack",
      comps: ["AArtifactPack", "AArtifactWrite"],
      isView: true,
      viewId: -1,
      query_data: {
        artifactId: 0,
        userId: 0,
        userName: "",
        title: "",
        startDate: "",
        endDate: "",
        pageIndex: 1,
        pageSize: 10,
      },
      page: {
        pageIndex: 1,
        pageSize: 10,
      },
      items: [],
      imgV: "",
    };
  },
  computed: {
    compName() {
      return this.nowComp;
    },
  },
  components: {
    AUserProfile: UserProfile,
    AArtifactPack: ArtifactPack,
    AArtifactWrite: ArtifactWriter,
    APageBack: PageBack,
  },
  methods: {
    view(id) {
      this.viewId = id;
      this.imgV="api/file_resource/download2?lib=artifact&ownId="+id;
      this.isView = true;
      this.nowComp = this.comps[1];
    },
    toWrite() {
      this.viewId = -1;
      this.isView = false;
      this.imgV="";
      this.nowComp = this.comps[1];
    },
    toEdit(id) {
      this.viewId = id;
      this.isView = false;
      this.nowComp = this.comps[1];
    },
    toPack() {
      this.viewId = -1;
      this.isView = false;
      this.nowComp = this.comps[0];
    },
    query() {
      axios
        .get("artifact/fetch/query", { params: this.query_data })
        .then((res) => {
          if (res.status == 200) {
            this.setItems(res.data.data);
            this.toPack();
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    clearQuery() {
      this.query_data.userId = 0;
      this.query_data.userName = "";
      this.query_data.title = "";
      this.query_data.startDate = "";
      this.query_data.endDate = "";
    },
    getNews() {
      axios
        .get("artifact/fetch/newest", { params: this.page })
        .then((res) => {
          if (res.status == 200) {
            this.setItems(res.data.data);
            this.toPack();
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    toSelf() {
      axios
        .get("artifact/by_user/" + this.id, { params: this.page })
        .then((res) => {
          if (res.status == 200) {
            this.setItems(res.data.data);
            this.toPack();
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    setItems(data) {
      for (let i = 0; i != data.length; ++i) {
        let v=data[i];
        v.url="api/file_resource/download2?lib=artifact&ownId=" + data[i].id;
      }
      this.items=data;
    },
  },
  mounted() {
    this.getNews();
  },
};
</script>

<style scoped>
button {
  color: #2c3e50;
  border-radius: 10px;
  padding: 2%;
}

.content {
  display: flex;
  padding: 0px;
}

.aside {
  width: 20%;
  margin: 2%;
}

.main {
  width: 70%;
  margin: 2%;
}

.query-form {
  position: sticky;
  top: 90px;
  margin-top: 10px;
  background-color: #2c3e50;
  color: white;
  padding: 10px;
  border-radius: 10px;
}

.query-form h1 {
  text-align: center;
}

.query-form p {
  display: flex;
}

.query-form label {
  width: 30%;
  margin: 5%;
  margin-top: 0px;
  margin-bottom: 0px;
  z-index: 1;
}

.query-form input {
  width: 50%;
  border-radius: 5px;
  padding: 2%;
  margin: 5%;
  margin-top: 0px;
  margin-bottom: 0px;
}

.tool {
  display: flex;
  list-style: none;
}

.tool button {
  width: 30%;
  margin: 10px;
  padding: 10px;
  align-self: center;
}

.artifact-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}

.card {
  flex: 1;
  height: 29%;
  min-width: 29%;
  max-width: 29%;
  background-color: #999;
  margin: 5px;
  padding: 5px;
  border-radius: 10px;
}

.card:hover {
  box-shadow: 10px 10px 50px #fff;
}

.card img {
  width: 100%;
  height: 100%;
}

.card h1 {
  margin: 1%;
  margin-top: 0px;
  padding: 0px;
  font-size: 1.2em;
  text-align: center;
}
</style>