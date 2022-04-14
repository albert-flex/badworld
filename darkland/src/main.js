import { createApp } from 'vue'
import App from './App.vue'
import VMdEditor from "@kangc/v-md-editor";
import "@kangc/v-md-editor/lib/style/base-editor.css";
import githubTheme from "@kangc/v-md-editor/lib/theme/github";
import "@kangc/v-md-editor/lib/theme/style/github.css";

VMdEditor.use(githubTheme);

const app=createApp(App).mount('#app');

app.use(VMdEditor);