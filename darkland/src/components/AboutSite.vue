<template>
  <div class="about-site">
    <div class="board-word">
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
      <form class="board-post">
        <p>
          <label for="email">邮箱:</label>
          <input type="email" name="email" id="email" />
        </p>
        <p>
          <label for="content">内容:</label>
          <textarea name="content" id="content"></textarea>
        </p>
        <p>
          <button type="submit">留言</button>
        </p>
      </form>
    </div>
    <div class="announce">
      <article>
        <h1>{{ announce.title }}</h1>
        <h2>{{ announce.id }}</h2>
        <div class="announce-content">
          <p v-for="item in announce.content" :key="item.id">
            {{ item }}
          </p>
        </div>
        <footer>2022/4/22 18:29</footer>
      </article>
      <announce-page-back />
    </div>
  </div>
</template>

<script>
import PageBack from "./PageBack.vue";
import axios from "axios";

export default {
  name: "AboutSite",
  data() {
    return {
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
        content: [],
      },
    };
  },
  methods: {},
  components: {
    WordPageBack: PageBack,
    AnnouncePageBack: PageBack,
  },
  mounted() {
    axios
      .get("http://localhost:9001/visit/board_word/newest")
      .then((response) => {
        if (response.status == 200) {
          this.board_word_list = response.data;
        } else {
          alert(response.statusText);
        }
      })
      .catch(function (error) {
        error;
      });

    axios
      .get("http://localhost:9001/visit/announce/newest")
      .then((response) => {
        if (response.status == 200) {
          if (response.data.length != 0) {
            let data = response.data[0];
            let words = [];
            for (let i = 0; i != 10; ++i) {
              words.push(data.content);
            }
            data.content = words;
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
  padding: 20px;
  margin: 20px;
  background-color: #2c3e50;
  border-radius: 20px;
  color: white;
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