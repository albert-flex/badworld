<template>
  <div class="all">
    <div class="head">
      <div class="avator">
        <img alt="用户头像" src="./default.png" />
        <input type="file" name="avataor" id="avator-button" v-if="!isView" />
      </div>
      <div class="data" v-if="!isView">
        <p>
          <label for="name">名称</label>
          <input
            type="text"
            name="name"
            id="name"
            :disabled="isView"
            v-model="artifact.name"
          />
        </p>
        <p>
          <label for="name">标题</label>
          <input
            type="text"
            name="title"
            id="title"
            :disabled="isView"
            v-model="artifact.title"
          />
        </p>
        <p>
          <label for="type">类型</label>
          <select
            name="type"
            id="type"
            :disabled="isView"
            v-model="artifact.typeId"
          >
            <option v-for="item in types" :key="item.id" :value="item.id">{{ item.name }}</option>
          </select>
          <button @click="addNewType">+</button>
        </p>
        <p>
          <label for="version">版本</label>
          <input
            type="text"
            name="version"
            id="version"
            :disabled="isView"
            v-model="artifact.version"
          />
        </p>
        <div class="tools">
          <button>取消</button>
          <button @click="postArtifact">完成</button>
        </div>
      </div>
      <div class="data2" v-if="isView">
        <p>
          <label for="name">名称</label>
          <label>{{ artifact.name }}</label>
        </p>
        <p>
          <label for="title">标题</label>
          <label>{{ artifact.title }}</label>
        </p>
        <p>
          <label for="type">类型</label>
          <label>{{ artifact.type }}</label>
        </p>
        <p>
          <label for="version">版本</label>
          <label>{{ artifact.version }}</label>
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
        :text="artifact.content"
        v-model="artifact.content"
      />
    </div>
  </div>
</template>

<script>
import VMdEditor from "@kangc/v-md-editor";
import VMdPreview from "@kangc/v-md-editor/lib/preview";
import axios from "axios";
axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/api";

export default {
  name: "ArtifactWriter",
  emits:["back"],
  props: {
    viewId: Number,
    isView: Boolean,
    userId: Number,
  },
  data() {
    return {
      editId: -1,
      types: [
        {
          id: 0,
          name: "游戏",
        },
        {
          id: 1,
          name: "音乐",
        },
        {
          id: 2,
          name: "绘画",
        },
      ],
      artifact: {
        id: 0,
        userId: 0,
        name: "名称",
        title: "标题",
        typeId: 0,
        version: "0",
        content: "",
      },
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
  methods: {
    addNewType() {
      let aname = prompt("请输入需要添加的新类型", "default");
      axios.post("artifact/type",{name: aname,userId: this.userId})
      .then(res=>{
        if(res.status==200){
          this.fetchTypeNews();          
        }else{
          alert(res.statusText);
        }
      }).catch(e=>{
        alert(e);
      });
    },
    fetchTypeName(id){
      for(let i=0;i!=this.types.length;++i){
        let a =this.types[i];
        if(a.id==id){
          return a.name;
        }
      }
      return "未知";
    },
    postArtifact() {
      this.artifact.userId =this.userId;
       axios
        .post("artifact", this.artifact)
        .then((res) => {
          if (res.status == 200) {
            alert("成功");
            this.$emit("back");
          } else {
            alert("失败");
          }
        });
    },
    fetchTypeNews() {
      axios
        .get("artifact/fetch/newstype")
        .then((res) => {
          if (res.status == 200) {
            this.types= res.data;
          } else {
            alert(res.statusText);
            return [];
          }
        })
        .catch((e) => {
          alert(e);
          return [];
        });
    },
  },
  mounted() {
    this.fetchTypeNews();
    if (this.isView) {
      axios
        .get("artifact/" + this.viewId)
        .then((res) => {
          if (res.status == 200) {
            this.artifact = res.data;
            this.artifact.type=this.fetchTypeName(res.data.typeId);
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
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

.data,
.data2 {
  width: 50%;
  padding: 2%;
}

.data2 p label {
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