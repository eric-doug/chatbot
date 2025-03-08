<template>
  <div class="chat-main">
    <div class="chat-main-content" v-for="(item,index) in msgList" :key="index">
      <li>
        <span>{{item.createTime}}</span>
        <span style="width: 100%">{{item.question}}</span>
      </li>
      <li>
        <span style="display: flex;text-align: center">
          <img src="../asserts/image/gpt.png" width="36" height="36">
          <span style="padding-left: 10px">{{item.createTime}}</span>
        </span>
        <span>
          <markdown-it-vue :content="item.answer"  class="markdown-body"  :options="options"></markdown-it-vue>
        </span>
      </li>
    </div>

    <div id="msg_end" style="height: 1px;width: 1px"></div>
  </div>
</template>

<script>
import MarkdownItVue from 'markdown-it-vue'
import 'markdown-it-vue/dist/markdown-it-vue.css'
import {formatDate} from "@/utils/date";
export default {
name: "chat-main",
  components:{MarkdownItVue},
  data(){
  return{
    msgList:[],
    options: {
      markdownIt: {
        linkify: true,
        highlight:true
      },
      linkAttributes: {
        attrs: {
          target: '_blank',
          rel: 'noopener'
        }
      }
    }
  }
  },
  methods:{
    appendQ(question){
      let param = {id:2,groupId:"148",question:question,answer:"",createTime:this.formatDate(new Date())}
      this.msgList.push(param);
      this.msgIndex = this.msgList.length -1
    },
    appendMsg(data){
      if(data.content){
        this.msgList[this.msgIndex].answer+=data.content
      }
    },
    toBottom() {
      this.$nextTick(() => {
        document.querySelector('#msg_end').scrollIntoView({behavior:"smooth"})
      })
    },
    formatDate(time){
      return formatDate(time, 'yyyy-MM-dd hh:mm:ss')
    },
  }
}
</script>

<style scoped>
.chat-main{
  height: calc(90vh);
  text-align: center;
  width: 100%;
  overflow-y: auto;
}
.markdown-body{
  color: #0c0c0c !important;
  margin-left: 10px;
  border-radius: 5px;
  padding-top: 10px;
}
.chat-main-content li{
  list-style-type: none;
  line-height: 36px;
  /*border:solid 1px white;*/
  margin: auto;
}
li:nth-child(odd) {
  /* 设置奇数项样式 */
  margin-right: 1.5vw;
  text-align: right;
  display: flex;
  flex-direction: column;
}

li:nth-child(even) {
  /* 设置偶数项样式 */
  text-align: left;
  margin-left: 1.5vw;
}

</style>