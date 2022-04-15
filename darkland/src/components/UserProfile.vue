<template>
  <div class="user">
    <div class="tap"></div>
    <div id="profile">
      <div class="firstline">
        <img src="../assets/backpaper.png" />
        <button>修改资料</button>
      </div>
      <div class="secondline">
        <p class="user-name">{{ my_user.username }} ({{ my_user.id }})</p>
        <p class="user-email">{{ my_user.profile.email }}</p>
      </div>
      <div class="thirdline">
        <p>@{{ my_user.profile.work }}</p>
        <p>@{{ my_user.profile.company }}</p>
        <p>@{{ my_user.profile.website }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.withCredentials=true;
axios.defaults.baseURL="/api";

export default {
  name: "UserProfile",
  props:{
    id:Number,
  },
  data() {
    return {
      my_user: {
        id: 0,
        username: "",
        profile: {
          website: "",
          company: "",
          work: "",
        },
      },
    };
  },
  mounted(){
    axios.get("/user/fetch_with_profile/"+this.id)
    .then(res=>{
      if(res.status==200){
        this.my_user=res.data;
      }
    })
  }
};
</script>

<style scoped>
.user {
  background-image: url(../assets/backpaper.png);
  width: 100%;
  height: 400px;
  background-size: cover;
  border-radius: 20px;
}

.tap {
  width: 100%;
  height: 45%;
}

.firstline {
  width: 100%;
  padding: 10px;
  display: flex;
}

.firstline img {
  margin-bottom: 0px;
  width: 100px;
  height: 100px;
  margin-right: auto;
}

.firstline button {
  margin-right: 20px;
  padding: 12px;
  align-self: center;
  border-radius: 20%;
}

.user-name {
  font-size: 1.2em;
  color: white;
  margin: 0px;
  margin-left: 1em;
}

.user-email {
  color: darkgray;
  margin: 0px;
  margin-top: 0.5em;
  margin-left: 1em;
}

.thirdline {
  display: flex;
}

.thirdline p {
  width: 33%;
  text-align: center;
  color: whitesmoke;
}
</style>