<template>
  <div class="post">
    <div class="aside">
      <a-mask-back :showMask="showPost" />
      <form
        class="post-card"
        :style="showPost ? 'display:block' : 'display:none'"
      >
        <p>
          <input
            type="text"
            placeholder="输入标题..."
            v-model="post_data.title"
          />
          <button type="submit" @click.stop.prevent="post">发送</button>
          <button @click.stop.prevent="closePost">关闭</button>
        </p>
        <p>
          <textarea
            name="content"
            id="content"
            placeholder="请输入帖子内容..."
            v-model="post_data.content"
          ></textarea>
        </p>
      </form>

      <a-user-profile :id="id" />
      <ul class="post-button">
        <li><button @click="openPost">分享生活</button></li>
        <li><button @click="getSelf">看自己</button></li>
      </ul>
      <form class="query-form">
        <h1>查询帖子</h1>
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
          <label for="title">帖子标题</label>
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
    <div class="content">
      <div v-for="item in items" :key="item.id">
        <article class="get-to" v-if="item.userId == id">
          <div class="left0">
            <div class="bar">
              <h1 class="push">{{ item.title }}</h1>
              <button>回复({{ item.replyCount }})</button>
              <p>{{ item.createTime }}</p>
            </div>
            <div class="post-content">
              <p>
                {{ item.content }}
              </p>
              <span class="righttriangle"></span>
            </div>
          </div>
          <div class="right0">
            <div class="bar"></div>
            <div>
              <img :src="item.imgUrl" />
              <p>我</p>
            </div>
          </div>
        </article>
        <article class="get-to" v-if="item.userId != id">
          <div class="right0">
            <div class="bar"></div>
            <div>
              <img :src="item.imgUrl" />
              <p>{{ item.userName }}</p>
            </div>
          </div>
          <div class="left0">
            <div class="bar">
              <h1 class="push">{{ item.title }}</h1>
              <button>回复({{ item.replyCount }})</button>
              <p>{{ item.createTime }}</p>
            </div>
            <div class="post-content">
              <span class="lefttriangle"></span>
              <p>
                {{ item.content }}
              </p>
            </div>
          </div>
        </article>
      </div>

      <a-page-back ref="pageback" :params="queryParam" :pageSize="10" :pageUrl="queryUrl" @pageDone="setItems"/>
    </div>
  </div>
</template>

<script>
import PageBack2 from "./PageBack2.vue";
import UserProfile from "./UserProfile.vue";
import MaskBack from "./MaskBack.vue";
import axios from "axios";
axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/api";

