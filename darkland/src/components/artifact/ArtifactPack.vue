<template>
  <div class="artifact-cards">
    <div class="card" v-for="item in items" :key="item.id">
      <img src="./backpaper.png" alt="" />
      <h1>标题标题</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: "ArtifactPack",
  data() {
    return {
      items: [1, 2, 3, 4, 5, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    };
  },
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
  background-color: #999;
  margin: 10px;
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