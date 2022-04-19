<template>
  <div class="about-site">
    <div class="board-word">
      <form class="board-post">
        <p>
          <label for="email" >邮箱:</label>
          <input type="email" v-model="left_word.email" name="email" id="email" />
        </p>
        <p>
          <label for="content">内容:</label>
          <textarea name="content" id="content" v-model="left_word.content"></textarea>
        </p>
        <p>
          <button type="submit" @click.stop.prevent="leftWord">留言</button>
        </p>
      </form>
      <div
        v-for="item in board_word_list"
        :key="item.id"
        class="board-word-list"
      >
        <article>
          <h1>{{ item.email }}</h1>
          <p>
            {{ item.content }}
          </p>
          <footer>{{ item.leftTime }}</footer>
        </article>
      </div>
      <word-page-back />
    </div>
    <div class="announce">
      <article>
        <h1>{{ announce.title }}</h1>
        <h2>{{ announce.id }}</h2>
        <div class="announce-content">
            <a-preview :text="announce.content"/>
        </div>
        <footer>2022/4/22 18:29</footer>
      </article>
      <announce-page-back />
    </div>
  </div>
</template>

<script>
import PageBack2 from "./PageBack2.vue";
import axios from "axios";
import VMdPreview from "@kangc/v-md-editor/lib/preview";
axios.defaults.baseURL="/api";

export default {
  name: "AboutSite",
  data() {
    return {
      left_word:{
        email: "",
        content: ""
      },
      board_word_list: [
        {
          email: "natsufumij@163.com",
          content:
            "你们很喜欢这个网站吗？我还可以.你们很喜欢这个网站吗？我还可以你们很喜欢这个网站吗？我还可以你们很喜欢这个网站吗？我还可以",
          leftTime: "2022/4/22 20:32:21",
        },
        {
          email: "natsufumij@163.com",
          content:
            "2你们很喜欢这个网站吗？我还可以.你们很喜欢这个网站吗？我还可以你们很喜欢这个网站吗？我还可以你们很喜欢这个网站吗？我还可以",
          leftTime: "2022/4/22 20:32:21",
        },
        {
          email: "natsufumij@163.com",
          content:
            "3你们很喜欢这个网站吗？我还可以.你们很喜欢这个网站吗？我还可以你们很喜欢这个网站吗？我还可以你们很喜欢这个网站吗？我还可以",
          leftTime: "2022/4/22 20:32:21",
        },
      ],
      announce: {
        id: 1,
        userId: 1,
        title: "关于网站运营",
        publishTime: "2022/4/12 18:21:22",
      },
      page1:{
        pageIndex: 1,
        pageSize: 10,
      },
      page2:{
        pageIndex:1,
        pageSize:1
      }
    };
  },
  methods: {
    leftWord(){
       console.log(this.left_word.email);
       console.log(this.left_word.content);
       axios.post("visit/board_word",this.left_word)
       .then(res=>{
          if(res.status!=200){
            alert(res.statusText);
          }else{
            alert("添加成功");
            this.board_word_list.pop();
            this.board_word_list.unshift({
              email: this.left_word.email,
              content: this.left_word.content,
              leftTime: "right now",
            });
            this.left_word.email='';
            this.left_word.content='';
          }
       }).catch(e=>{
         alert(e);
       });
    }
  },
  components: {
    WordPageBack: PageBack2,
    AnnouncePageBack: PageBack2,
    APreview: VMdPreview,
  },
  mounted() {
    axios
      .get("visit/board_word/newest",{params: this.page1})
      .then((response) => {
        if (response.status == 200) {
          this.board_word_list = response.data.data;
        } else {
          alert(response.statusText);
        }
      })
      .catch(function (error) {
        error;
      });

    axios
      .get("visit/announce/newest",{params: this.page2})
      .then((response) => {
        if (response.status == 200) {
          if (response.data.length != 0) {
            let data = response.data.data[0];
            this.announce = data;
          }
        } else {
          alert(response.statusText);
        }
      })
      .catch(function (error) {
        error;
      });
  },
};
</script>

<style scoped>
.about-site {
  display: flex;
}

.board-word {
  width: 25%;
}

.announce {
  width: 75%;
}

.board-word-list article {
  margin: 7%;
  border-radius: 10px;
  background-color: #2c3e50;
  box-shadow: 3px 3px gray;
  color: whitesmoke;
}

.board-word-list h1 {
  text-align: center;
  padding-top: 5%;
}

.board-word-list p {
  font-size: 1.2em;
  background-color: white;
  color: #2c3e50;
  padding: 2%;
}

.board-word-list footer {
  text-align: right;
  margin: 5%;
  padding-bottom: 5%;
}

.board-post {
  opacity: 0.2;
  padding: 20px;
  margin: 20px;
  background-color: #2c3e50;
  border-radius: 20px;
  color: white;
  position: sticky;
  top: 90px;
}

.board-post:hover{
  opacity: 0.9;
}

.board-post p {
  display: flex;
}

.board-post label {
  height: 30%;
  padding: 10px;
  width: 20%;
  margin-left: 20px;
}

.board-post input {
  height: 30%;
  padding: 10px;
  width: 80%;
  margin-right: 20px;
}

.board-post button {
  margin: 0px auto;
  width: 60px;
  height: 40px;
  font-size: 1em;
  border-radius: 5px;
  border-width: 0px;
  color: black;
}

.board-post textarea {
  padding: 10px;
  width: 80%;
  height: 100px;
  margin-right: 20px;
}

.announce {
  margin: 0px;
  margin-bottom: 5%;
}

.announce article {
  padding-left: 10%;
  padding-right: 10%;
}

.announce-content {
  height: 900px;
}

.announce article h1 {
  text-align: center;
  font-size: 1.8em;
  margin: 5%;
}

.announce article h2 {
  text-align: right;
  font-size: 1.2em;
  margin: 2%;
}

.announce article footer {
  text-align: right;
}

.announce article p {
  text-indent: 2em;
  font-size: 1.2em;
}
</style>