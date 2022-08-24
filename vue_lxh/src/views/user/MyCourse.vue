<template>
    <!--我的课程-->
    <div style="margin:auto; width:95%;padding-top:10px">
        <div> 
        <va-tabs v-model="course">
    <template #tabs>
       <va-tab
          v-for="tab in tabs"
          :key="tab.key">
          <va-icon :name="tab.icon"></va-icon>
          {{ tab.title }}
        </va-tab>
    </template>
  </va-tabs>
        </div>
    </div>
</template>

<script>

const tabs=[
    {icon:'play_lesson',key:'myCourse',title:'已购买课程'},
    {icon:'star',key:'collection',title:'我的收藏'}
]

export default{
    data(){
        return{
            course:'',
            tabs,
        }
    }
}
</script>
