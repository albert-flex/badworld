import { createApp } from 'vue'
import App from './App.vue'
import VMdEditor from "@kangc/v-md-editor";
import "@kangc/v-md-editor/lib/style/base-editor.css";
import githubTheme from "@kangc/v-md-editor/lib/theme/github";
import "@kangc/v-md-editor/lib/theme/style/github.css";
import VMdPreview from "@kangc/v-md-editor/lib/preview";
import "@kangc/v-md-editor/lib/style/preview.css";


VMdEditor.use(githubTheme);
VMdPreview.use(githubTheme);

const app=createApp(App);

app.use(VMdEditor);
app.use(VMdPreview);
app.mount('#app');