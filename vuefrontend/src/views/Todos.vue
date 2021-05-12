<template>
  <div class="container">
    <header class="jumbotron">
      <h3>Todos</h3>
       <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
    </header>
    <form>
      <div class="form-group">
        <input type="text" v-model="todostring"/>
        <button @click="saveTodo()">Add</button>
      </div>
    </form>
    <div v-for="todo in content" :key="todo.id">
      <button class="btn-primary" type="submit" :id="todo.id" @click="changeCompleted($event)">Done</button>
      <input class="form" :class="todo.completed && 'completed'" @keydown.enter="edit($event)" @keydown.delete="deleteTodo($event)" :disabled="!(isAdmin || isModerator)" type="text" :value="todo.text" :id="todo.id">
      <button class="btn-primary" type="submit" :disabled="!(isAdmin)" :id="todo.id" @click="deleteTodo($event)">X</button>
    </div>
  </div>
</template>

<script>
import UserService from '../services/user.service';
export default {
  name: 'Todos',
  data() {
    return {
      content: [],
      todostring: '',
      id:null,
      editString:''
    };
  },
   computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    isAdmin() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
    isModerator() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_MODERATOR');
      }

      return false;
    }
  },
  methods: {
    saveTodo(){
      UserService.postTodo(this.todostring)
    },
    deleteTodo(){
      this.id = event.currentTarget.id
      UserService.deleteTodo(this.id)
      this.reloadPage()
    },
    changeCompleted(){
      this.id = event.currentTarget.id
      UserService.changeTodoStatus(this.id)
      this.reloadPage()
    },
    edit(){
      this.id = event.currentTarget.id
      this.editString = event.currentTarget.value
      UserService.editTodo(this.id,this.editString)
      this.reloadPage()
    },
    reloadPage(){
      window.location.reload();
    },
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  },
  mounted() {
    UserService.getUserBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
<style scoped>
.completed{
  text-decoration: line-through;
}
</style>
