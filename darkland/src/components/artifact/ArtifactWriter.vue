<template>
  <div>
    <div class="head">
        <div class="avator">
            <img alt="用户头像" src="./default.png" width="100px" height="100px">
            <input type="file" name="avataor" id="avator-button">
        </div>
        <div class="profile">
            <p>
                <label for="name">名称</label>
                <input type="text" name="name" id="name">
            </p>
            <p>
                <label for="type">类型</label>
                <select name="type" id="type">
                    <option value="game">游戏</option>
                    <option value="music">音乐</option>
                    <option value="manga">漫画</option>
                    <option value="novel">小说</option>
                </select>
            </p>
        </div>
    </div>
    <a-editor v-model="text" height="400px"></a-editor>
    <div class="tools">
        <button>取消</button>
        <button>完成</button>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import VMdEditor from "@kangc/v-md-editor";

export default {
  name: "ArtifactWriter",
  setup() {
    const text = ref("");
    return {
      text,
    };
  },
  components: {
    AEditor: VMdEditor,
  },
};
</script>

<style scoped>
</style>