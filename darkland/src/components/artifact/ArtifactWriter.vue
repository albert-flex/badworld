<template>
  <div class="all">
    <div class="head">
      <div class="avator">
        <img alt="用户头像" src="./default.png" />
        <input type="file" name="avataor" id="avator-button" />
      </div>
      <div class="data" v-if="!isView">
        <p>
          <label for="name">名称</label>
          <input type="text" name="name" id="name" :disabled="isView"/>
        </p>
        <p>
          <label for="name">标题</label>
          <input type="text" name="title" id="title" :disabled="isView"/>
        </p>
        <p>
          <label for="type">类型</label>
          <select name="type" id="type" :disabled="isView">
            <option value="game">游戏</option>
            <option value="music">音乐</option>
            <option value="manga">漫画</option>
            <option value="novel">小说</option>
          </select>
          <button @click="addNewType">+</button>
        </p>
        <p>
          <label for="version">版本</label>
          <input type="text" name="version" id="version" :disabled="isView"/>
        </p>
        <div class="tools">
          <button>取消</button>
          <button>完成</button>
        </div>
      </div>
      <div class="data2" v-if="isView">
        <p>
          <label for="name">名称</label>
          <label>名称</label>
        </p>
        <p>
          <label for="title">标题</label>
          <label>标题</label>
        </p>
        <p>
          <label for="type">类型</label>
          <label>类型</label>
        </p>
        <p>
          <label for="version">版本</label>
          <label>版本</label>
        </p>
      </div>
    </div>
    <h1>作品内容</h1>
    <div class="content">
      <!-- <a-editor v-model="text" ></a-editor> -->
      <!-- <a-view :text="text"/> -->
      <component
        :is="currentMk"
        class="content-area"
        height="800px"
        :text="text"
      />
    </div>
  </div>
</template>

<script>
import VMdEditor from "@kangc/v-md-editor";
import VMdPreview from "@kangc/v-md-editor/lib/preview";

export default {
  name: "ArtifactWriter",
  data() {
    return {
      text: "",
      isView: false,
    };
  },
  computed: {
    currentMk() {
      return this.isView ? "AView" : "AEditor";
    },
  },
  components: {
    AEditor: VMdEditor,
    AView: VMdPreview,
  },
  methods:{
    addNewType(){
      let name=prompt("please enter type name","default");
      name;
    }
  },
};
</script>

<style scoped>
.all {
  padding: 2%;
}

.all h1 {
  text-align: center;
  text-shadow: -10px -10px 30px gray;
}

.head {
  display: flex;
  justify-content: flex-start;
  margin-bottom: 5%;
  background-color: #2c3e50;
  color: white;
  padding: 5%;
  box-shadow: -10px 10px 50px gray;
  border-radius: 10px;
}

.avator {
  width: 50%;
}

.data ,.data2{
  width: 50%;
  padding: 2%;
}

.data2 p label{
  width: 40%;
  margin: 5%;
  padding: 5%;
}

.head img {
  width: 80%;
  height: 80%;
}

.content-area {
  height: 800px;
  min-height: 800px;
}

.data p label {
  width: 30%;
  margin-left: 5%;
  margin-right: 5%;
}

.data p input,
.data p select {
  width: 60%;
  margin-left: 5%;
  padding: 2%;
}

.avator p input[type="file"] {
  padding: 10px;
  background: none;
}

.data p button {
  padding: 2%;
  background: none;
  background-color: white;
}

.data p option {
  padding: 0px;
}

.tools {
  display: flex;
}

.tools button {
  margin: 10%;
  padding: 2% 4% 2% 4%;
}
</style>