export default {
  name: "PostHome",
  props: {
    id: Number,
  },
  data() {
    return {
      itemlength: [1, 2, 3, 4, 5, 6, 7, 8],
      items: [
        {
          title: "标题标题标题",
          userId: 0,
          createTime: "20202",
          content: "aksahdas",
          replyCount: 100,
        },
      ],
      showPost: false,
      post_data: {
        userId: 0,
        title: "标题",
        content: "内容",
      },
      query_data: {
        userId: 0,
        userName: " ",
        title: " ",
        startDate: "",
        endDate: "",
        pageIndex: 1,
        pageSize: 10,
      },
      page: {
        pageIndex: 1,
        pageSize: 10,
      },
      userIdCache: [],
      queryUrl: "",
      query_urls: ["post/fetch/query", "post/fetch/newest", "post/by_user/"+this.id],
      queryParam: "",
    };
  },
  components: {
    AMaskBack: MaskBack,
    APageBack: PageBack2,
    AUserProfile: UserProfile,
  },
  methods: {
    closePost() {
      this.showPost = false;
      console.log("closePost");
    },
    openPost() {
      this.showPost = true;
    },
    post() {
      this.post_data.userId = this.id;
      this.post_data.createTime = "";
      axios
        .post("post", this.post_data)
        .then((res) => {
          if (res.status == 200) {
            alert("成功");
            this.post_data.createTime = "刚才";
            this.post_data.replyCount = 0;
            this.items.unshift(this.post_data);
            this.closePost();
          } else {
            alert("失败");
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    query() {
      this.queryUrl=this.query_urls[0];
      this.queryParam=this.query_data;
      this.$nextTick(()=>{
        console.log("call page");
        this.$refs.pageback.page();        
      });

      // this.query_data.pageIndex = this.page.pageIndex;
      // this.query_data.pageSize = this.page.pageSize;
      // axios
      //   .get("post/fetch/query", { params: this.query_data })
      //   .then((res) => {
      //     if (res.status == 200) {
      //       this.setItems(res.data);
      //     } else {
      //       alert(res.statusText);
      //     }
      //   })
      //   .catch((e) => {
      //     alert(e);
      //   });
    },
    clearQuery() {
      this.query_data.userId = 0;
      this.query_data.userName = "";
      this.query_data.title = "";
      this.query_data.startDate = "";
      this.query_data = "";
    },
    getNews() {
      this.queryUrl=this.query_urls[1];
      this.queryParam="";
      this.$nextTick(()=>{
        console.log("call page");
        this.$refs.pageback.page();        
      });

      // axios
      //   .get("post/fetch/newest", { params: this.page })
      //   .then((res) => {
      //     if (res.status == 200) {
      //       this.setItems(res.data);
      //     } else {
      //       alert(res.statusText);
      //     }
      //   })
      //   .catch((e) => {
      //     alert(e);
      //   });
    },
    setItems(data) {
      let items = data.data;
      for (let i = 0; i !== items.length; ++i) {
        let v = items[i];
        this.getUserName(v.userId, v);
        v.imgUrl="api/file_resource/download2?lib=user/profile&ownId="+v.userId;
      }
      this.items = items;
    },
    getSelf() {
      this.queryUrl=this.query_urls[2];
      this.queryParam="";
      this.$nextTick(()=>{
        console.log("call page");
        this.$refs.pageback.page();        
      });

      // axios
      //   .get("post/by_user/" + this.id, { params: this.page })
      //   .then((res) => {
      //     if (res.status == 200) {
      //       this.setItems(res.data);
      //     } else {
      //       alert(res.statusText);
      //     }
      //   })
      //   .catch((e) => {
      //     alert(e);
      //   });
    },
    getUserName(id, data) {
      if (id === this.id) {
        return "我";
      } else {
        if (this.userIdCache[id] !== undefined) {
          data.userName = this.userIdCache[id];
          return this.userIdCache[id];
        } else {
          axios.get("user/fetch/" + id).then((res) => {
            if (res.status == 200) {
              this.userIdCache[id] = res.data.userName;
              data.userName = this.userIdCache[id];
            }
          });
        }
      }
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

.post {
  display: flex;
}

.aside {
  width: 30%;
  padding: 1%;
}

.content {
  width: 70%;
  margin: 1%;
  margin-left: 0px;
}

.content button {
  align-self: center;
  margin: 10px auto;
}

.post-button {
  list-style: none;
  display: flex;
  justify-self: stretch;
  align-items: center;
}

.post-button li {
  margin-right: 5%;
  width: 50%;
  justify-self: center;
}

.post-button button {
  align-self: center;
  padding: 10px;
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

.post-card {
  font-size: 1.1em;
  position: fixed;
  padding: 20px;
  margin: 20px;
  width: 400px;
  left: 50%;
  margin-left: -200px;
  margin-top: 100px;
  background-color: #2c3e50;
  border-radius: 20px;
  color: white;
  z-index: 20;
}

.post-card p {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  width: 100%;
}

.post-card input {
  width: 50%;
  height: 30px;
  margin: 2%;
  border-radius: 5px;
  padding: 5px;
}

.post-card button {
  width: 15%;
  padding: 10px;
  margin: 1%;
  border-radius: 5px;
}

.post-card textarea {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  margin: 2%;
  padding: 10px;
  height: 100px;
  width: 90%;
  border-radius: 5px;
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

/*
  POST CONTENT
*/

.get-to {
  display: flex;
  margin: 0%;
  padding: 2%;
  margin-bottom: 10px;
  background-color: #2c3e50;
  color: white;
  border-radius: 10px 10px;
}

.bar {
  display: flex;
  height: 10%;
  margin: 2%;
}

.bar button,
.bar p {
  margin: 5px;
  background: none;
  font-size: 0.9em;
  border: none;
  color: white;
  align-self: center;
  text-align: center;
}

.post-content {
  display: flex;
}

.post-content p {
  text-indent: 2em;
  width: 100%;
  margin: 0px;
  background-color: cadetblue;
  border-radius: 10px;
  padding: 2% 3%;
  display: flex;
}

.push {
  margin-right: auto;
  font-size: 1.2em;
  align-self: center;
  text-align: center;
}

.right0,
.left0 {
  margin-top: 1%;
  padding-bottom: 0px;
}

.left0 {
  width: 80%;
}

.right {
  width: 20%;
}

.right0 p {
  margin: 2px;
  font-size: 1em;
  text-align: center;
}

.right0 img,
.left0 img {
  width: 100px;
  height: 100px;
}

.lefttriangle {
  width: 0px;
  height: 0px;
  border-top: 8px solid transparent;
  border-bottom: 8px solid transparent;
  border-right: 8px solid cadetblue;
  top: 10px;
  position: relative;
}

.righttriangle {
  width: 0px;
  height: 0px;
  border-top: 8px solid transparent;
  border-bottom: 8px solid transparent;
  border-left: 8px solid cadetblue;
  top: 10px;
  position: relative;
}
</style>