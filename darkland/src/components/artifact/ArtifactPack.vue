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
  props:{
    items: [],
  },
  methods:{
    toPage(id,index,size){
      this.$emit(id,index,size);
    }
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