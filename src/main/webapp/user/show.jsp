<%--
  Created by IntelliJ IDEA.
  User: Jackie Zhou
  Date: 2020/12/27
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/user-demo/js/vue-2.6.12.js"></script>
    <script type="text/javascript" src="/user-demo/js/axios-0.21.0.js"></script>
    <script type="text/javascript" src="/user-demo/js/qs-6.9.4.js"></script>
    <script type="text/javascript" src="/user-demo/js/vue-router-3.4.9.js"></script>
</head>
<body>
    <div id="app">
        <router-link to="/user/show"><button>查询</button></router-link>
        <router-link to="/user/add"><button>添加</button></router-link>
        <hr>
        <router-view></router-view>
    </div>
    <template id="user-table">
        <div>
            <form action="" @submit.prevent="handleAClick(1)">
                <input type="text" v-model="query">
                <input type="submit" value="查询">
            </form>
            <table border="1">
                <thead>
                <tr>
                    <th>用户编号</th>
                    <th>用户名</th>
                    <th>用户邮箱</th>
                    <th>密码</th>
                    <th>手机号码</th>
                    <th>用户职位</th>
                    <th>在职状态</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="u in users" :key="u.userId">
                    <td>{{u.userId}}</td>
                    <td>{{u.username}}</td>
                    <td>{{u.email}}</td>
                    <td>{{u.pwd}}</td>
                    <td>{{u.mobile}}</td>
                    <td>{{u.role}}</td>
                    <td>{{u.status}}</td>
                    <td>{{u.createTime}}</td>
                    <td>{{u.updateTime}}</td>
                    <td>
                        <button @click="removeUser(u.userId)">删除</button>
                        <router-link :to="{path:'/user/update',query:{id:u.userId}}"><button>修改</button></router-link>

                    </td>
                </tr>
                </tbody>
            </table>
            <p>
                <a href="" v-for="page in pages" :key="page" @click.prevent="handleAClick(page)">
                    <button style="margin: 0 10px">{{page}}</button>
                </a>
            </p>
        </div>
    </template>

    <template id="add-form">
        <form action="" @submit.prevent="addUser">
            用户名<input type="text" v-model="u.username" id=""><br>
            用户邮箱<input type="text" v-model="u.email" id=""><br>
            密码<input type="text" v-model="u.pwd" id=""><br>
            手机号码<input type="text" v-model="u.mobile" id=""><br>
            用户职位<input type="text" v-model="u.role" id=""><br>
            在职状态<input type="number" v-model="u.status" id=""><br>
            <input type="submit" value="添加">
        </form>
    </template>
    
    <template id="update-form">
        <form action="" @submit.prevent="updateUser">
            <input type="hidden" v-model="u.userId">
            用户名<input type="text" v-model="u.username" id=""><br>
            用户邮箱<input type="text" v-model="u.email" id=""><br>
            密码<input type="text" v-model="u.pwd" id=""><br>
            手机号码<input type="text" v-model="u.mobile" id=""><br>
            用户职位<input type="text" v-model="u.role" id=""><br>
            在职状态<input type="number" v-model="u.status" id=""><br>
            <input type="submit" value="修改">
        </form>
    </template>
    
    
    
    <script>
        const userTable = {
            template:"#user-table",
            data(){
                return{
                    users:[],
                    pages:[],
                    query:"",
                    currentPage:1
                }
            },
            created:function(){
                this.handleAClick(1)
            },
            methods:{
                handleAClick(page){
                    this.currentPage = page;
                    axios.get("/user-demo/users?pageNum="+this.currentPage+"&pageSize="+5+"&query="+this.query)
                        .then(resp=>{
                        this.users = resp.data.users;
                        this.pages = resp.data.pages;
                    }).catch(error=>console.log(error))
                },
                removeUser(id){
                    axios.delete("/user-demo/users/"+id)
                    .then(resp=>{
                        let isDelete = confirm("确认要删除吗?");
                        if(!isDelete){
                            return;
                        }
                        if(resp.data.status == "success"){
                            alert("删除成功");
                            this.handleAClick(this.currentPage);
                        }else{
                            alert("删除失败");
                        }
                    }).catch(error=>console.log(error))
                }
            }
        }
        const addForm = {
            template: "#add-form",
            data() {
                return {
                    u:{}
                }
            },
            methods:{
                addUser(){
                    axios.post("/user-demo/users",this.u)
                    .then(resp=>{
                        if(resp.data.status == "success"){
                            alert("添加成功");
                            this.$router.push("/user/show");
                        }else{
                            alert("添加失败");
                        }
                    }).catch(error=>console.log(error))
                }
            }

        }

        const updateForm = {
            template: "#update-form",
            data(){
                return{
                    u:{}
                }
            },
            created(){
                axios.get("/user-demo/users/"+this.$route.query.id)
                .then(resp=>{
                    this.u = resp.data;
                }).catch(error=>console.log(error))
            },
            methods:{
                updateUser:function(){
                    axios.put("/user-demo/users",this.u)
                        .then(resp=>{
                            if(resp.data.status == "success"){
                                console.log("修改成功");
                                this.$router.push("/user/show");
                            }else{
                                console.log("修改失败");
                            }
                        }).catch(error=>console.log(error))
                }

            }
        }

        const router = new VueRouter({
            routes:[
                {path:"/user/show",component:userTable},
                {path:"/user/add",component:addForm},
                {path:"/user/update",component:updateForm}
            ]
        })

        const vm = new Vue({
            el:"#app",
            router
        })
    </script>


</body>
</html>
