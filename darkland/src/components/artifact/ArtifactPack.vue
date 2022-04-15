<template>
  <div class="artifact-cards">
    <div class="card" v-for="item in items" :key="item.id" @click="$emit('view',item.id)">
      <img src="./default.png" alt="" />
      <h1>{{ item.title }}</h1>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.withCredentials=true;
axios.defaults.baseURL="/api";

export default {
  name: "ArtifactPack",
  emits:["view"],
  data() {
    return {
      items: [
        {
          id: 0,
          title: "标题标题",
        },
        {
          id: 1,
          title: "标题标题",
        },
        {
          id: 2,
          title: "标题标题",
        },
        {
          id: 3,
          title: "标题标题",
        },
        {
          id: 4,
          title: "标题标题",
        },
        {
          id: 5,
          title: "标题标题",
        },
        {
          id: 6,
          title: "标题标题",
        },
      ],
    };
  },
  mounted(){
    axios.get("artifact/fetch/news")
    .then(res=>{
      if(res.status==200){
        this.items=res.data;
      }else{
        alert(res.statusText);
      }
    }).catch(e=>{
      alert(e);
    });
  }
};
</script>

<style scoped>
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
  background-color: white;
  margin: 10px;
  padding: 5px;
  border-radius: 10px;
}

.card:hover {
  background-color: #999;
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