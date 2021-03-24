var app = new Vue({
    el: '.todoapp',
    data: {
      todos: [],
      newTodo: '',
      editedTodo: null,
      STORAGE_KEY: 'todos'
    },

    computed:{
        allDone: {
            set: function (value) {
                this.todos.forEach(function (todo) {
                    todo.completed = value;
                });
            }
        }
    },
    
    methods:{
        save: function () {
			localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.todos));
		},

        addTodo: function () {
            var value = this.newTodo && this.newTodo.trim();
            if (!value) {
                return;
            }
            this.todos.push({ id: Date.now(), title: value, completed: false });
            this.newTodo = '';
            console.log(this.todos)  
        },

        removeTodo: function (todo) {
            var index = this.todos.indexOf(todo);
            this.todos.splice(index, 1);
        },
        
        getTodos: function () {
			this.todos = JSON.parse(localStorage.getItem(this.STORAGE_KEY));
            if (!this.todos){
                this.todos = []
            }
		},
        editTodo: function (todo) {
            this.beforeEditCache = todo.title;
            this.editedTodo = todo;
        },

        doneEdit: function (todo) {
            if (!this.editedTodo) {
                return;
            }
            this.editedTodo = null;
            todo.title = todo.title.trim();
            if (!todo.title) {
                this.removeTodo(todo);
            }
        },

        cancelEdit: function (todo) {
            this.editedTodo = null;
            todo.title = this.beforeEditCache;
        },
    },


    mounted() {
        this.$nextTick(function () {
           this.getTodos()
        })
      },
    beforeUpdate() {
        this.$nextTick(function () {
           this.save()
        })
      },
  })

