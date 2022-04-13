<template>
  <div class="content">
    <div class="aside">
      <a-user-profile />
      <form class="query-form">
        <h1>查询作品</h1>
        <p>
          <label for="aid">作品Id</label>
          <input type="text" name="aid" id="aid" />
        </p>
        <p>
          <label for="uid">用户Id</label>
          <input type="text" name="uid" id="uid" />
        </p>
        <p>
          <label for="uname">用户名</label>
          <input type="text" name="uname" id="uname" />
        </p>
        <p>
          <label for="title">标题</label>
          <input type="text" name="title" id="title" />
        </p>
        <p>
          <label for="timefrom">时间起点</label>
          <input type="date" name="timefrom" id="timefrom" />
        </p>
        <p>
          <label for="timeend">时间终点</label>
          <input type="date" name="timeend" id="timeend" />
        </p>
        <div class="tool">
          <button type="submit">查询</button>
          <button>清空</button>
          <button>最新</button>
        </div>
      </form>
    </div>
    <div class="main">
      <div class="artifact-cards">
        <div class="card" v-for="item in items" :key="item.id">
          <img src="../assets/backpaper.png" alt="">
          <h1>标题标题</h1>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserProfile from "./UserProfile.vue";

export default {
  name: "ArtifactHome",
  components: {
    AUserProfile: UserProfile,
  },
  data(){
    return{
      items:[1,2,3,4,5,6,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
    }
  }
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
  background-color: blue;
}

.main {
  width: 70%;
  margin: 2%;
  background-color: green;
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
  justify-content:flex-start;
}

.card {
  flex: 1;
  height: 31%;
  min-width: 31%;
  max-width: 31%;
  background-color: #999;
  margin: 5px;
  padding: 5px;
  border-radius: 10px;
}

.card:hover{
  box-shadow: 10px 10px 50px #fff;
  box-shadow: -10px -10px 50px #fff;
}

.card img{
  width: 100%;
  height: 100%;
}

.card h1{
  margin: 1%;
  margin-top: 0px;
  padding: 0px;
  font-size: 1.2em;
  text-align: center;
}

.card:nth-child(3n){
  margin-right: 0px;
}

</style>