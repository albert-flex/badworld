<template>
  <div class="user">
    <div class="tap"></div>
    <div id="profile">
      <div class="firstline">
        <img :src="avator" />
        <input
          type="file"
          name="uploadAvator"
          id="uploadAvator"
          v-if="isEdit"
          @change="getFile($event)"
        />
        <button @click="toEdit" v-if="!isEdit">修改资料</button>
        <button @click="confirm" v-if="isEdit">完成</button>
      </div>
      <div class="secondline">
        <p class="user-name" v-if="!isEdit">
          {{ my_user.username }} ({{ my_user.id }})
        </p>
        <input
          type="text"
          name="username"
          placeholder="用户名"
          class="user-name"
          id="username"
          v-model="editData.userName"
          v-if="isEdit"
        />
        <p class="user-email" v-if="!isEdit">{{ my_user.profile.email }}</p>
        <input
          type="email"
          name="useremail"
          placeholder="邮箱"
          class="user-email"
          id="email"
          v-model="editData.profile.email"
          v-if="isEdit"
        />
      </div>
      <div class="thirdline">
        <p v-if="!isEdit">@{{ my_user.profile.work }}</p>
        <p v-if="!isEdit">@{{ my_user.profile.company }}</p>
        <p v-if="!isEdit">@{{ my_user.profile.website }}</p>
        <input
          type="text"
          placeholder="工作"
          v-model="editData.profile.work"
          v-if="isEdit"
        />
        <input
          type="text"
          placeholder="公司"
          v-model="editData.profile.company"
          v-if="isEdit"
        />
        <input
          type="text"
          placeholder="网站"
          v-model="editData.profile.website"
          v-if="isEdit"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/api";

export default {
  name: "UserProfile",
  props: {
    id: Number,
  },
  data() {
    return {
      my_user: {
        id: 0,
        username: "",
        profile: {
          email: "",
          website: "",
          company: "",
          work: "",
        },
      },
      avatorFiles: [],
      avator: "",
      isEdit: false,
      editData: {
        id: 0,
        username: "",
        profile: {
          userId: 0,
          email: "",
          work: "",
          company: "",
          website: "",
        },
      },
    };
  },
  mounted() {
    this.getSelf();
  },
  methods: {
    getFile(event) {
      const files = event.target.files;
      this.avatorFiles[0] = files[0];
    },
    getSelf() {
      axios
        .get("user/fetch_with_profile/" + this.id)
        .then((res) => {
          if (res.status == 200) {
            this.my_user = res.data;
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
      this.avator =
        "api/file_resource/download2?lib=user/profile&ownId=" + this.id;
    },
    toEdit() {
      this.editData.id = this.my_user.id;
      this.editData.profile.userId = this.my_user.id;
      this.editData.profile.email = this.my_user.profile.email;
      this.editData.profile.work = this.my_user.profile.work;
      this.editData.profile.website = this.my_user.profile.website;
      this.editData.username = this.my_user.username;
      this.isEdit = true;
    },
    postAvator() {
      let formData = new FormData();
      formData.append("lib", "user/profile");
      formData.append("ownId", this.id);
      formData.append("name", "avator");
      formData.append("file", this.avatorFiles[0]);
      axios
        .post("file_resource/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          if (res.status == 200) {
            alert("成功，id:" + res.data);
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    confirm() {
      axios
        .put("user/update", this.editData)
        .then((res) => {
          if (res.status == 200) {
            axios
              .put("user/update_profile", this.editData.profile)
              .then((res2) => {
                if (res2.status == 200) {
                  alert("修改成功");
                  this.getSelf();
                  this.postAvator();
                } else {
                  alert("失败：" + res2.statusText);
                }
              });
          } else {
            alert(res.statusText);
          }
        })
        .catch((e) => {
          alert(e);
        });
      this.isEdit = false;
    },
  },
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
  background: none;
  font-size: 1.2em;
  color: white;
  margin: 0px;
  margin-left: 1em;
}

.user-email {
  background: none;
  color: white;
  margin: 0px;
  margin-top: 0.5em;
  margin-left: 1em;
}

.thirdline {
  display: flex;
}

.thirdline p,
.thirdline input {
  background: none;
  width: 33%;
  text-align: center;
  color: white;
}
</style>