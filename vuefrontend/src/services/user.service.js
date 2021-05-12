import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/todos/';

class UserService {

  getUserBoard() {
    return axios.get(API_URL, { headers: authHeader() });
  }
  postTodo(todoText){
    return axios.post(API_URL+"add",{
      text: todoText
    })

  }
  deleteTodo(id){
    return axios.delete(API_URL+id)
  }
  changeTodoStatus(id){
    return axios.put(API_URL+id)
  }
  editTodo(id,todoText){
    return axios.put(API_URL+id+"/edit",{
      text : todoText
    })
  }
}

export default new UserService();
